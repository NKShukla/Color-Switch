package sample;

import javafx.scene.Node;

abstract public class Obstacle extends GameElements {
    private double duration;
    private double angle;

    public double getDuration() {
        return duration;
    }

    public double getAngle() {
        return angle;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    public Obstacle clone() {
        return (Obstacle)super.clone();
    }

    public abstract void move();

    public abstract Node[] getParts();
}
