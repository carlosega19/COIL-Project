package com.main.templates.MathManagement;

public class Vec2d {
    public double x;
    public double y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] toPolar() {
        double mag = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double theta = Math.atan2(y, x);
        return new double[] {mag, theta};
    }

    public Vec2d setMagnitude(double mag) {
        double[] polar = toPolar();
        return new Vec2d(mag * Math.cos(polar[1]), mag * Math.sin(polar[1]));
    }

    public static Vec2d fromPolar(double deg, double mag) {
        return new Vec2d(mag * Math.cos(deg), mag * Math.sin(deg));
    }

    public void add(Vec2d other) {
        x += other.x;
        y += other.y;
    }

    public static Vec2d angleBetween(Vec2d a, Vec2d b) {
        double dy = b.y - a.y;
        double dx = b.x - a.x;
        double theta = Math.atan2(dy, dx);
        double mag = 0.1;
        return fromPolar(theta, mag);
    }
}
