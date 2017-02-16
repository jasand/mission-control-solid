package no.jan.rocket.flight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.jan.rocket.comm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by jasand on 15.01.2017.
 */
public class FlightController {
    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);
    private String portName;
    private RocketSerialComm rocketSerialComm;
    private AltimeterListener altimeterListener;
    private IMUListener imuListener;
    private AltimeterBaselineListener altimeterBaselineListener;
    private IMUBaselineListener imuBaselineListener;
    private CommandResponseListener commandResponseListener;

    public FlightController(String portName) {
        this.portName = portName;
        this.rocketSerialComm = new RocketSerialComm(portName, new RocketDataListenerImpl());
    }

    public static List<String> getAvailableCommPortNames() {
        return RocketSerialComm.getAvailableCommPortNames();
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

    public void setAltimeterBaselineListener(AltimeterBaselineListener altimeterBaselineListener) {
        this.altimeterBaselineListener = altimeterBaselineListener;
    }

    public void setImuBaselineListener(IMUBaselineListener imuBaselineListener) {
        this.imuBaselineListener = imuBaselineListener;
    }

    public boolean initialize() {
        boolean result = rocketSerialComm.sendCommand(RocketCommand.INIT);
        return result;
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
            } else if (rocketDataPacket instanceof IMUBaselineData) {
                ((IMUBaselineData) rocketDataPacket).setLogTime(new Date());
                if (imuBaselineListener != null) {
                    imuBaselineListener.receiveIMUBaselineData((IMUBaselineData) rocketDataPacket);
                }
                logFlightData(rocketDataPacket);
            } else if (rocketDataPacket instanceof AltimeterBaselineData) {
                ((AltimeterBaselineData) rocketDataPacket).setLogTime(new Date());
                if (altimeterBaselineListener != null) {
                    altimeterBaselineListener.receiveAltimeterBaselineData((AltimeterBaselineData) rocketDataPacket);
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
