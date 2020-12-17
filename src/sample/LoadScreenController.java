package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class LoadScreenController extends HomeScreen implements Initializable {

    ObservableList<String> list = FXCollections.observableArrayList();

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

    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

    public void loadPlayer() throws IOException {
        errorLabel.setVisible(false);
        System.out.println("Entered!");
        Stage playerScreen = (Stage) nameTextField.getScene().getWindow();
        String name = nameTextField.getText();

        if (name.length() == 0) {
            errorLabel.setVisible(true);
            return;
        }
        if (!HomeScreen.playerList.containsKey(name)) {
            System.out.println("Player Not Found Exception!");
        }else {
            System.out.println("Player Existed: " + name);
            HomeScreen.currentPlayer = HomeScreen.playerList.get(name);
            playerScreen.close();
            resumeGame();
        }
    }

    public void resumeGame() throws IOException {
        GameScreen gameScreen = HomeScreen.currentPlayer.getScreen();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameScreen.fxml"));
        AnchorPane rootAnchor = loader.load();
        GameScreenController gameScreenController = loader.getController();
        gameScreenController.setScoreValue(gameScreen.getScore());

        Scene scene = new Scene(rootAnchor);
        Stage gameStage = new Stage();
        gameStage.setTitle("COLOR SWITCH");
        //gameStage.initStyle(StageStyle.UTILITY);
        gameStage.initModality(Modality.APPLICATION_MODAL);
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();

        Ball ball = gameScreen.getBall();
        ArrayList<ColorSwitcher> colorSwitchers = gameScreen.getColorSwitchers();
        ArrayList<Star> stars = gameScreen.getStars();

        Obstacle nextObstacle = gameScreen.getNextObstacle();
        rootAnchor.getChildren().addAll(nextObstacle.getParts());
        rootAnchor.getChildren().add(ball.getBall());
        rootAnchor.getChildren().add(colorSwitchers.get(0).getBall());
        rootAnchor.getChildren().add(stars.get(0).getStar());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                ball.jump();
            }
        });

        gameScreen.getLosingTimer().start();
        gameScreen.setGameTimer(new GameTimer(gameScreen, nextObstacle, null, rootAnchor, loader.getController()));
        AnimationTimer gameTimer = gameScreen.getGameTimer();
        gameTimer.start();
        gameScreen.playAnimation();
    }

    private void loadData(){
        list.removeAll();
        for (Map.Entry mapElement : playerList.entrySet()) {
            String name = (String)mapElement.getKey();
            list.add(name);
        }
        savedGamesList.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
