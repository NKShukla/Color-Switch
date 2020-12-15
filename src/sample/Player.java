package sample;

import java.io.IOException;
import java.io.Serializable;

public class Player implements Serializable {
    private final String NAME;
    private GameScreen gameScreen;
    private long score;

    Player(String name) throws IOException {
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

    public long getScore() {
        return score;
    }

    public void newGame() throws IOException {
        gameScreen = new GameScreen();
    }

    public void pauseGame() {

    }

    public void increaseScore() {
        score++;
    }
}
