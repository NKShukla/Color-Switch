package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColorSwitcher extends GameElements implements Animation{
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private Circle ball;

    ColorSwitcher(float[] pos, float[] dim) {
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.WHITE);
    }

    public Circle getBall() {
        return ball;
    }

    public int[] pickColor() {
        return null; //reassign it accordingly
    }

    @Override
    public void disappear() {

    }
}
