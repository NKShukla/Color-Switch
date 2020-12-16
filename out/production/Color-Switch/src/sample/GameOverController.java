package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {
    @FXML
    ImageView homeImage, reviveImage, restartImage;

    public void newGame() throws IOException {
        System.out.println("New Game!");
        Stage screen = (Stage) restartImage.getScene().getWindow();
        screen.close();
        HomeScreen.currentPlayer.getScreen().setCollision(false);
        HomeScreen.currentPlayer.getScreen().playAnimation();
        HomeScreen.currentPlayer.getScreen().getPlayerController().restartGame();
    }

    public void revive() {
        System.out.println("Revived!");
        Stage screen = (Stage) reviveImage.getScene().getWindow();
        screen.close();
        HomeScreen.currentPlayer.getScreen().setCollision(false);
        HomeScreen.currentPlayer.getScreen().playAnimation();
        HomeScreen.currentPlayer.getScreen().getLosingTimer().start();
        HomeScreen.currentPlayer.getScreen().getGameTimer().start();
    }

    public void saveGame() throws IOException {
        Stage screen = (Stage) homeImage.getScene().getWindow();
        screen.close();
        //HomeScreen.currentPlayer.getScreen().getGameStage().close();
        HomeScreen.serialise();
    }
}
