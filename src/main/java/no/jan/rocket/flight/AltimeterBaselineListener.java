package no.jan.rocket.flight;

import no.jan.rocket.comm.AltimeterBaselineData;

/**
 * Created by jasand on 16.02.2017.
 */
public interface AltimeterBaselineListener {
    void receiveAltimeterBaselineData(AltimeterBaselineData altimeterBaselineData);
}
