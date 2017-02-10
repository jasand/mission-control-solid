package no.jan.rocket.flight;

import java.time.LocalDateTime;

/**
 * Created by jasand on 15.01.2017.
 */
public class FlightData {
    private String flightName;
    private String rocketType;
    private String motor;
    private String comments;
    private LocalDateTime logTime;

    public FlightData(String flightName, String rocketType, String motor, String comments) {
        this.flightName = flightName;
        this.rocketType = rocketType;
        this.motor = motor;
        this.comments = comments;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getRocketType() {
        return rocketType;
    }

    public void setRocketType(String rocketType) {
        this.rocketType = rocketType;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }
}
