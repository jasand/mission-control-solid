package no.jan.rocket.flight;

import no.jan.rocket.comm.AltimeterData;

/**
 * Created by jasand on 05.02.2017.
 */
public interface AltimeterListener {
    void receiveAltimeterData(AltimeterData altimeterData);
}
