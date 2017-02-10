package no.jan.rocket.comm;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by jasand on 15.01.2017.
 */
public class IMUData implements RocketDataPacket {
    private Long ts;
    private Double gx;
    private Double gy;
    private Double gz;
    private Double ax;
    private Double ay;
    private Double az;
    private Double mx;
    private Double my;
    private Double mz;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date logTime;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public Double getGx() {
        return gx;
    }

    public void setGx(Double gx) {
        this.gx = gx;
    }

    public Double getGy() {
        return gy;
    }

    public void setGy(Double gy) {
        this.gy = gy;
    }

    public Double getGz() {
        return gz;
    }

    public void setGz(Double gz) {
        this.gz = gz;
    }

    public Double getAx() {
        return ax;
    }

    public void setAx(Double ax) {
        this.ax = ax;
    }

    public Double getAy() {
        return ay;
    }

    public void setAy(Double ay) {
        this.ay = ay;
    }

    public Double getAz() {
        return az;
    }

    public void setAz(Double az) {
        this.az = az;
    }

    public Double getMx() {
        return mx;
    }

    public void setMx(Double mx) {
        this.mx = mx;
    }

    public Double getMy() {
        return my;
    }

    public void setMy(Double my) {
        this.my = my;
    }

    public Double getMz() {
        return mz;
    }

    public void setMz(Double mz) {
        this.mz = mz;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}
