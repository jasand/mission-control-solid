package no.jan.rocket;


import no.jan.rocket.comm.RocketSerialComm;
import no.jan.rocket.flight.FlightController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by jasand on 15.01.2017.
 */
public class Testing {
    private final static Logger LOG = LoggerFactory.getLogger(Testing.class);

    public static void main(String[] args) {
        LOG.info("TESTING");
        List<String> serialPortNames = RocketSerialComm.getAvailableCommPortNames();
        System.out.println("Choose a serial port:");
        for (int i=0; i<serialPortNames.size(); i++) {
            System.out.println(i+1 + " - " + serialPortNames.get(i));
        }
        System.out.println("0 - Quit");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if (choice == 0) System.exit(0);
        FlightController flightController = new FlightController(serialPortNames.get(choice-1));

        while (true) {
            System.out.println("\nCommands:");
            System.out.println("1 - Start");
            System.out.println("2 - Hartbeat");
            System.out.println("3 - Stop");
            System.out.println("0 - Quit");

            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            for (Thread thread : threadSet) {
                System.out.println("  -> " + thread.getName() + " - " + thread.getState().name());
            }

            int command = scan.nextInt();
            if (command == 0) {
                flightController.close();
                System.exit(0);
            } else if (command == 1) {
                flightController.start();
            } else if (command == 2) {
                flightController.heartbeat();
            } else if (command == 3) {
                flightController.stop();
            }
        }
    }

//    public static void main(String[] args) {
//        System.out.println("Jadda!!");
//        SerialPort ports[] = SerialPort.getCommPorts();
//        for (SerialPort port : ports) {
//            System.out.println("Name: " + port.getSystemPortName() + " - " + port.getDescriptivePortName());
//        }
//
//        byte[] cmd = {'S', 'T', 'R', 'T'};
//        SerialPort comPort = SerialPort.getCommPort("cu.usbserial-DA013LW1");
//        boolean open = comPort.openPort();
//        if (open) {
//            System.out.println("Open success");
//        } else {
//            System.out.println("Open fail");
//            return;
//        }
//        int sent = comPort.writeBytes(cmd, 4);
//        System.out.println("Sent " + sent + " bytes");
//
//        comPort.openPort();
//        try {
//            while (true)
//            {
//                while (comPort.bytesAvailable() == 0)
//                    Thread.sleep(20);
//
//                byte[] readBuffer = new byte[comPort.bytesAvailable()];
//                int numRead = comPort.readBytes(readBuffer, readBuffer.length);
////                System.out.println("Read " + numRead + " bytes.");
//                String read = new String(readBuffer);
//                System.out.print(read);
//            }
//        } catch (Exception e) { e.printStackTrace(); }
//        comPort.closePort();
//    }

}
