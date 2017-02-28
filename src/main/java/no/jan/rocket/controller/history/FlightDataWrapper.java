package no.jan.rocket.controller.history;

import no.jan.rocket.comm.*;

import java.util.ArrayList;
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

    private Double maxAlt;
    private Double maxSpeed;
    private Double minSpeed;

    private Double maxAcc;
    private Double maxAccX;
    private Double maxAccY;
    private Double maxAccZ;

    private Double minAcc;
    private Double minAccX;
    private Double minAccY;
    private Double minAccZ;

    public FlightDataWrapper() {
        commandReplies = new ArrayList<>();
        altimeterDataList = new ArrayList<>();
        imuDataList = new ArrayList<>();
    }

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

    public Double getMaxAlt() {
        return maxAlt;
    }

    public void setMaxAlt(Double maxAlt) {
        this.maxAlt = maxAlt;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Double getMaxAcc() {
        return maxAcc;
    }

    public void setMaxAcc(Double maxAcc) {
        this.maxAcc = maxAcc;
    }

    public Double getMaxAccX() {
        return maxAccX;
    }

    public void setMaxAccX(Double maxAccX) {
        this.maxAccX = maxAccX;
    }

    public Double getMaxAccY() {
        return maxAccY;
    }

    public void setMaxAccY(Double maxAccY) {
        this.maxAccY = maxAccY;
    }

    public Double getMaxAccZ() {
        return maxAccZ;
    }

    public void setMaxAccZ(Double maxAccZ) {
        this.maxAccZ = maxAccZ;
    }

    public Double getMinAcc() {
        return minAcc;
    }

    public void setMinAcc(Double minAcc) {
        this.minAcc = minAcc;
    }

    public Double getMinAccX() {
        return minAccX;
    }

    public void setMinAccX(Double minAccX) {
        this.minAccX = minAccX;
    }

    public Double getMinAccY() {
        return minAccY;
    }

    public void setMinAccY(Double minAccY) {
        this.minAccY = minAccY;
    }

    public Double getMinAccZ() {
        return minAccZ;
    }

    public void setMinAccZ(Double minAccZ) {
        this.minAccZ = minAccZ;
    }
}
