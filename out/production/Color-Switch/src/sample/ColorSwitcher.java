package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class ColorSwitcher extends GameElements implements Animation{
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private Circle ball;
    private boolean intersected = false;

    ColorSwitcher(float[] pos, float[] dim) {
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.WHITE);
    }

    public Circle getBall() {
        return ball;
    }

    public int pickColor() { Random rand = new Random(); return rand.nextInt(4);}

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
