package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ShareScreenController extends HomeScreen {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    ImageView backImage;


    public void backHome() {
        System.out.println("Back to Home!");
        Stage playerScreen = (Stage) backImage.getScene().getWindow();
        playerScreen.close();
    }

    public void openFB() {
        getHostServices().showDocument("https://www.facebook.com/");
    }

    public void openInsta() {
        getHostServices().showDocument("https://www.instagram.com/");
    }

    public void openWhatsApp() {
        getHostServices().showDocument("https://www.whatsapp.com/");
    }

    public void openTwitter() {
        getHostServices().showDocument("https://twitter.com/?lang=en");
    }
}
