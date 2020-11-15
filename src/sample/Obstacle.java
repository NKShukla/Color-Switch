package sample;

abstract public class Obstacle extends GameElements{
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

    abstract public void move();
}
