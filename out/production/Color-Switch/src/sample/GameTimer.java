package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class GameTimer extends AnimationTimer {
    private final GameElements gameElement1;
    private final GameElements gameElement2;

    GameTimer(GameElements first, GameElements second) {
        gameElement1 = first;
        gameElement2 = second;
    }

    @Override
    public void handle(long l) {
        Shape[] parts = (Shape[])((Obstacle)gameElement1).getParts();
        Circle ball = ((Ball) gameElement2).getBall();
        Shape[] intersect = new Shape[parts.length];
        for(int i = 0; i < parts.length; i++) {
            intersect[i] = Shape.intersect(parts[i], ball);
            if (intersect[i].getBoundsInLocal().getWidth() != -1) {
                if(parts[i].getStroke() != ball.getFill()) {
                    System.out.println("Collide!");
                    PlayerController.collision = true;
                    stop();
                }
            }
        }
    }
}
