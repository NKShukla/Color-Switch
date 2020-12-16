package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Ball extends GameElements implements Animation{
    private double JUMP_VALUE;
    private boolean fall;
    private double fallSpeed;
    private double jumpSpeed;
    private transient Circle ball;

    Ball(float[] pos, float[] dim, double fSpeed, double jSpeed) {
        setPosition(pos);
        setDimensions(dim);
        JUMP_VALUE = 10; //reassign it accordingly

        ball = new Circle(pos[0], pos[1], dim[0]/2);
        Random rand = new Random();
        int index = rand.nextInt(4);
        ball.setFill(GameScreen.colors[index]);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        int colorIndex = 3;
        if(ball.getStroke() == Color.RED)
            colorIndex = 0;
        else if(ball.getStroke() == Color.YELLOW)
            colorIndex = 1;
        else if(ball.getStroke() == Color.GREEN)
            colorIndex = 2;
        oos.writeObject(colorIndex);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        int colorIndex = (int)ois.readObject();
        float[] pos = getPosition();
        float[] dim = getDimensions();
        ball = new Circle(pos[0], pos[1], dim[0]/2);
        ball.setFill(GameScreen.colors[colorIndex]);
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
                new KeyFrame(Duration.seconds(1), new KeyValue(ball.centerYProperty(), ball.getCenterY()+20, Interpolator.LINEAR))
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

    public double getCenterY(){
        return ball.getCenterY();
    }
    public void setCenterY(double y){
        ball.setCenterY(y);
    }

    public Ball clone() {
        return (Ball) super.clone();
    }

    @Override
    public void disappear() {

    }
}
