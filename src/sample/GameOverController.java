package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {
    @FXML
    ImageView homeImage, reviveImage, restartImage;

    public void newGame() throws IOException, ClassNotFoundException {
        System.out.println("New Game!");
        Stage screen = (Stage) restartImage.getScene().getWindow();
        screen.close();
        HomeScreen.currentPlayer.setScore(HomeScreen.currentPlayer.getScreen().getScore());
        GameScreen gameScreen = HomeScreen.currentPlayer.getScreen();
        gameScreen.setCollision(false);
        gameScreen.playAnimation();
        gameScreen.getPlayerController().restartGame();
    }

    public void revive() throws ClassNotFoundException, IOException, InsufficientScoreException {
        GameScreen gameScreen = HomeScreen.currentPlayer.getScreen();
        if(gameScreen.getScore() >= gameScreen.getLevel().getRevivalScore()){
            System.out.println("Revived!");
            Stage screen = (Stage) reviveImage.getScene().getWindow();
            screen.close();
            gameScreen.setStarScore(gameScreen.getScore()-gameScreen.getLevel().getRevivalScore());
            gameScreen.setCollision(false);
            gameScreen.playAnimation();
            gameScreen.getLosingTimer().start();
            gameScreen.getGameTimer().start();
        }
        else{
            throw new InsufficientScoreException("Insufficient Score Exception!");
        }
    }

    public void saveGame() throws IOException {
        Stage screen = (Stage) homeImage.getScene().getWindow();
        screen.close();
        //HomeScreen.currentPlayer.getScreen().getGameStage().close();
        HomeScreen.currentPlayer.setScore(HomeScreen.currentPlayer.getScreen().getScore());
        HomeScreen.serialise();
    }
}
