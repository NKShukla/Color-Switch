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
import java.util.*;

public class LeaderboardController extends HomeScreen implements Initializable {

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
    ListView<String> leaderboard;

    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

    private void loadData() {
        List<Map.Entry<String, Player>> dataList = new LinkedList<>(HomeScreen.playerList.entrySet());
        dataList.sort(Comparator.comparingInt(o -> -1 * o.getValue().getScore()));

        list.removeAll();
        int count = 1;
        for (Map.Entry<String, Player> mapElement : dataList) {
            String name = mapElement.getKey();
            Player player = mapElement.getValue();
            list.add(count + ") " + name + " - " + player.getScore());
            count++;
        }
        leaderboard.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
