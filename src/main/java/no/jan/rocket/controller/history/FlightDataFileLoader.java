package no.jan.rocket.controller.history;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import no.jan.rocket.comm.*;

import java.io.*;

/**
 * Created by jasand on 26.02.2017.
 */
public class FlightDataFileLoader {

    public static FlightDataWrapper load(File file) throws IOException {
        FlightDataWrapper flightDataWrapper = new FlightDataWrapper();
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            try {
                IMUData imuData = objectMapper.readValue(inputLine, IMUData.class);
                flightDataWrapper.getImuDataList().add(imuData);
                continue;
            } catch (JsonProcessingException jpEx) {}
            try {
                AltimeterData altimeterData = objectMapper.readValue(inputLine, AltimeterData.class);
                flightDataWrapper.getAltimeterDataList().add(altimeterData);
                continue;
            } catch (JsonProcessingException jpEx) {}
            try {
                RocketCommandReply commandReply = objectMapper.readValue(inputLine, RocketCommandReply.class);
                flightDataWrapper.getCommandReplies().add(commandReply);
                continue;
            } catch (JsonProcessingException jpEx) {}
            try {
                AltimeterBaselineData altimeterBaselineData = objectMapper.readValue(inputLine, AltimeterBaselineData.class);
                flightDataWrapper.setAltimeterBaselineData(altimeterBaselineData);
                continue;
            } catch (JsonProcessingException jpEx) {}
            try {
                IMUBaselineData imuBaselineData = objectMapper.readValue(inputLine, IMUBaselineData.class);
                flightDataWrapper.setImuBaselineData(imuBaselineData);
                continue;
            } catch (JsonProcessingException jpEx) {}
            System.out.println("Could not parse line: " + inputLine);
        }
        return flightDataWrapper;
    }

}
