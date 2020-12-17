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
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class LoadScreenController extends HomeScreen implements Initializable {

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

    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

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
            HomeScreen.currentPlayer.getScreen().setPlayerController(currentPlayer.getScreen().getPlayerController());

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
        }
    }

    private void loadData(){
        list.removeAll();
        String a="Abhishek", b="Naval", c="Player 3", d="Player 4";
        list.addAll(a,b,c,d);
        savedGamesList.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}