package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private final String NAME;
    private GameScreen gameScreen;
    private long score;

    Player(String name) {
        this.NAME = name;
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

    public void newGame() {

    }

    public void pauseGame() {

    }
}
