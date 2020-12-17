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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends Application {
    public static Player currentPlayer;
    public static HashMap<String, Player> playerList = new HashMap<>();
    public MediaPlayer mediaPlayer;
    Media sound = new Media(getClass().getResource("bgmusic.mp3").toString());

    private boolean mute=true;

    @FXML
    AnchorPane rootAnchor;
    @FXML
    Label title1, title2;
    @FXML
    ImageView bgImage, newImage, resumeImage, exitImage, leaderBoardImage, helpImage, shareImage, muteImage, unmuteImage;


    public HomeScreen() {
        mediaPlayer = new MediaPlayer(sound);
    }

    public void newGame() throws IOException {
        System.out.println("New Game!");

        AnchorPane root = FXMLLoader.load(getClass().getResource("playerScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        //playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void resumeGame() throws IOException {
        System.out.println("Resume Game!");
        Parent root = FXMLLoader.load(getClass().getResource("loadScreen.fxml"));
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
        if(HomeScreen.currentPlayer!=null)
            HomeScreen.currentPlayer.setScore(HomeScreen.currentPlayer.getScreen().getScore());
        serialise();
        System.out.println("Exit Game!");
        Platform.exit();
    }

    public void showLeaderBoard() throws IOException {
        System.out.println("Leader Board!");
        //mediaPlayer.stop();
        AnchorPane root = FXMLLoader.load(getClass().getResource("leaderboardScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        //playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void help() throws IOException {
        System.out.println("Help!");
        AnchorPane root = FXMLLoader.load(getClass().getResource("helpScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        //playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void share() throws IOException {
        System.out.println("Shared!");
        AnchorPane root = FXMLLoader.load(getClass().getResource("shareScreen.fxml"));
        Scene scene = new Scene(root);

        Stage playerStage = new Stage();
        playerStage.setTitle("COLOR SWITCH");
        //playerStage.initStyle(StageStyle.UTILITY);
        playerStage.initModality(Modality.APPLICATION_MODAL);
        playerStage.setScene(scene);
        playerStage.setResizable(false);
        playerStage.show();
    }

    public void mute(){
        if(!mute){
            mute=true;
            System.out.println("Muted");
            mediaPlayer.stop();
        }
        else
            System.out.println("Already Muted");
    }

    public void unmute(){
        if(mute){
            mute=false;
            System.out.println("Unmuted");
            mediaPlayer.play();
        }
        else
            System.out.println("Already Playing");
    }

    public static void serialise() throws IOException {
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream (new FileOutputStream("savedData.txt"));
            out.writeObject(playerList);
            System.out.println("Serialise!");
            for (Map.Entry mapElement : playerList.entrySet()) {
                String key = (String)mapElement.getKey();
                System.out.println(key);
            }
        } finally {
            if(out != null)
                out.close();
        }
    }

    public static void deserialise() throws IOException {
        ObjectInputStream in = null;
        try {
            System.out.println("Deserialised!");
            in = new ObjectInputStream (new FileInputStream("savedData.txt"));
            playerList = (HashMap<String, Player>) in.readObject();

            for (Map.Entry mapElement : playerList.entrySet()) {
                String key = (String)mapElement.getKey();
                System.out.println(key);
            }
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if(in != null)
                in.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //deserialise();

        Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("COLOR SWITCH");
        //primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
