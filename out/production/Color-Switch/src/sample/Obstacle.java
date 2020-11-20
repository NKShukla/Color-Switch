package sample;

import javafx.scene.paint.Color;

abstract public class Obstacle extends GameElements{
    private double duration;
    private double angle;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    public double getDuration() {
        return duration;
    }

    public double getAngle() {
        return angle;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    abstract public void move();
}
