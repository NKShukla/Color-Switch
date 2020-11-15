package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.HashMap;

public class HomeScreen extends Application implements Serializable {
    private Player currentPlayer;
    private HashMap<String, Player> playerList;

    public void newGame() {

    }

    public void resumeGame() {

    }

    public void exitGame() {

    }

    public static void serialise() {

    }

    public static void deserialise() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Color Switch");
        StackPane pane = new StackPane();
        Button newButton = new Button("New Game");
        Button resumeButton = new Button("Resume Game");
        Button exitButton = new Button("Exit Game");
        pane.getChildren().add(exitButton);
        pane.getChildren().add(resumeButton);
        pane.getChildren().add(newButton);
        Scene scene = new Scene(pane ,600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
