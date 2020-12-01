package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;

abstract public class AbstractLineObstacle extends Obstacle {
    protected final Color[] colors = new Color[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    public abstract void move();

    public abstract Node[] getParts();
}
