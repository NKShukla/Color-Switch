package sample;

import javafx.scene.paint.Color;

public class Star extends GameElements implements Animation{
    private int point;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    Star(float[] pos, float[] dim, int p) {

    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public void disappear() {

    }
}
