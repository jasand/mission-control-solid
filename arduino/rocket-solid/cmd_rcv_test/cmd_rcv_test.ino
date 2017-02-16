
#include <SoftwareSerial.h>

char INIT_CMD[5] = "INIT";
char START_CMD[5] = "STRT";
char STOP_CMD[5] = "STOP";
char HEARTBEAT_CMD[5] = "HRTB";

SoftwareSerial XBee(2, 3); // RX, TX

char rcvCmdBuf[5];
int CMD_LEN = 4;

void setup() {
  Serial.begin(9600);
  XBee.begin(9600);
}

void loop() {
  processReceivedCommand();

  delay(500);
  
}

void processReceivedCommand() {
  while (XBee.available()) {
    char rcvd = XBee.read();
    int rcvBufLen = strlen(rcvCmdBuf);
    rcvCmdBuf[rcvBufLen] = rcvd;
    if (strlen(rcvCmdBuf) >= CMD_LEN) {
      if (strcmp(INIT_CMD, rcvCmdBuf) == 0) {
        Serial.println("Found INIT_CMD");  // Set state and reply in main prog
        clearRcvCmdBuf();
      } else if (strcmp(START_CMD, rcvCmdBuf) == 0) {
        Serial.println("Found START_CMD");
        clearRcvCmdBuf();
      } else if (strcmp(STOP_CMD, rcvCmdBuf) == 0) {
        Serial.println("Found STOP_CMD");
        clearRcvCmdBuf();
      } else if (strcmp(HEARTBEAT_CMD, rcvCmdBuf) == 0) {
        Serial.println("Found HEARTBEAT_CMD");
        clearRcvCmdBuf();
      } else {
        for (int i=0; i<CMD_LEN; i++) {
          rcvCmdBuf[i] = rcvCmdBuf[i+1]; // Shift all one left
        }
        Serial.println("Shifted left");
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


