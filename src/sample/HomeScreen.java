package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.HashMap;

public class HomeScreen extends Application {
    public static Player currentPlayer;
    public static HashMap<String, Player> playerList = new HashMap<>();
    @FXML
    AnchorPane rootAnchor;
    @FXML
    Label title1, title2;
    @FXML
    ImageView bgImage, newImage, resumeImage, exitImage, leaderBoardImage, settingImage, helpImage, shareImage;

    public void newGame() throws IOException {
        System.out.println("New Game!");

        AnchorPane root = FXMLLoader.load(getClass().getResource("playerScreen.fxml"));
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
        //playerStage.initStyle(StageStyle.UTILITY);
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

    public static void serialise() throws IOException {
        ObjectOutputStream out = null;
//        try{
//            out = new ObjectOutputStream (new FileOutputStream("savedData.txt"));
//            out.writeObject(playerList);
//            System.out.println("Serialise!");
//        } finally {
//            if(out != null)
//                out.close();
//        }
    }

    public static void deserialise() throws IOException {
        ObjectInputStream in = null;
//        try {
//            System.out.println("Deserialised!");
//            in = new ObjectInputStream (new FileInputStream("savedData.txt"));
//            playerList = (HashMap<String, Player>) in.readObject();
//        }
//        catch(ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        finally {
//            if(in != null)
//                in.close();
//        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        deserialise();

        Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("COLOR SWITCH");
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
