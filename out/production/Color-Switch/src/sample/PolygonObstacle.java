package sample;

import javafx.scene.paint.Color;

abstract public class PolygonObstacle extends Obstacle{
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    abstract public void move();
}
