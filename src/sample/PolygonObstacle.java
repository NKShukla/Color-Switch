package sample;

import javafx.scene.Node;

abstract public class PolygonObstacle extends Obstacle {
    public abstract void move();
    public abstract void stop();

    public abstract Node[] getParts();
}
