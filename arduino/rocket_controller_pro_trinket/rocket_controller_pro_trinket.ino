#include <LSM9DS1_Registers.h>
#include <LSM9DS1_Types.h>
#include <Wire.h>
#include <SPI.h>
#include <SparkFunLSM9DS1.h>
#include <SparkFunMPL3115A2.h>

MPL3115A2 myPressure; // Altimeter

LSM9DS1 imu;
#define LSM9DS1_M  0x1E
#define LSM9DS1_AG  0x6B

#define DECLINATION 3.16

#define IGNITION_PIN 5
#define PWR_INDICATOR 6
#define LED_INDICATOR 13 
#define BAUDRATE 57600
#define PRINT_SPEED 125 // ms between prints (8Hz sample frequency)
static unsigned long lastPrint = 0; // Keep track of print time
static unsigned long blinkTime = 0;
static unsigned long blinkInterval = 1000;

char INIT_CMD[5] = "INIT";
char INITIALIZED_MSG[5] = "INTD";
char START_CMD[5] = "STRT";
char STOP_CMD[5] = "STOP";
char HEARTBEAT_CMD[5] = "HRTB";
char rcvCmdBuf[5];
unsigned int CMD_LEN = 4;

static int STOPPED = 0;
static int INITIALIZING = 1;
static int INITIALIZED = 2;
static int WAITING = 3;
static int STARTED = 4;
static int state = STOPPED;

const int DISCARD_SAMPLES = 10;
const int BASELINE_SAMPLES = 20;

float baseAx, baseAy, baseAz, baseGx, baseGy, baseGz, baseMx, baseMy, baseMz, baseAlt, baseTemp;
int initCounter = 0;

void setup() {
  pinMode(IGNITION_PIN, OUTPUT);
  digitalWrite(IGNITION_PIN, LOW);
  pinMode(PWR_INDICATOR, OUTPUT);
  digitalWrite(PWR_INDICATOR, HIGH);
  pinMode(LED_INDICATOR, OUTPUT);
  digitalWrite(LED_INDICATOR, LOW);
  
  Serial.begin(BAUDRATE);

  imu.settings.device.commInterface = IMU_MODE_I2C;
  imu.settings.device.mAddress = LSM9DS1_M;
  imu.settings.device.agAddress = LSM9DS1_AG;

  if (!imu.begin()) {
    Serial.println("Failed to communicate with LSM9DS1.");
    Serial.println("Double-check wiring.");
    while (1)
      ;
  }
  Wire.setClock(400000);

  //Altimeter sensor
  myPressure.setModeAltimeter();
  myPressure.setOversampleRate(5);  // Trade off to get 8Hz sampling frequency of sensors. (7 -> ca 2,5Hz)
  myPressure.enableEventFlags();

  delay(2000);
}

void loop() {

  processReceivedCommands();

  if (state == INITIALIZING) {
    if ((lastPrint + PRINT_SPEED) < millis()) {
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
      lastPrint = millis();
      printIMU();
      printAltitude();
      Serial.println();
    }
    
  } else if (state == STOPPED) {
    if (millis() - blinkInterval > blinkTime) {
      blinkTime = millis();
      toggleLed();
    }
  }
}

void toggleLed() {
  int currVal = digitalRead(LED_INDICATOR);
  if (currVal == LOW) {
    digitalWrite(LED_INDICATOR, HIGH);
  } else {
    digitalWrite(LED_INDICATOR, LOW);
  }
}

void printBaseIMU() {
  Serial.print("{\"ts\":");
  Serial.print(millis());
  Serial.print(",\"baseGx\":");
  Serial.print(baseGx, 2);
  Serial.print(",\"baseGy\":");
  Serial.print(baseGy, 2);
  Serial.print(",\"baseGz\":");
  Serial.print(baseGz, 2);
  Serial.print(",\"baseAx\":");
  Serial.print(baseAx, 2);
  Serial.print(",\"baseAy\":");
  Serial.print(baseAy, 2);
  Serial.print(",\"baseAz\":");
  Serial.print(baseAz, 2);
  Serial.print(",\"baseMx\":");
  Serial.print(baseMx, 2);
  Serial.print(",\"baseMy\":");
  Serial.print(baseMy, 2);
  Serial.print(",\"baseMz\":");
  Serial.print(baseMz, 2);
  Serial.println("}");
}

void printIMU() {
  imu.readGyro();
  imu.readAccel();
  imu.readMag();

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

void printBaseAltitude() {
  Serial.print("{\"ts\":");
  Serial.print(millis());
  Serial.print(",\"baseAlt\":");
  Serial.print(baseAlt, 2);
  Serial.print(",\"baseTemp\":");
  Serial.print(baseTemp, 2);
  Serial.println("}");
}

void printAltitude() {
  float altitude = myPressure.readAltitude();
  float temperature = myPressure.readTemp();
  Serial.print("{\"ts\":");
  Serial.print(millis());
  Serial.print(",\"alt\":");
  Serial.print(altitude, 2);
  Serial.print(",\"temp\":");
  Serial.print(temperature, 2);
  Serial.println("}");
}

void processReceivedCommands() {
  while (Serial.available()) {
    char rcvd = Serial.read();
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
        digitalWrite(IGNITION_PIN, HIGH);
      } else if (strcmp(STOP_CMD, rcvCmdBuf) == 0) {
        state = STOPPED;
        digitalWrite(IGNITION_PIN, LOW);
        printCommandReply(STOP_CMD);
        clearRcvCmdBuf();
      } else if (strcmp(HEARTBEAT_CMD, rcvCmdBuf) == 0) {
        printCommandReply(HEARTBEAT_CMD);
        clearRcvCmdBuf();
      } else {
        for (unsigned int i=0; i<CMD_LEN; i++) {
          rcvCmdBuf[i] = rcvCmdBuf[i+1]; // Shift all one left
        }
      }
    } // if
  } // while
}

void clearRcvCmdBuf() {
  for (unsigned int i=0; i<CMD_LEN; i++) {
    rcvCmdBuf[i] = '\0';
  }
}

void printCommandReply(char* cmd) {
  Serial.print("{\"replyFor\":\"");
  Serial.print(cmd);
  Serial.println("\",\"status\":\"OK\"}");
}

boolean cmdEquals(char* str1, char* str2) {
  for (int i=0; i<4; i++) {
    if (str1[i] != str2[i]) {
      return false;
    }
  }
  return true;
}

