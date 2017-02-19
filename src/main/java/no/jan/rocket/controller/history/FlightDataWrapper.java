package no.jan.rocket.controller.history;

import no.jan.rocket.comm.*;

import java.util.List;

/**
 * Created by jasand on 19.02.2017.
 */
public class FlightDataWrapper {
    private List<RocketCommandReply> commandReplies;
    private AltimeterBaselineData altimeterBaselineData;
    private IMUBaselineData imuBaselineData;
    private List<AltimeterData> altimeterDataList;
    private List<IMUData> imuDataList;

    public List<RocketCommandReply> getCommandReplies() {
        return commandReplies;
    }

    public void setCommandReplies(List<RocketCommandReply> commandReplies) {
        this.commandReplies = commandReplies;
    }

    public AltimeterBaselineData getAltimeterBaselineData() {
        return altimeterBaselineData;
    }

    public void setAltimeterBaselineData(AltimeterBaselineData altimeterBaselineData) {
        this.altimeterBaselineData = altimeterBaselineData;
    }

    public IMUBaselineData getImuBaselineData() {
        return imuBaselineData;
    }

    public void setImuBaselineData(IMUBaselineData imuBaselineData) {
        this.imuBaselineData = imuBaselineData;
    }

    public List<AltimeterData> getAltimeterDataList() {
        return altimeterDataList;
    }

    public void setAltimeterDataList(List<AltimeterData> altimeterDataList) {
        this.altimeterDataList = altimeterDataList;
    }

    public List<IMUData> getImuDataList() {
        return imuDataList;
    }

    public void setImuDataList(List<IMUData> imuDataList) {
        this.imuDataList = imuDataList;
    }
}
