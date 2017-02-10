package no.jan.rocket.comm;

import java.time.LocalDateTime;

/**
 * Created by jasand on 17.01.2017.
 */
public class RocketCommandLogEntry {
    private RocketCommand rocketCommand;
    private LocalDateTime logTime;
    private Boolean successfulWrite;

    public RocketCommandLogEntry() {
    }

    public RocketCommandLogEntry(RocketCommand rocketCommand, LocalDateTime logTime, Boolean successfulWrite) {
        this.rocketCommand = rocketCommand;
        this.logTime = logTime;
        this.successfulWrite = successfulWrite;
    }

    public RocketCommand getRocketCommand() {
        return rocketCommand;
    }

    public void setRocketCommand(RocketCommand rocketCommand) {
        this.rocketCommand = rocketCommand;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public Boolean getSuccessfulWrite() {
        return successfulWrite;
    }

    public void setSuccessfulWrite(Boolean successfulWrite) {
        this.successfulWrite = successfulWrite;
    }
}
