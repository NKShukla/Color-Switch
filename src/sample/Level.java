package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    private int levelNumber;
    private int revivalScore;
    private double ballFallSpeed;
    private double ballJumpSpeed;
    private double obstacleDuration;
    private ArrayList<String> availableObstacles;

    Level(int levelNumber) {

    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getRevivalScore() {
        return revivalScore;
    }

    public double getFallSpeed() {
        return ballFallSpeed;
    }

    public double getJumpSpeed() {
        return ballJumpSpeed;
    }

    public double getObstacleDuration() {
        return obstacleDuration;
    }

    public ArrayList<String> getAvailableObstacles() {
        return availableObstacles;
    }

    public void intialiseLevel(int xlevel) {

    }
}
