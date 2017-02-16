package no.jan.rocket.comm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasand on 16.01.2017.
 */
class SerialReceiverParser {
    private static Logger LOG = LoggerFactory.getLogger(SerialReceiverParser.class);

    private String receiveBuffer = "";

    // Super simple parsing for now, no arrays, no nested objects
    public List<RocketDataPacket> receiveAndScanForDataPackets(String received) {
        receiveBuffer += received;
        // TODO: Rewrite parsing and make more readable
//        LOG.info("RCV BUF: " + receiveBuffer);

        List<RocketDataPacket> dataPackets = new ArrayList<RocketDataPacket>();
        while (true) {
            if (receiveBuffer.length() == 0) {
                break;
            }
            if (receiveBuffer.charAt(0) != '{') {
                int index = receiveBuffer.indexOf('{');
                if (index > 0) {
                    receiveBuffer = receiveBuffer.substring(index);
                } else {
                    receiveBuffer = "";
                    break;
                }
            }
            int endIdx = 0;
            for (int i=1; i<receiveBuffer.length(); i++) {
                if (receiveBuffer.charAt(i) == '}') {
                    endIdx = i;
                    break;
                }
                if (receiveBuffer.charAt(i) == '{') {
                    // First part of buffer is incomplete data, remove and start over again.
                    receiveBuffer = receiveBuffer.substring(i);
                    endIdx=-1;
                    break;
                }
                if (i+1 == receiveBuffer.length()) {
                    // if not found end, not all received, return any packets and wait for more data
                    return dataPackets;
                }
            }
            if (endIdx > 0) {
                String dataPacketStr = receiveBuffer.substring(0, endIdx + 1);
                receiveBuffer = receiveBuffer.substring(endIdx + 1);
                dataPackets.add(createDataPacket(dataPacketStr));
            } else if (endIdx == 0) {
                break;
            }
        }
        return dataPackets;
    }

    private RocketDataPacket createDataPacket(String dataPacketStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            IMUData telemetryData = objectMapper.readValue(dataPacketStr, IMUData.class);
            return telemetryData;
        } catch (IOException e) {
            // Swallow, try RocketCommandReply too before giving up
        }
        try {
            AltimeterData telemetryData = objectMapper.readValue(dataPacketStr, AltimeterData.class);
            return telemetryData;
        } catch (IOException e) {
            // Swallow, try RocketCommandReply too before giving up
        }
        try {
            IMUBaselineData telemetryData = objectMapper.readValue(dataPacketStr, IMUBaselineData.class);
            return telemetryData;
        } catch (IOException e) {
            // Swallow, try RocketCommandReply too before giving up
        }
        try {
            AltimeterBaselineData telemetryData = objectMapper.readValue(dataPacketStr, AltimeterBaselineData.class);
            return telemetryData;
        } catch (IOException e) {
            // Swallow, try RocketCommandReply too before giving up
        }
        try {
            RocketCommandReply commandReply = objectMapper.readValue(dataPacketStr, RocketCommandReply.class);
            return commandReply;
        } catch (IOException e) {
            // do not swallow this...
            throw new RuntimeException("Error parsing received data: " + dataPacketStr, e);
        }
    }
}
