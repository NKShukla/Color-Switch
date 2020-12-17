package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController extends HomeScreen implements Initializable {

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
    ListView<String> leaderboard;

    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

    private void loadData() {
        list.removeAll();
        String a = "Abhishek", b = "Naval", c = "Player 3", d = "Player 4";
        list.addAll(a, b, c, d);
        leaderboard.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
