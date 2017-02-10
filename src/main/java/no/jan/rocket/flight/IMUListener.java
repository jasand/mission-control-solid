package no.jan.rocket.flight;

import no.jan.rocket.comm.IMUData;

/**
 * Created by jasand on 05.02.2017.
 */
public interface IMUListener {
    void receiveIMUData(IMUData imuData);
}
