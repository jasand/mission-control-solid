package no.jan.rocket.flight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.jan.rocket.comm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jasand on 15.01.2017.
 */
public class FlightController {
    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);
    private String portName;
    private RocketSerialComm rocketSerialComm;
    private AltimeterListener altimeterListener;
    private IMUListener imuListener;
    private CommandResponseListener commandResponseListener;

    public FlightController(String portName) {
        this.portName = portName;
        this.rocketSerialComm = new RocketSerialComm(portName, new RocketDataListenerImpl());
    }

    public void setCommandResponseListener(CommandResponseListener commandResponseListener) {
        this.commandResponseListener = commandResponseListener;
    }

    public void setImuListener(IMUListener imuListener) {
        this.imuListener = imuListener;
    }

    public void setAltimeterListener(AltimeterListener altimeterListener) {
        this.altimeterListener = altimeterListener;
    }

    public boolean start() {
        boolean result = rocketSerialComm.sendCommand(RocketCommand.STRT);
        //logFlightData(new RocketCommandLogEntry(RocketCommand.STRT, LocalDateTime.now(), result));
        return result;
    }

    public boolean stop() {
        boolean result = rocketSerialComm.sendCommand(RocketCommand.STOP);
        //logFlightData(new RocketCommandLogEntry(RocketCommand.STOP, LocalDateTime.now(), result));
        return result;
    }

    public boolean heartbeat() {
        boolean result = rocketSerialComm.sendCommand(RocketCommand.HRTB);
        //logFlightData(new RocketCommandLogEntry(RocketCommand.HRTB, LocalDateTime.now(), result));
        return result;
    }

    public boolean abort() {
        boolean result = rocketSerialComm.sendCommand(RocketCommand.ABRT);
        //logFlightData(new RocketCommandLogEntry(RocketCommand.ABRT, LocalDateTime.now(), result));
        return result;
    }

    public boolean close() {
        return rocketSerialComm.close();
    }

    public class RocketDataListenerImpl implements RocketDataListener {

        public void receiveData(RocketDataPacket rocketDataPacket) {
            ObjectMapper objectMapper = new ObjectMapper();

            if (rocketDataPacket instanceof RocketCommandReply) {
                ((RocketCommandReply) rocketDataPacket).setLogTime(new Date());
                if (commandResponseListener != null) {
                    commandResponseListener.receiveCommandResponse((RocketCommandReply) rocketDataPacket);
                }
                logFlightData(rocketDataPacket);
            } else if (rocketDataPacket instanceof IMUData) {
                ((IMUData) rocketDataPacket).setLogTime(new Date());
                if (imuListener != null) {
                    imuListener.receiveIMUData((IMUData) rocketDataPacket);
                }
                logFlightData(rocketDataPacket);
            } else if (rocketDataPacket instanceof AltimeterData) {
                ((AltimeterData) rocketDataPacket).setLogTime(new Date());
                if (altimeterListener != null) {
                    altimeterListener.receiveAltimeterData((AltimeterData) rocketDataPacket);
                }
                logFlightData(rocketDataPacket);
            }
        }
    }

    private void logCommandSent(Object data) {
        try {
            LOG.info("COMMAND: {}", new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not serialize object.", e);
        }
    }

    private void logFlightData(Object data) {
        try {
            LOG.info("FLIGHT DATA: {}", new ObjectMapper().writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not serialize object.", e);
        }
    }
}
