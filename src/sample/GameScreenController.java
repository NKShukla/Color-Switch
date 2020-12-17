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
    ImageView pauseImage, homeImage, playImage;

    public void pauseGame() throws IOException {
        HomeScreen.currentPlayer.getScreen().pauseAnimation();
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
        HomeScreen.currentPlayer.getScreen().playAnimation();
        System.out.println("Play!");
        Stage screen = (Stage) playImage.getScene().getWindow();
        screen.close();
    }

    public void saveGame() throws IOException {
        Stage screen = (Stage) homeImage.getScene().getWindow();
        screen.close();
        HomeScreen.currentPlayer.setScore(HomeScreen.currentPlayer.getScreen().getScore());
        HomeScreen.serialise();
        HomeScreen.currentPlayer.getScreen().playAnimation();
        System.out.println("Saved!");
    }

    public void backToHome() {
        Stage screen = (Stage) homeImage.getScene().getWindow();
        screen.close();
        HomeScreen.currentPlayer.getScreen().getGameStage().close();
        System.out.println("Back to Home!");
    }

    public void jump() {
        System.out.println("Ball Jumped!");
        //ball.jump();
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue.setText(Integer.valueOf(scoreValue).toString());
    }

    public int getScoreValue() {
        return Integer.parseInt(this.scoreValue.getText());
    }
}
