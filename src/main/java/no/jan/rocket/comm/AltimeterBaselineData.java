package no.jan.rocket.comm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by jasand on 16.02.2017.
 */
public class AltimeterBaselineData implements RocketDataPacket {
    private Long ts;
    private Double baseAlt;
    private Double baseTemp;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date logTime;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Double getBaseAlt() {
        return baseAlt;
    }

    public void setBaseAlt(Double baseAlt) {
        this.baseAlt = baseAlt;
    }

    public Double getBaseTemp() {
        return baseTemp;
    }

    public void setBaseTemp(Double baseTemp) {
        this.baseTemp = baseTemp;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
