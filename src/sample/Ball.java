package sample;

public class Ball extends GameElements implements Animation{
    private final double JUMP_VALUE;
    private boolean fall;
    private double fallSpeed;
    private double jumpSpeed;

    Ball(float[] pos, float[] dim, double fSpeed, double jSpeed) {
        JUMP_VALUE = 10; //reassign it accordingly
    }

    public double getJumpValue() {
        return JUMP_VALUE;
    }

    public double getFallSpeed() {
        return fallSpeed;
    }

    public double getJumpSpeed() {
        return jumpSpeed;
    }

    public boolean isFalling() {
        return fall;
    }

    public void jump() {

    }

    public void setFallSpeed(double xSpeed) {
        this.fallSpeed = xSpeed;
    }

    public void setJumpSpeed(double xSpeed) {
        this.jumpSpeed = xSpeed;
    }

    @Override
    public void disappear() {

    }
}
