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
#define PRINT_SPEED 1000 // ms between prints
static unsigned long lastPrint = 0; // Keep track of print time

char INIT_CMD[5] = "INIT";
char INITIALIZED_MSG[5] = "INTD";
char START_CMD[5] = "STRT";
char STOP_CMD[5] = "STOP";
char HEARTBEAT_CMD[5] = "HRTB";
char rcvCmdBuf[5];
int CMD_LEN = 4;

static int STOPPED = 0;
static int INITIALIZING = 1;
static int INITIALIZED = 2;
static int WAITING = 3;
static int STARTED = 4;
static int state = STOPPED;

const int DISCARD_SAMPLES = 2;
const int BASELINE_SAMPLES = 5;

float baseAx, baseAy, baseAz, baseGx, baseGy, baseGz, baseMx, baseMy, baseMz, baseAlt, baseTemp;
int initCounter = 0;

SoftwareSerial XBee(2, 3); // RX, TX

void setup() {
  Serial.begin(9600);
  XBee.begin(9600);

  imu.settings.device.commInterface = IMU_MODE_I2C;
  imu.settings.device.mAddress = LSM9DS1_M;
  imu.settings.device.agAddress = LSM9DS1_AG;

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

  //Configure the altimeter sensor
  myPressure.setModeAltimeter(); // Measure altitude above sea level in meters
  //myPressure.setModeBarometer(); // Measure pressure in Pascals from 20 to 110 kPa
  myPressure.setOversampleRate(7); // Set Oversample to the recommended 128
  myPressure.enableEventFlags(); // Enable all three pressure and temp event flags 

  
}

void loop() {

  processAnyReceivedCommands();

  if (state == INITIALIZING) {
    if ((lastPrint + PRINT_SPEED) < millis()) {
      if (initCounter == 0) {
        //resetBaseValues();
      }
      imu.readGyro();
      imu.readAccel();
      imu.readMag();
      float altitude = myPressure.readAltitude();
      float temperature = myPressure.readTemp();
      if (initCounter >= DISCARD_SAMPLES) {
        baseAx += imu.calcAccel(imu.ax);
        baseAy += imu.calcAccel(imu.ay);
        baseAz += imu.calcAccel(imu.az);
        baseGx += imu.calcGyro(imu.gx);
        baseGy += imu.calcGyro(imu.gy);
        baseGz += imu.calcGyro(imu.gz);
        baseMx += imu.calcMag(imu.mx);
        baseMy += imu.calcMag(imu.my);
        baseMz += imu.calcMag(imu.mz);
        baseAlt += altitude;
        baseTemp += temperature;
      }
      if (initCounter == DISCARD_SAMPLES + BASELINE_SAMPLES - 1 ) {
        baseAx /= BASELINE_SAMPLES;
        baseAy /= BASELINE_SAMPLES;
        baseAz /= BASELINE_SAMPLES;
        baseGx /= BASELINE_SAMPLES;
        baseGy /= BASELINE_SAMPLES;
        baseGz /= BASELINE_SAMPLES;
        baseMx /= BASELINE_SAMPLES;
        baseMy /= BASELINE_SAMPLES;
        baseMz /= BASELINE_SAMPLES;
        baseAlt /= BASELINE_SAMPLES;
        baseTemp /= BASELINE_SAMPLES;
        printBaseIMU();
        printBaseAltitude();
        state = INITIALIZED;
      }
      lastPrint = millis(); // Update lastPrint time
      initCounter++;
    }
    
  } else if (state == INITIALIZED) {
    printCommandReply(INITIALIZED_MSG); // Signal ready for start...
    state = WAITING;
    
  } else if (state == STARTED) {
    if ((lastPrint + PRINT_SPEED) < millis()) {
      //printGyro();  // Print "G: gx, gy, gz"
      //printAccel(); // Print "A: ax, ay, az"
      //printMag();   // Print "M: mx, my, mz"
      printIMU();
      printAltitude();
      Serial.println();
      lastPrint = millis(); // Update lastPrint time
    }
  }
  
}

void printBaseIMU() {
  XBee.print("{\"ts\":");
  XBee.print(millis());
  XBee.print(",\"baseGx\":");
  XBee.print(baseGx, 2);
  XBee.print(",\"baseGy\":");
  XBee.print(baseGy, 2);
  XBee.print(",\"baseGz\":");
  XBee.print(baseGz, 2);
  XBee.print(",\"baseAx\":");
  XBee.print(baseAx, 2);
  XBee.print(",\"baseAy\":");
  XBee.print(baseAy, 2);
  XBee.print(",\"baseAz\":");
  XBee.print(baseAz, 2);
  XBee.print(",\"baseMx\":");
  XBee.print(baseMx, 2);
  XBee.print(",\"baseMy\":");
  XBee.print(baseMy, 2);
  XBee.print(",\"baseMz\":");
  XBee.print(baseMz, 2);
  XBee.println("}");
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

void printBaseAltitude() {
  XBee.print("{\"ts\":");
  XBee.print(millis());
  XBee.print(",\"baseAlt\":");
  XBee.print(baseAlt, 2);
  XBee.print(",\"baseTemp\":");
  XBee.print(baseTemp, 2);
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
        initCounter = 0;
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


