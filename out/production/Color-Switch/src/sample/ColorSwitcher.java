package sample;

import javafx.scene.paint.Color;

public class ColorSwitcher extends GameElements implements Animation{
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    ColorSwitcher(float[] pos, float[] dim) {

    }

    public int[] pickColor() {
        return null; //reassign it accordingly
    }

    @Override
    public void disappear() {

    }
}
