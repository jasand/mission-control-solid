package no.jan.rocket.controller.history;

import no.jan.rocket.comm.AltimeterBaselineData;
import no.jan.rocket.comm.AltimeterData;
import no.jan.rocket.comm.IMUBaselineData;
import no.jan.rocket.comm.IMUData;

import java.text.DecimalFormat;

/**
 * Created by jasand on 26.02.2017.
 */
public class FlightDataCalculator {

    public static FlightDataWrapper calculate(FlightDataWrapper flightDataWrapper) {
        AltimeterBaselineData altBaselineData = flightDataWrapper.getAltimeterBaselineData();
        AltimeterData prevAltData = null;
        for (AltimeterData altimeterData : flightDataWrapper.getAltimeterDataList()) {
            if (flightDataWrapper.getMaxAlt() == null
                    || altimeterData.getAlt() - altBaselineData.getBaseAlt() > flightDataWrapper.getMaxAlt()) {
                flightDataWrapper.setMaxAlt(altimeterData.getAlt() - altBaselineData.getBaseAlt());
            }
            if (prevAltData != null) {
                Double speed = (altimeterData.getAlt() - prevAltData.getAlt()) / ((altimeterData.getTs() - prevAltData.getTs()) / 1000.0);
                if (flightDataWrapper.getMaxSpeed() == null
                        || speed > flightDataWrapper.getMaxSpeed()) {
                    flightDataWrapper.setMaxSpeed(speed);
                }
                if (flightDataWrapper.getMinSpeed() == null
                        || speed < flightDataWrapper.getMinSpeed()) {
                    flightDataWrapper.setMinSpeed(speed);
                }
            }
            prevAltData = altimeterData;
        }
        IMUBaselineData imuBaselineData = flightDataWrapper.getImuBaselineData();
        for (IMUData imuData : flightDataWrapper.getImuDataList()) {
            Double accel = Math.sqrt(imuData.getAx()*imuData.getAx()
                    + imuData.getAy()*imuData.getAy() + imuData.getAz()*imuData.getAz());
            if (flightDataWrapper.getMaxAcc() == null || accel > flightDataWrapper.getMaxAcc()) {
                flightDataWrapper.setMaxAcc(accel);
                flightDataWrapper.setMaxAccX(imuData.getAx());
                flightDataWrapper.setMaxAccY(imuData.getAy());
                flightDataWrapper.setMaxAccZ(imuData.getAz());
            }
            if (flightDataWrapper.getMinAcc() == null || accel < flightDataWrapper.getMinAcc()) {
                flightDataWrapper.setMinAcc(accel);
                flightDataWrapper.setMinAccX(imuData.getAx());
                flightDataWrapper.setMinAccY(imuData.getAy());
                flightDataWrapper.setMinAccZ(imuData.getAz());
            }
        }
        return flightDataWrapper;
    }

    public static Double calculatePitch(IMUData imuData) {
        Double pitch = Math.atan2(imuData.getAx(), Math.sqrt(imuData.getAy() * imuData.getAy() + imuData.getAz() * imuData.getAz()));
        pitch *= 180.0 / Math.PI;
        return pitch;
    }

    public static Double calculateRoll(IMUData imuData) {
        Double roll = Math.atan2(imuData.getAy(), imuData.getAz());
        roll *= 180.0 / Math.PI;
        return roll;
    }

    public static Double calculateAzimuthOrig(IMUData imuData) {
        Double DECLINATION = 3.16;
        Double azimuth;
        if (imuData.getMy() == 0) {
            azimuth = (imuData.getMx() < 0) ? Math.PI : 0;
        } else {
            azimuth = Math.atan2(imuData.getMx(), imuData.getMy());
        }
        azimuth -= DECLINATION * Math.PI / 180;

        if (azimuth > Math.PI) azimuth -= (2 * Math.PI);
        else if (azimuth < -Math.PI) azimuth += (2 * Math.PI);
        else if (azimuth < 0) azimuth += 2 * Math.PI;
        azimuth *= 180.0 / Math.PI;
        return azimuth;
    }

    public static Double calculateAzimuth(IMUData imuData) {
        Double DECLINATION = 3.16;
        Double azimuth;
        if (-imuData.getMy() == 0) {
            azimuth = (-imuData.getMx() < 0) ? Math.PI : 0;
        } else {
            azimuth = Math.atan2(-imuData.getMx(), -imuData.getMy());
        }
        azimuth -= DECLINATION * Math.PI / 180;

        if (azimuth > Math.PI) azimuth -= (2 * Math.PI);
        else if (azimuth < -Math.PI) azimuth += (2 * Math.PI);
        else if (azimuth < 0) azimuth += 2 * Math.PI;
        azimuth *= 180.0 / Math.PI;
        return azimuth;
    }
}
