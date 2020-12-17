package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class
    PlayerController extends  HomeScreen {
    ObservableList list = FXCollections.observableArrayList();

    @FXML
    ImageView backImage;
    @FXML
    Label nameLabel, errorLabel;
    @FXML
    Button submitBtn;
    @FXML
    TextField nameTextField;
    @FXML
    ListView<String> savedGamesList;

    public void addPlayer() throws IOException, ClassNotFoundException {
        errorLabel.setVisible(false);
        System.out.println("Submitted!");
        Stage playerScreen = (Stage) nameTextField.getScene().getWindow();
        String name = nameTextField.getText();

        if (name.length() == 0) {
            errorLabel.setVisible(true);
            return;
        }
        if (HomeScreen.playerList.containsKey(name)) {
            System.out.println("Player Existed!");
            HomeScreen.currentPlayer = HomeScreen.playerList.get(name);
            HomeScreen.currentPlayer.getScreen().setPlayerController(this);
            playerScreen.close();
            restartGame();
        } else {
            System.out.println("New Player Created: " + name);
            HomeScreen.currentPlayer = new Player(name);
            HomeScreen.playerList.put(name, HomeScreen.currentPlayer);
            HomeScreen.currentPlayer.getScreen().setPlayerController(this);
            playerScreen.close();
            continueGame();
        }
    }

    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

    public void continueGame() throws IOException {
        GameScreen gameScreen = HomeScreen.currentPlayer.getScreen();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        AnchorPane rootAnchor = loader.load();

        Scene scene = new Scene(rootAnchor);
        Stage gameStage = new Stage();
        gameStage.setTitle("COLOR SWITCH");
        //gameStage.initStyle(StageStyle.UTILITY);
        gameStage.initModality(Modality.APPLICATION_MODAL);
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
        gameScreen.setGameStage(gameStage);

        Ball ball = gameScreen.getBall();
        ArrayList<ColorSwitcher> colorSwitchers = gameScreen.getColorSwitchers();
        ArrayList<Star> stars = gameScreen.getStars();

        Obstacle nextObstacle = gameScreen.nextObstacle();
        rootAnchor.getChildren().addAll(nextObstacle.getParts());
        rootAnchor.getChildren().add(ball.getBall());
        rootAnchor.getChildren().add(colorSwitchers.get(0).getBall());
        rootAnchor.getChildren().add(stars.get(0).getStar());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                ball.jump();
            }
        });

        gameScreen.setGameTimer(new GameTimer(gameScreen, nextObstacle, null, rootAnchor, loader.getController()));
        GameTimer gameTimer = gameScreen.getGameTimer();
        gameTimer.start();

        AnimationTimer losingTimer = gameScreen.getLosingTimer();
        losingTimer.start();
        gameScreen.playAnimation();
    }

    public void restartGame() throws IOException, ClassNotFoundException {
        HomeScreen.currentPlayer.getScreen().getGameStage().close();
        HomeScreen.currentPlayer.newGame();
        HomeScreen.currentPlayer.getScreen().setPlayerController(this);
        continueGame();
    }
}
