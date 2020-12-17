package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ColorSwitcher extends GameElements implements Animation{
    private transient Circle ball;
    private boolean intersected;
    private transient Timeline CStimer;
    int i=0;

    ColorSwitcher(float[] pos, float[] dim) {
        setPosition(pos);
        setDimensions(dim);
        intersected = false;
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.WHITE);
        CStimer = new Timeline();
        CStimer.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), event -> {
            ball.setFill(GameScreen.colors[i++%4]);
        });
        CStimer.getKeyFrames().add(moveBall);
        CStimer.play();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        float[] pos = getPosition();
        float[] dim = getDimensions();
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.WHITE);
        if(intersected)
            disappear();
        CStimer = new Timeline();
        CStimer.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), event -> {
            ball.setFill(GameScreen.colors[i++%4]);
        });
        CStimer.getKeyFrames().add(moveBall);
        CStimer.play();
    }

    public Circle getBall() {
        return ball;
    }

    public int pickColor() {
        Random rand = new Random();
        return rand.nextInt(4);
    }

    public boolean getIntersected() {
        return intersected;
    }

    public void setIntersected(boolean status) {
        intersected = status;
    }

    @Override
    public void disappear() {
        ball.setVisible(false);
    }
}
