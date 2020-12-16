package sample;

import java.io.Serializable;

public class GameElements implements Serializable {
    private float[] position;
    private float[] dimensions;

    public float[] getPosition() {
        return position;
    }

    public float[] getDimensions() {
        return dimensions;
    }

    public void setPosition(float[] pos) {
        this.position = pos;
    }

    public void setDimensions(float[] dim) {
        this.dimensions = dim;
    }
}
