package no.jan.rocket.flight;

import no.jan.rocket.comm.IMUBaselineData;

/**
 * Created by jasand on 16.02.2017.
 */
public interface IMUBaselineListener {
    void receiveIMUBaselineData(IMUBaselineData imuBaselineData);
}
