package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class GameScreen implements Serializable {
    private int starScore;
    private Level currentLevel;
    private final int[] SCREEN_SIZE;
    private Ball ball;
    private ArrayList<Obstacle> obstacleList;
    private ArrayList<ColorSwitcher> colorSwitchers;
    private ArrayList<Star> stars;

    GameScreen() {
        SCREEN_SIZE = new int[]{500,500}; //reassign it accordingly
    }

    public int getScore() {
        return starScore;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public int[] getScreenSize() {
        return SCREEN_SIZE;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Obstacle> getObstaclesList() {
        return obstacleList;
    }

    public ArrayList<ColorSwitcher> getColorSwitchers() {
        return colorSwitchers;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public void updateLevel() {

    }

    public void nextObstacle() {

    }

    public void loseGame() {

    }
}
