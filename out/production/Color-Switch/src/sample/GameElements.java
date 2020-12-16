package sample;

import java.io.Serializable;

public class GameElements implements Serializable, Cloneable {
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

    public GameElements clone() {
        GameElements gameElement = null;
        try{
            gameElement = (GameElements)super.clone();
            gameElement.position = this.position.clone();
            gameElement.dimensions = this.dimensions.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return gameElement;
    }
}
