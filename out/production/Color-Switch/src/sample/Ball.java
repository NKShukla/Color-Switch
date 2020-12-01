package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Random;

public class Ball extends GameElements implements Animation{
    private double JUMP_VALUE;
    private boolean fall;
    private double fallSpeed;
    private double jumpSpeed;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private Circle ball;

    Ball(float[] pos, float[] dim, double fSpeed, double jSpeed) {
        JUMP_VALUE = 10; //reassign it accordingly

        ball = new Circle(pos[0], pos[1], dim[0]/2);

        Random rand = new Random();
        int index = rand.nextInt(4);

        ball.setFill(colors[index]);

//        Timeline tl = new Timeline();
//        tl.setCycleCount(javafx.animation.Animation.INDEFINITE);
//        KeyFrame moveBall = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//
//                    public void handle(ActionEvent event) {
//
//                        double xMin = ball.getBoundsInParent().getMinX();
//                        double yMin = ball.getBoundsInParent().getMinY();
//                        double xMax = ball.getBoundsInParent().getMaxX();
//                        double yMax = ball.getBoundsInParent().getMaxY();
//
////                        if (xMin < 0 || xMax > scene.getWidth()) {
////                            dx = dx * -1;
////                        }
////                        if (yMin < 0 || yMax > scene.getHeight()) {
////                            dy = dy * -1;
////                        }
//
//                        ball.setTranslateY(ball.getTranslateY() + 20);
//                    }
//                });
//
//        tl.getKeyFrames().add(moveBall);
//        tl.play();
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

    @Override
    public void disappear() {

    }
}
