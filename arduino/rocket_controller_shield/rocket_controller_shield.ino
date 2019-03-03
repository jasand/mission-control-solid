#include <LSM9DS1_Registers.h>
#include <LSM9DS1_Types.h>
// The SFE_LSM9DS1 library requires both Wire and SPI be
// included BEFORE including the 9DS1 library.
#include <Wire.h>
#include <SPI.h>
#include <SparkFunLSM9DS1.h>
#include <SparkFunMPL3115A2.h>
#include <SoftwareSerial.h>

MPL3115A2 myPressure;

LSM9DS1 imu;
#define LSM9DS1_M  0x1E // Would be 0x1C if SDO_M is LOW
#define LSM9DS1_AG  0x6B // Would be 0x6A if SDO_AG is LOW

#define DECLINATION 3.16

#define PRINT_CALCULATED
//#define PRINT_RAW
#define PRINT_SPEED_ALT 133 // ms between prints
#define PRINT_SPEED_IMU 50 // ms between prints
static unsigned long lastPrintAlt = 0; // Keep track of print time
static unsigned long lastPrintImu = 0; // Keep track of print time

char INIT_CMD[5] = "INIT";
char START_CMD[5] = "STRT";
char STOP_CMD[5] = "STOP";
char HEARTBEAT_CMD[5] = "HRTB";
char rcvCmdBuf[5];
int CMD_LEN = 4;

static int STOPPED = 0;
static int INITIALIZING = 1;
static int STARTED = 2;
static int state = STOPPED;

SoftwareSerial XBee(2, 3); // RX, TX

void setup() {
  Serial.begin(57600);
  //XBee.begin(57600);

  imu.settings.device.commInterface = IMU_MODE_I2C;
  imu.settings.device.mAddress = LSM9DS1_M;
  imu.settings.device.agAddress = LSM9DS1_AG;

  setupGyro(); // Set up gyroscope parameters
  setupAccel(); // Set up accelerometer parameters
  setupMag(); // Set up magnetometer parameters

  if (!imu.begin()) {
    Serial.println("Failed to communicate with LSM9DS1.");
    Serial.println("Double-check wiring.");
    Serial.println("Default settings in this sketch will " \
                  "work for an out of the box LSM9DS1 " \
                  "Breakout, but may need to be modified " \
                  "if the board jumpers are.");
    while (1)
      ;
  }
  Wire.setClock(400000);

  //Configure the altimeter sensor
  myPressure.setModeAltimeter(); // Measure altitude above sea level in meters
  //myPressure.setModeBarometer(); // Measure pressure in Pascals from 20 to 110 kPa
  myPressure.setOversampleRate(5); // Trade off... Sampler ca 7,5 Hz
  myPressure.enableEventFlags(); // Enable all three pressure and temp event flags 
  //Serial.println("SETUP");
}

void loop() {

  processAnyReceivedCommandsSerial();

  if (state == STARTED || state == INITIALIZING) {
    if ((lastPrintImu + PRINT_SPEED_IMU) <= millis()) {
      lastPrintImu = millis();
      printIMUSerial();
    }
    if ((lastPrintAlt + PRINT_SPEED_ALT) <= millis()) {
      lastPrintAlt = millis();
      printAltitudeSerial();
    }
  }
  
}


void printIMUSerial() {
  long readStart = millis();
  if ( imu.gyroAvailable() ){
    imu.readGyro();
  }
  if ( imu.accelAvailable() ) {
    imu.readAccel();
  }
  if ( imu.magAvailable() ) {
    imu.readMag();
  }
  long readEnd = millis();
  Serial.print("{\"ts\":");
  Serial.print(millis());
  Serial.print(",\"gx\":");
  Serial.print(imu.calcGyro(imu.gx), 2);
  Serial.print(",\"gy\":");
  Serial.print(imu.calcGyro(imu.gy), 2);
  Serial.print(",\"gz\":");
  Serial.print(imu.calcGyro(imu.gz), 2);
  Serial.print(",\"ax\":");
  Serial.print(imu.calcAccel(imu.ax), 2);
  Serial.print(",\"ay\":");
  Serial.print(imu.calcAccel(imu.ay), 2);
  Serial.print(",\"az\":");
  Serial.print(imu.calcAccel(imu.az), 2);
  Serial.print(",\"mx\":");
  Serial.print(imu.calcMag(imu.mx), 2);
  Serial.print(",\"my\":");
  Serial.print(imu.calcMag(imu.my), 2);
  Serial.print(",\"mz\":");
  Serial.print(imu.calcMag(imu.mz), 2);
  Serial.println("}");
}

void printAltitudeSerial() {
  long readStart = millis();
  float altitude = myPressure.readAltitude();
  float temperature = myPressure.readTemp();
  long readEnd = millis();
  Serial.print("{\"ts\":");
  Serial.print(millis());
  Serial.print(",\"alt\":");
  Serial.print(altitude, 2);
  Serial.print(",\"temp\":");
  Serial.print(temperature, 2);
  Serial.println("}");
}


void processAnyReceivedCommandsSerial() {
  while (Serial.available()) {
    char rcvd = Serial.read();
    int rcvBufLen = strlen(rcvCmdBuf);
    rcvCmdBuf[rcvBufLen] = rcvd;
    if (strlen(rcvCmdBuf) >= CMD_LEN) {
      if (strcmp(INIT_CMD, rcvCmdBuf) == 0) {
        state = INITIALIZING;
        printCommandReply(INIT_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(START_CMD, rcvCmdBuf) == 0) {
        state = STARTED;
        printCommandReply(START_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(STOP_CMD, rcvCmdBuf) == 0) {
        state = STOPPED;
        printCommandReply(STOP_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(HEARTBEAT_CMD, rcvCmdBuf) == 0) {
        printCommandReply(HEARTBEAT_CMD);
        clearRcvCmdBuf();
      } else {
        char discarded[2]; 
        discarded[0]= rcvCmdBuf[0];
        discarded[1] = '\0';
        for (int i=0; i<CMD_LEN; i++) {
          rcvCmdBuf[i] = rcvCmdBuf[i+1]; // Shift all one left
        }
      }
    } // if
  } // while
}

void printIMU() {
  long readStart = millis();
  if ( imu.gyroAvailable() ){
    imu.readGyro();
  }
  if ( imu.accelAvailable() ) {
    imu.readAccel();
  }
  if ( imu.magAvailable() ) {
    imu.readMag();
  }
  long readEnd = millis();
  Serial.print("IMU read millis: ");
  Serial.println(readEnd-readStart);

  XBee.print("{\"ts\":");
  XBee.print(millis());
  XBee.print(",\"gx\":");
  XBee.print(imu.calcGyro(imu.gx), 2);
  XBee.print(",\"gy\":");
  XBee.print(imu.calcGyro(imu.gy), 2);
  XBee.print(",\"gz\":");
  XBee.print(imu.calcGyro(imu.gz), 2);
  XBee.print(",\"ax\":");
  XBee.print(imu.calcAccel(imu.ax), 2);
  XBee.print(",\"ay\":");
  XBee.print(imu.calcAccel(imu.ay), 2);
  XBee.print(",\"az\":");
  XBee.print(imu.calcAccel(imu.az), 2);
  XBee.print(",\"mx\":");
  XBee.print(imu.calcMag(imu.mx), 2);
  XBee.print(",\"my\":");
  XBee.print(imu.calcMag(imu.my), 2);
  XBee.print(",\"mz\":");
  XBee.print(imu.calcMag(imu.mz), 2);
  XBee.println("}");
}

void printAltitude() {
  long readStart = millis();
  float altitude = myPressure.readAltitude();
  float temperature = myPressure.readTemp();
  long readEnd = millis();
  Serial.print("ALT read millis: ");
  Serial.println(readEnd-readStart);
  XBee.print("{\"ts\":");
  XBee.print(millis());
  XBee.print(",\"alt\":");
  XBee.print(altitude, 2);
  XBee.print(",\"temp\":");
  XBee.print(temperature, 2);
  XBee.println("}");
}

void processAnyReceivedCommands() {
  while (XBee.available()) {
    char rcvd = XBee.read();
    int rcvBufLen = strlen(rcvCmdBuf);
    rcvCmdBuf[rcvBufLen] = rcvd;
    if (strlen(rcvCmdBuf) >= CMD_LEN) {
      if (strcmp(INIT_CMD, rcvCmdBuf) == 0) {
        state = INITIALIZING;
        printCommandReply(INIT_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(START_CMD, rcvCmdBuf) == 0) {
        state = STARTED;
        printCommandReply(START_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(STOP_CMD, rcvCmdBuf) == 0) {
        state = STOPPED;
        printCommandReply(STOP_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(HEARTBEAT_CMD, rcvCmdBuf) == 0) {
        printCommandReply(HEARTBEAT_CMD);
        clearRcvCmdBuf();
      } else {
        char discarded[2]; 
        discarded[0]= rcvCmdBuf[0];
        discarded[1] = '\0';
        for (int i=0; i<CMD_LEN; i++) {
          rcvCmdBuf[i] = rcvCmdBuf[i+1]; // Shift all one left
        }
        Serial.print("Shifted left: ");
        Serial.println(discarded);
      }
    } // if
  } // while
}

void clearRcvCmdBuf() {
  for (int i=0; i<CMD_LEN; i++) {
    rcvCmdBuf[i] = '\0';
  }
  Serial.println("Cleared buf");
}

void printCommandReply(char* cmd) {
  Serial.print("{\"replyFor\":\"");
  Serial.print(cmd);
  Serial.println("\",\"status\":\"OK\"}");
  XBee.print("{\"replyFor\":\"");
  XBee.print(cmd);
  XBee.println("\",\"status\":\"OK\"}");
}

boolean cmdEquals(char* str1, char* str2) {
  for (int i=0; i<4; i++) {
    if (str1[i] != str2[i]) {
      return false;
    }
  }
  return true;
}


void setupGyro()
{
  // [enabled] turns the gyro on or off.
  imu.settings.gyro.enabled = true;  // Enable the gyro
  // [scale] sets the full-scale range of the gyroscope.
  // scale can be set to either 245, 500, or 2000
  imu.settings.gyro.scale = 500; // Set scale to +/-245dps
  // [sampleRate] sets the output data rate (ODR) of the gyro
  // sampleRate can be set between 1-6
  // 1 = 14.9    4 = 238
  // 2 = 59.5    5 = 476
  // 3 = 119     6 = 952
  imu.settings.gyro.sampleRate = 2; // 59.5Hz ODR
  // [bandwidth] can set the cutoff frequency of the gyro.
  // Allowed values: 0-3. Actual value of cutoff frequency
  // depends on the sample rate. (Datasheet section 7.12)
  imu.settings.gyro.bandwidth = 0;
  // [lowPowerEnable] turns low-power mode on or off.
  imu.settings.gyro.lowPowerEnable = false; // LP mode off
  // [HPFEnable] enables or disables the high-pass filter
  imu.settings.gyro.HPFEnable = true; // HPF disabled
  // [HPFCutoff] sets the HPF cutoff frequency (if enabled)
  // Allowable values are 0-9. Value depends on ODR.
  // (Datasheet section 7.14)
  imu.settings.gyro.HPFCutoff = 1; // HPF cutoff = 4Hz
  // [flipX], [flipY], and [flipZ] are booleans that can
  // automatically switch the positive/negative orientation
  // of the three gyro axes.
  imu.settings.gyro.flipX = false; // Don't flip X
  imu.settings.gyro.flipY = false; // Don't flip Y
  imu.settings.gyro.flipZ = false; // Don't flip Z
}

void setupAccel()
{
  // [enabled] turns the acclerometer on or off.
  imu.settings.accel.enabled = true; // Enable accelerometer
  // [enableX], [enableY], and [enableZ] can turn on or off
  // select axes of the acclerometer.
  imu.settings.accel.enableX = true; // Enable X
  imu.settings.accel.enableY = true; // Enable Y
  imu.settings.accel.enableZ = true; // Enable Z
  // [scale] sets the full-scale range of the accelerometer.
  // accel scale can be 2, 4, 8, or 16
  imu.settings.accel.scale = 8; // Set accel scale to +/-8g.
  // [sampleRate] sets the output data rate (ODR) of the
  // accelerometer. ONLY APPLICABLE WHEN THE GYROSCOPE IS
  // DISABLED! Otherwise accel sample rate = gyro sample rate.
  // accel sample rate can be 1-6
  // 1 = 10 Hz    4 = 238 Hz
  // 2 = 50 Hz    5 = 476 Hz
  // 3 = 119 Hz   6 = 952 Hz
  imu.settings.accel.sampleRate = 2; // Set accel to 10Hz.
  // [bandwidth] sets the anti-aliasing filter bandwidth.
  // Accel cutoff freqeuncy can be any value between -1 - 3. 
  // -1 = bandwidth determined by sample rate
  // 0 = 408 Hz   2 = 105 Hz
  // 1 = 211 Hz   3 = 50 Hz
  imu.settings.accel.bandwidth = 0; // BW = 408Hz
  // [highResEnable] enables or disables high resolution 
  // mode for the acclerometer.
  imu.settings.accel.highResEnable = false; // Disable HR
  // [highResBandwidth] sets the LP cutoff frequency of
  // the accelerometer if it's in high-res mode.
  // can be any value between 0-3
  // LP cutoff is set to a factor of sample rate
  // 0 = ODR/50    2 = ODR/9
  // 1 = ODR/100   3 = ODR/400
  imu.settings.accel.highResBandwidth = 0;  
}

void setupMag()
{
  // [enabled] turns the magnetometer on or off.
  imu.settings.mag.enabled = true; // Enable magnetometer
  // [scale] sets the full-scale range of the magnetometer
  // mag scale can be 4, 8, 12, or 16
  imu.settings.mag.scale = 4; // Set mag scale to +/-12 Gs
  // [sampleRate] sets the output data rate (ODR) of the
  // magnetometer.
  // mag data rate can be 0-7:
  // 0 = 0.625 Hz  4 = 10 Hz
  // 1 = 1.25 Hz   5 = 20 Hz
  // 2 = 2.5 Hz    6 = 40 Hz
  // 3 = 5 Hz      7 = 80 Hz
  imu.settings.mag.sampleRate = 5; // Set OD rate to 20Hz
  // [tempCompensationEnable] enables or disables 
  // temperature compensation of the magnetometer.
  imu.settings.mag.tempCompensationEnable = false;
  // [XYPerformance] sets the x and y-axis performance of the
  // magnetometer to either:
  // 0 = Low power mode      2 = high performance
  // 1 = medium performance  3 = ultra-high performance
  imu.settings.mag.XYPerformance = 3; // Ultra-high perform.
  // [ZPerformance] does the same thing, but only for the z
  imu.settings.mag.ZPerformance = 3; // Ultra-high perform.
  // [lowPowerEnable] enables or disables low power mode in
  // the magnetometer.
  imu.settings.mag.lowPowerEnable = false;
  // [operatingMode] sets the operating mode of the
  // magnetometer. operatingMode can be 0-2:
  // 0 = continuous conversion
  // 1 = single-conversion
  // 2 = power down
  imu.settings.mag.operatingMode = 0; // Continuous mode
}



