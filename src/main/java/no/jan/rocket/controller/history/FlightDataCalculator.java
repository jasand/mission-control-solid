package no.jan.rocket.controller.history;

import no.jan.rocket.comm.AltimeterBaselineData;
import no.jan.rocket.comm.AltimeterData;
import no.jan.rocket.comm.IMUBaselineData;
import no.jan.rocket.comm.IMUData;

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
}
