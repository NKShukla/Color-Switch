package sample;

import javafx.scene.Node;

abstract public class AbstractLineObstacle extends Obstacle {

    public abstract void move();
    public abstract void stop();

    public abstract Node[] getParts();
}
