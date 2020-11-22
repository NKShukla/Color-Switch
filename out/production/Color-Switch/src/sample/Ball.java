package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Ball extends GameElements implements Animation{
    private final double JUMP_VALUE;
    private boolean fall;
    private double fallSpeed;
    private double jumpSpeed;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private Circle ball;

    Ball(float[] pos, float[] dim, double fSpeed, double jSpeed) {
        JUMP_VALUE = 10; //reassign it accordingly

        ball = new Circle(pos[0], pos[1], dim[0]);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(ball.centerYProperty(), ball.getCenterY(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(ball.centerYProperty(), ball.getCenterY()+1, Interpolator.LINEAR))
        );
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
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

    public Circle getBall() {
        return ball;
    }

    public boolean isFalling() {
        return fall;
    }

    public void jump() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(ball.centerYProperty(), ball.getCenterY(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(ball.centerYProperty(), ball.getCenterY()-2, Interpolator.LINEAR))
        );
        timeline.setCycleCount(1);
        timeline.play();
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
