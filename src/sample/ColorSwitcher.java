package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ColorSwitcher extends GameElements implements Animation{
    private transient Circle ball;
    private boolean intersected;

    ColorSwitcher(float[] pos, float[] dim) {
        setPosition(pos);
        setDimensions(dim);
        intersected = false;
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.WHITE);
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
