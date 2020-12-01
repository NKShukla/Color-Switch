package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PlayerController {
    @FXML
    ImageView backImage;
    @FXML
    Label nameLabel, errorLabel;
    @FXML
    Button submitBtn;
    @FXML
    TextField nameTextField;

    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    public void addPlayer() throws IOException {
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

            AnchorPane rootAnchor = FXMLLoader.load(getClass().getResource("newGamePopUp.fxml"));
            Scene scene = new Scene(rootAnchor);
            Stage popUpStage = new Stage();
            popUpStage.setTitle("ATTENTION");
            //gameStage.initStyle(StageStyle.UTILITY);
            popUpStage.initModality(Modality.APPLICATION_MODAL);
            popUpStage.setScene(scene);
            popUpStage.setResizable(false);
            popUpStage.show();
            playerScreen.close();
        } else {
            System.out.println("New Player Created: " + name);
            HomeScreen.currentPlayer = new Player(name);
            HomeScreen.playerList.put(name, HomeScreen.currentPlayer);
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
        AnchorPane rootAnchor = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));
        Scene scene = new Scene(rootAnchor);
        Stage gameStage = new Stage();
        gameStage.setTitle("COLOR SWITCH");
        //gameStage.initStyle(StageStyle.UTILITY);
        gameStage.initModality(Modality.APPLICATION_MODAL);
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();

        Random rand = new Random();
        ArrayList<Obstacle> obstacleList = gameScreen.getObstaclesList();
        Ball ball = gameScreen.getBall();
        ArrayList<ColorSwitcher> colorSwitchers = gameScreen.getColorSwitchers();
        ArrayList<Star> stars = gameScreen.getStars();

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ball.setCenterY(ball.getCenterY() - 20);
                ball.getBall().setFill(colors[colorSwitchers.get(0).pickColor()]);
                colorSwitchers.get(0).disappear();
            }
        });

        Obstacle nextObstacle = obstacleList.get(rand.nextInt(obstacleList.size()));
        nextObstacle.move();
        rootAnchor.getChildren().addAll(nextObstacle.getParts());
        rootAnchor.getChildren().add(ball.getBall());
        rootAnchor.getChildren().add(colorSwitchers.get(0).getBall());
        rootAnchor.getChildren().add(stars.get(0).getStar());
        Stage playerScreen = (Stage) nameTextField.getScene().getWindow();
        playerScreen.close();
    }

    public void restartGame() throws IOException {
        HomeScreen.currentPlayer.newGame();
        continueGame();
    }

}