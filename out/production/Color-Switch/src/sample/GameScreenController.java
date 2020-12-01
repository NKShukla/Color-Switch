package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GameScreenController {
    @FXML
    AnchorPane rootAnchor;
    @FXML
    Label scoreTitle, scoreValue, playLabel;
    @FXML
    ImageView pauseImage, homeImage, playImage, reviveImage, restartImage;

    public void loseGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameOverScreen.fxml"));
        Scene scene = new Scene(root);

        Stage gameOvereStage = new Stage();
        gameOvereStage.setTitle("COLOR SWITCH");
        //gameOvereStage.initStyle(StageStyle.UTILITY);
        gameOvereStage.initModality(Modality.APPLICATION_MODAL);
        gameOvereStage.setScene(scene);
        gameOvereStage.setResizable(false);
        gameOvereStage.show();
    }

    public void pauseGame() throws IOException {
        System.out.println("Paused!");
        Parent root = FXMLLoader.load(getClass().getResource("pauseScreen.fxml"));
        Scene scene = new Scene(root);

        Stage pauseStage = new Stage();
        pauseStage.setTitle("COLOR SWITCH");
        //pauseStage.initStyle(StageStyle.UTILITY);
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setScene(scene);
        pauseStage.setResizable(false);
        pauseStage.show();
    }

    public void playGame() {
        System.out.println("Play!");
        Stage screen = (Stage) playImage.getScene().getWindow();
        screen.close();
    }

    public void newGame() {
        System.out.println("New Game!");
        Stage screen = (Stage) restartImage.getScene().getWindow();
        screen.close();
    }

    public void saveGame() throws IOException {
        Stage screen = (Stage) homeImage.getScene().getWindow();
        screen.close();
        HomeScreen.serialise();
    }

    public void revive() {
        System.out.println("Revived!");
        Stage screen = (Stage) reviveImage.getScene().getWindow();
        screen.close();
    }

    public void jump() {
        System.out.println("Ball Jumped!");
        //ball.jump();
    }
}
