package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;

public class HomeScreen extends Application {
    private Player currentPlayer;
    private HashMap<String, Player> playerList;
    @FXML
    AnchorPane rootAnchor;
    @FXML
    Label title1, title2, nameLabel;
    @FXML
    ImageView bgImage, newImage, resumeImage, exitImage, leaderBoardImage, settingImage, helpImage, shareImage, backImage;
    @FXML
    Button submitBtn;
    @FXML
    TextField nameTextField;

    public void newGame() throws IOException {
        System.out.println("New Game!");

        Parent root = FXMLLoader.load(getClass().getResource("playerScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void resumeGame() throws IOException {
        System.out.println("Resume Game!");
        Parent root = FXMLLoader.load(getClass().getResource("playerScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void exitGame() throws IOException {
        serialise();
        System.out.println("Exit Game!");
        Platform.exit();
    }

    public void showLeaderBoard() {
        System.out.println("Leader Board!");
    }

    public void settings() {
        System.out.println("Settings!");
    }

    public void help() {
        System.out.println("Help!");
    }

    public void share() {
        System.out.println("Shared!");
    }

    public void choosePlayer() throws IOException {
        System.out.println("Submitted!");
        Stage playerScreen = (Stage)nameTextField.getScene().getWindow();
        String name = nameTextField.getText();
        playerScreen.close();
        currentPlayer = new Player(name);
    }

    public static void serialise() throws IOException{
        System.out.println("Serialise!");
    }

    public static void deserialise() {
        System.out.println("Deserialised!");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        deserialise();

        Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("COLOR SWITCH");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
