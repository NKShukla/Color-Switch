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

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    public abstract void move();
    public abstract void stop();

    public abstract Node[] getParts();
}
