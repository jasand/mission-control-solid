package no.jan.rocket.ahrs;

import org.junit.Test;

import static org.junit.Assert.*;

public class MadgwickAHRSTest {

    @Test
    public void shouldReturnUnityWhenNoGyroAndFlatSensor() {
        MadgwickAHRS madgwickAHRS = new MadgwickAHRS(100);
        madgwickAHRS.updateIMU(0,0,0,0,0,1);
        Quaternion q = madgwickAHRS.getQuaternion();
        assertEquals(new Quaternion(1,0,0,0), q);
    }

}