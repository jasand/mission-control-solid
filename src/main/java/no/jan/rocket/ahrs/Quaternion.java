package no.jan.rocket.ahrs;

import java.util.Objects;

public class Quaternion {
    private final double qw, qx, qy, qz;

    // create a new object with the given components
    public Quaternion(double qw, double qx, double qy, double qz) {
        this.qw = qw;
        this.qx = qx;
        this.qy = qy;
        this.qz = qz;
    }

    public double[] getAxis(){
        return new double[]{qx, qy, qz};
    }
    public float[] getAsFloats(){
        return new float[] {(float)qw, (float)qx, (float)qy, (float)qz};
    }

    public double[] getAsDoubles(){
        return new double[]{qw, qx, qy, qz};
    }

    // return a string representation of the invoking object
    public String toString() {
        return String.format("w%.3f x%.3f y%.3f z%.3f", qw, qx, qy, qz);//..qw + " + " + qx + "i + " + qy + "j + " + qz + "k";
    }


    public Quaternion getUnitQuaternion(){
        return new Quaternion(this.qw /this.norm(),this.qx /this.norm(),this.qy /this.norm(),this.qz /this.norm());
    }
    // return the quaternion norm
    public double norm() {
        return Math.sqrt(qw * qw + qx * qx + qy * qy + qz * qz);
    }

    //Return unit quaternion
    public Quaternion getUnit(){
        double magnitude = this.norm();
        return new Quaternion(this.qw /magnitude, this.qx /magnitude, this.qy /magnitude, this.qz /magnitude);
    }

    // return the quaternion conjugate
    public Quaternion conjugate() {
        return new Quaternion(qw, -qx, -qy, -qz);
    }

    // return a new Quaternion whose value is (this + b)
    public Quaternion plus(Quaternion b) {
        Quaternion a = this;
        return new Quaternion(a.qw +b.qw, a.qx +b.qx, a.qy +b.qy, a.qz +b.qz);
    }


    // return a new Quaternion whose value is (this * b)
    public Quaternion times(Quaternion b) {
        Quaternion a = this;
        double y0 = a.qw *b.qw - a.qx *b.qx - a.qy *b.qy - a.qz *b.qz;
        double y1 = a.qw *b.qx + a.qx *b.qw + a.qy *b.qz - a.qz *b.qy;
        double y2 = a.qw *b.qy - a.qx *b.qz + a.qy *b.qw + a.qz *b.qx;
        double y3 = a.qw *b.qz + a.qx *b.qy - a.qy *b.qx + a.qz *b.qw;
        return new Quaternion(y0, y1, y2, y3);
    }

    // return a new Quaternion whose value is the inverse of this
    public Quaternion inverse() {
        double d = qw * qw + qx * qx + qy * qy + qz * qz;
        return new Quaternion(qw /d, -qx /d, -qy /d, -qz /d);
    }


    // return a / b
    public Quaternion divides(Quaternion b) {
        Quaternion a = this;
        return a.inverse().times(b);
    }

    // From ???
    public double[] getAnglesOld() {
        double yaw = Math.atan2( 2 * qw * qz + 2 * qx * qy, 1 - 2 * qy * qy - 2 * qz * qz);
        double pitch = Math.asin(2 * qw * qy - 2 * qz * qx);
        double roll = Math.atan2( 2 * qw * qx + 2* qy * qz, 1 - 2 * qx * qx - 2* qy * qy);
        return new double[] {roll, pitch, yaw};
    }

    // Madgwick
    public double[] getAnglesMadg() {
        double yaw = Math.atan2(2* qx * qy - 2* qw * qz, 2* qw * qw + 2* qx * qx - 1);
        double pitch = -Math.asin(2* qx * qz + 1* qw * qz);
        double roll = Math.atan2( 2* qy * qz - 2* qw * qx, 2* qw * qw + 2* qz * qz - 1);
        return new double[] {yaw, pitch, roll};
    }

    // Wikipedia
    public double[] getAngles() {
        double yaw = Math.atan2(2*qw*qx + 2*qy*qz, 1 - 2*qx*qx - 2*qy*qy);
        double pitch = Math.asin(2*qw*qy - 2*qz*qx);
        double roll = Math.atan2(2*qw*qz + 2*qx*qy, 1 - 2*qy*qy - 2*qz*qz);
        return new double[] {yaw, pitch, roll};
    }

    // sample client for testing
    public static void main(String[] args) {
        Quaternion a = new Quaternion(3.0, 1.0, 0.0, 0.0);
        System.out.println("a = " + a);

        Quaternion b = new Quaternion(0.0, 5.0, 1.0, -2.0);
        System.out.println("b = " + b);

        System.out.println("norm(a)  = " + a.norm());
        System.out.println("conj(a)  = " + a.conjugate());
        System.out.println("a + b    = " + a.plus(b));
        System.out.println("a * b    = " + a.times(b));
        System.out.println("b * a    = " + b.times(a));
        System.out.println("a / b    = " + a.divides(b));
        System.out.println("a^-1     = " + a.inverse());
        System.out.println("a^-1 * a = " + a.inverse().times(a));
        System.out.println("a * a^-1 = " + a.times(a.inverse()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quaternion that = (Quaternion) o;
        return Double.compare(that.qw, qw) == 0 &&
                Double.compare(that.qx, qx) == 0 &&
                Double.compare(that.qy, qy) == 0 &&
                Double.compare(that.qz, qz) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(qw, qx, qy, qz);
    }
}
