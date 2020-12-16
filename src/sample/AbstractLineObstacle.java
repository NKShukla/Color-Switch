package sample;

import javafx.scene.Node;

abstract public class AbstractLineObstacle extends Obstacle {

    public AbstractLineObstacle clone() {
        return (AbstractLineObstacle) super.clone();
    }

    public abstract void move();

    public abstract Node[] getParts();
}
