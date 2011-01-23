package org.lumine.util.math;

import com.google.common.base.Objects;

public final class Vector3D {
    public static Vector3D at(final double x, final double y, final double z) {
        return new Vector3D(x, y, z);
    }

    private final double x, y, z;

    private Vector3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D crossP(final double x, final double y, final double z) {
        return Vector3D.at(this.y * z - this.z * y, this.z * x - this.x * z,
                this.x * y - y * x);
    }

    public Vector3D crossP(final Vector3D that) {
        return Vector3D.at(this.y * that.z - this.z * that.y, this.z * that.x
                - this.x * that.z, this.x * that.y - this.y * that.x);
    }

    public double dotP(final double x, final double y, final double z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public double dotP(final Vector3D that) {
        return this.x * that.x + this.y * that.y + this.z * that.z;
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Vector3D minus(final double x, final double y, final double z) {
        return Vector3D.at(this.x - x, this.y - y, this.z - z);
    }

    public Vector3D minus(final Vector3D that) {
        return Vector3D.at(this.x - that.x, this.y - that.y, this.z - that.z);
    }

    public Vector3D normalized() {
        final double length = this.length();
        return Vector3D.at(this.x / length, this.y / length, this.z / length);
    }

    public Vector3D plus(final double x, final double y, final double z) {
        return Vector3D.at(this.x + x, this.y + y, this.z + z);
    }

    public Vector3D plus(final Vector3D that) {
        return Vector3D.at(this.x + that.x, this.y + that.y, this.z + that.z);
    }

    public Vector3D times(final double s) {
        return Vector3D.at(this.x * s, this.y * s, this.z * s);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("x", this.x).add("y", this.y)
                .add("z", this.z).toString();
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }
}
