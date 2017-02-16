package no.jan.rocket.comm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by jasand on 16.02.2017.
 */
public class IMUBaselineData implements RocketDataPacket {
    private Long ts;
    private Double baseGx;
    private Double baseGy;
    private Double baseGz;
    private Double baseAx;
    private Double baseAy;
    private Double baseAz;
    private Double baseMx;
    private Double baseMy;
    private Double baseMz;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date logTime;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Double getBaseGx() {
        return baseGx;
    }

    public void setBaseGx(Double baseGx) {
        this.baseGx = baseGx;
    }

    public Double getBaseGy() {
        return baseGy;
    }

    public void setBaseGy(Double baseGy) {
        this.baseGy = baseGy;
    }

    public Double getBaseGz() {
        return baseGz;
    }

    public void setBaseGz(Double baseGz) {
        this.baseGz = baseGz;
    }

    public Double getBaseAx() {
        return baseAx;
    }

    public void setBaseAx(Double baseAx) {
        this.baseAx = baseAx;
    }

    public Double getBaseAy() {
        return baseAy;
    }

    public void setBaseAy(Double baseAy) {
        this.baseAy = baseAy;
    }

    public Double getBaseAz() {
        return baseAz;
    }

    public void setBaseAz(Double baseAz) {
        this.baseAz = baseAz;
    }

    public Double getBaseMx() {
        return baseMx;
    }

    public void setBaseMx(Double baseMx) {
        this.baseMx = baseMx;
    }

    public Double getBaseMy() {
        return baseMy;
    }

    public void setBaseMy(Double baseMy) {
        this.baseMy = baseMy;
    }

    public Double getBaseMz() {
        return baseMz;
    }

    public void setBaseMz(Double baseMz) {
        this.baseMz = baseMz;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
