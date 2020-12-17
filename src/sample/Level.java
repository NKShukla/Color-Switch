package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {
    private int levelNumber;
    private int revivalScore;
    private double ballFallTime;
    private double obstacleDuration;
    private ArrayList<String> availableObstacles;

    Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.revivalScore = levelNumber * 2;
        this.availableObstacles = new ArrayList<>();

        if(levelNumber >= 1) {
            availableObstacles.add("RectangleObstacle");
            availableObstacles.add("CircleObstacle");
            availableObstacles.add("OneDLineObstacle");
        }
        if(levelNumber >= 2) {
            availableObstacles.add("TwoDLineObstacle");
        }
        if(levelNumber >=3) {
            availableObstacles.add("EllipseObstacle");
        }
        if(levelNumber >= 4) {
            availableObstacles.add("HexagonObstacle");
        }
        if(levelNumber >=5) {
            availableObstacles.add("OctagonObstacle");
            this.obstacleDuration = 1;
            this.ballFallTime = 0.02;
        }
        else{
            this.obstacleDuration = 6 - levelNumber;
            this.ballFallTime = 0.11 - levelNumber * 0.01;
        }
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getRevivalScore() {
        return revivalScore;
    }

    public double getFallTime() {
        return ballFallTime;
    }

    public double getObstacleDuration() {
        return obstacleDuration;
    }

    public ArrayList<String> getAvailableObstacles() {
        return availableObstacles;
    }
}
