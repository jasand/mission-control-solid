package no.jan.rocket.comm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jasand on 15.01.2017.
 */
public class RocketCommandReply implements RocketDataPacket {
    private String replyFor;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date logTime;

    public String getReplyFor() {
        return replyFor;
    }

    public void setReplyFor(String replyFor) {
        this.replyFor = replyFor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
