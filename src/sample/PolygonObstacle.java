package sample;

import javafx.scene.Node;

abstract public class PolygonObstacle extends Obstacle {
    public abstract void move();

    public abstract Node[] getParts();

    public PolygonObstacle clone() {
        return (PolygonObstacle) super.clone();
    }
}
