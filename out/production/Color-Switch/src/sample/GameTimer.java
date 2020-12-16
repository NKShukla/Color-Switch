package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameTimer extends AnimationTimer {
    private final GameScreen gameScreen;
    private final Ball ball;
    private final ArrayList<ColorSwitcher> colorSwitchers;
    private final ArrayList<Star> stars;
    private Obstacle nextObstacle, prevObstacle;
    private final transient AnchorPane rootAnchor;
    private final transient GameScreenController gameScreenController;

    GameTimer(GameScreen gameScreen, Obstacle nextObstacle, Obstacle prevObstacle, AnchorPane rootAnchor, GameScreenController gameScreenController) {
        this.gameScreen = gameScreen;
        this.ball = gameScreen.getBall();
        this.colorSwitchers = gameScreen.getColorSwitchers();
        this.stars = gameScreen.getStars();
        this.nextObstacle = nextObstacle;
        this.prevObstacle = prevObstacle;
        this.rootAnchor = rootAnchor;
        this.gameScreenController = gameScreenController;
    }

    @Override
    public void handle(long l) {
        Shape[] parts1 = (Shape[])nextObstacle.getParts();
        Shape[] parts2 = null;
        if(prevObstacle != null)
            parts2 = (Shape[])prevObstacle.getParts();
        Circle ballCircle = ball.getBall();
        Shape[] intersect = new Shape[parts1.length];
        Circle switcherCircle = colorSwitchers.get(0).getBall();
        Node starNode =  stars.get(0).getStar();

        for(int i = 0; i < parts1.length; i++) {
            intersect[i] = Shape.intersect(parts1[i], ballCircle);
            if (intersect[i].getBoundsInLocal().getWidth() != -1) {
                if(parts1[i].getStroke() != ballCircle.getFill()) {
                    System.out.println("Collide!");
                    gameScreen.setCollision(true);
                    gameScreen.pauseAnimation();
                    gameScreen.getBall().setCenterY(parts1[i].getLayoutY() + 100);
                    stop();
                }
            }
        }

        if(parts2 != null) {
            intersect = new Shape[parts2.length];
            for(int i = 0; i < parts2.length; i++) {
                intersect[i] = Shape.intersect(parts2[i], ballCircle);
                if (intersect[i].getBoundsInLocal().getWidth() != -1) {
                    if(parts2[i].getStroke() != ballCircle.getFill()) {
                        System.out.println("Collide!");
                        gameScreen.setCollision(true);
                        gameScreen.pauseAnimation();
                        gameScreen.getBall().setCenterY(gameScreen.getBall().getCenterY() - 100);
                        stop();
                    }
                }
            }
        }

        if(switcherCircle.intersects(ballCircle.getBoundsInLocal()) && !colorSwitchers.get(0).getIntersected()) {
            System.out.println("Intersected with a colorSwitcher");
            colorSwitchers.get(0).disappear();
            colorSwitchers.get(0).setIntersected(true);
            ballCircle.setFill(GameScreen.colors[colorSwitchers.get(0).pickColor()]);
        }

        if(ballCircle.getBoundsInParent().intersects(starNode.getBoundsInParent()) && !stars.get(0).getIntersected()) {
            System.out.println("Intersected with a star");
            stars.get(0).disappear();
            stars.get(0).setIntersected(true);
            gameScreenController.setScoreValue(gameScreenController.getScoreValue() + 1);
            gameScreen.setStarScore(gameScreenController.getScoreValue());
        }

        if(ballCircle.getCenterY() <= 200.0) {
            ballCircle.setCenterY(ballCircle.getCenterY()+5);
            switcherCircle.setCenterY(switcherCircle.getCenterY()+5);
            starNode.setLayoutY(starNode.getLayoutY()+5);
            for (Shape part : parts1) {
                part.setLayoutY(part.getLayoutY() + 5);
            }
            if(parts2 != null)
            {
                for (Shape part : parts2) {
                    part.setLayoutY(part.getLayoutY() + 5);
                }
            }
        }

        else if(ballCircle.getCenterY() >= 400.0) {
            ballCircle.setCenterY(ballCircle.getCenterY()-5);
            switcherCircle.setCenterY(switcherCircle.getCenterY()-5);
            starNode.setLayoutY(starNode.getLayoutY()-5);
            for (Shape part : parts1) {
                part.setLayoutY(part.getLayoutY() - 5);
            }
            if(parts2 != null)
            {
                for (Shape part : parts2) {
                    part.setLayoutY(part.getLayoutY() - 5);
                }
            }
        }

        if(parts2 != null && parts2[0].getLayoutY() >= 550.0){
            rootAnchor.getChildren().removeAll(prevObstacle.getParts());
        }

        if(parts1[0].getLayoutY() >= 400.0) {
            prevObstacle = nextObstacle;
            nextObstacle = gameScreen.nextObstacle();
            nextObstacle.move();
            rootAnchor.getChildren().remove(colorSwitchers.get(0).getBall());
            rootAnchor.getChildren().remove(stars.get(0).getStar());
            colorSwitchers.add(0, new ColorSwitcher(new float[]{250.0f, 50.0f}, new float[]{10.0f}));
            try {
                stars.add(0,new Star(new float[]{237.0f, -112.0f}, new float[]{25.0f}, 1));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            rootAnchor.getChildren().add(colorSwitchers.get(0).getBall());
            rootAnchor.getChildren().add(stars.get(0).getStar());
            rootAnchor.getChildren().addAll(nextObstacle.getParts());
        }
    }

    public Obstacle getNextObstacle() {
        return nextObstacle;
    }

    public Obstacle getPrevObstacle() {
        return prevObstacle;
    }

    public GameScreenController getGameScreenController() {
        return gameScreenController;
    }
}
