package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerNotFoundException extends Throwable {
    PlayerNotFoundException(String msg) throws IOException {
        super(msg);
        AnchorPane rootAnchor = FXMLLoader.load(getClass().getResource("newGamePopUp.fxml"));
        Scene scene = new Scene(rootAnchor);
        Stage exceptionStage = new Stage();
        exceptionStage.setTitle("WARNING");
        //gameStage.initStyle(StageStyle.UTILITY);
        exceptionStage.initModality(Modality.APPLICATION_MODAL);
        exceptionStage.setScene(scene);
        exceptionStage.setResizable(false);
        exceptionStage.show();
    }
}
