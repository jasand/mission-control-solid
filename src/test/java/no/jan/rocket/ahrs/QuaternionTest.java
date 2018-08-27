package no.jan.rocket.ahrs;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuaternionTest {

    @Test
    public void shouldReturnZeroAnglesForIdentity() {
        Quaternion q = new Quaternion(1,0,0,0);
        double[] angles = q.getAngles();
        assertEquals(0.0, angles[0], 0.0001);
        assertEquals(0.0, angles[1], 0.0001);
        assertEquals(0.0, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYaw1() {
        Quaternion q = new Quaternion(1, 1, 1, 1);
        q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(1.5707963, angles[0], 0.0001);
        assertEquals(0, angles[1], 0.0001);
        assertEquals(1.5707963, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYaw2() {
        Quaternion q = new Quaternion(0.5122, 0.4646, -0.5114, -0.5101);
        //q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(1.5255538, angles[0], 0.0001);
        assertEquals(-0.0499173, angles[1], 0.0001);
        assertEquals(-1.6143973, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYaw3() {
        Quaternion q = new Quaternion(0.7071, 0.7071, 0, 0);
        q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(Math.PI/2, angles[0], 0.0001);
        assertEquals(0, angles[1], 0.0001);
        assertEquals(0, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYaw4() {
        Quaternion q = new Quaternion(1, 1, 0, 0);
        q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(Math.PI/2, angles[0], 0.0001);
        assertEquals(0, angles[1], 0.0001);
        assertEquals(0, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYaw5() {
        Quaternion q = new Quaternion(0.3, 0.2, -0.3, -0.4);
        q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(1.2490458, angles[0], 0.0001);
        assertEquals(-0.0526559, angles[1], 0.0001);
        assertEquals(-1.8925469, angles[2], 0.0001);
    }

    @Test
    public void shouldReturnRollPitchYawAfterMultipleNormalizations() {
        Quaternion q = new Quaternion(0.3, 0.2, -0.3, -0.4);
        q = q.getUnit();
        q = q.getUnit();
        q = q.getUnit();
        q = q.getUnit();
        double[] angles = q.getAngles();
        assertEquals(1.2490458, angles[0], 0.0001);
        assertEquals(-0.0526559, angles[1], 0.0001);
        assertEquals(-1.8925469, angles[2], 0.0001);
    }

}