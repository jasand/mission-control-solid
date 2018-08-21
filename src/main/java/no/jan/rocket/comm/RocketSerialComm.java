package no.jan.rocket.comm;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasand on 15.01.2017.
 */
public class RocketSerialComm {
    private static final Logger LOG = LoggerFactory.getLogger(RocketSerialComm.class);
    private SerialPort comPort;
    private SerialReceiverParser serialReceiverParser;
    private RocketDataListener rocketDataListener;

//    public final static int BAUDRATE = 9600;
//    public final static int BAUDRATE = 19200;
//    public final static int BAUDRATE = 38400;
    public final static int BAUDRATE = 57600;
//    public final static int BAUDRATE = 115200;

    public static List<String> getAvailableCommPortNames() {
        SerialPort ports[] = SerialPort.getCommPorts();
        List<String> portNames = new ArrayList<String>();
        for (SerialPort port : ports) {
            String name = port.getSystemPortName();
            if (!name.startsWith("tty")) {
                portNames.add(name);
            }
        }
        return portNames;
    }

    public RocketSerialComm(String portName, RocketDataListener rocketDataListener) {
        super();
        this.rocketDataListener = rocketDataListener;
        comPort = SerialPort.getCommPort(portName);
        comPort.setBaudRate(BAUDRATE);
        serialReceiverParser = new SerialReceiverParser();
        boolean open = comPort.openPort();

        comPort.addDataListener(new RocketSerialPortDataListener());
    }

    public boolean sendCommand(RocketCommand cmd) {
        int sent = comPort.writeBytes(cmd.name().getBytes(), 4);
        if (sent == 4) {
            LOG.info("Sent command: " + cmd.name());
            return true;
        } else {
            LOG.error("Unable to send: " + cmd.name() + ". Write bytes returned: " + sent);
            return false;
        }
    }

    public boolean close() {
        return comPort.closePort();
    }

    public class RocketSerialPortDataListener implements SerialPortDataListener {

        @Override
        public int getListeningEvents() {
            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                LOG.warn("UNKNOWN EVENT: " + serialPortEvent.getEventType());
                return;
            }
            byte[] newData = new byte[comPort.bytesAvailable()];
            int numRead = comPort.readBytes(newData, newData.length);
            String received = new String(newData);

//            LOG.info("RECEIVED BYTES STR: " + received);
            List<RocketDataPacket> dataPackets = serialReceiverParser.receiveAndScanForDataPackets(received);
            for (RocketDataPacket dataPacket : dataPackets) {
                rocketDataListener.receiveData(dataPacket);
            }
        }


    }
}
