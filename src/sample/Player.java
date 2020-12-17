package sample;

import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
    private final String NAME;
    private GameScreen gameScreen;
    private int score;

    Player(String name) throws IOException, ClassNotFoundException {
        this.NAME = name;
        gameScreen = new GameScreen();
        score = 0;
    }

    public String getNAME() {
        return NAME;
    }

    public GameScreen getScreen() {
        return gameScreen;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int x) {
        if(score < x) {
            score = x;
        }
    }

    public void newGame() throws IOException, ClassNotFoundException {
        gameScreen = new GameScreen();
    }
}
