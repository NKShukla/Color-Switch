package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Star extends GameElements implements Animation{
    private int point;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private Circle ball;

    Star(float[] pos, float[] dim, int p) {
        ball = new Circle(pos[0], pos[1], dim[0]);
        ball.setFill(Color.YELLOW);
        this.point = p;
    }

    public int getPoint() {
        return point;
    }

    public Circle getBall() {
        return ball;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public void disappear() {

    }
}
