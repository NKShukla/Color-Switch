package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class HomeScreen extends Application implements Serializable {
    private Player currentPlayer;
    private HashMap<String, Player> playerList;

    public void newGame() {
        System.out.println("New Game!");
    }

    public void resumeGame() {
        System.out.println("Resume Game!");
    }

    public void exitGame() {
        System.out.println("Exit Game!");
    }

    public static void serialise() throws IOException{

    }

    public static void deserialise() {
        System.out.println("Deserialised!!");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        deserialise();

        //Parent root = FXMLLoader.load(getClass().getResource("homeScreen.fxml"));
        /*Testing obstacles*/
        AnchorPane root = new AnchorPane();

        CircleObstacle obstacle1 = new CircleObstacle(new float[]{100.0f,100.0f}, new float[]{50.0f}, 2.0, 0.0);
        obstacle1.move();

        RectangleObstacle obstacle2 = new RectangleObstacle(new float[]{300.0f, 100.0f}, new float[]{50.0f}, 2.0, 0.0);
        obstacle2.move();

        EllipseObstacle obstacle3 = new EllipseObstacle(new float[]{100.0f,300.0f}, new float[]{50.0f, 25.0f}, 2.0, 0.0);
        obstacle3.move();

        HexagonObstacle obstacle4 = new HexagonObstacle(new float[]{300.0f, 300.0f}, new float[]{50.0f}, 2.0, 0.0);
        obstacle4.move();

        OneDLineObstacle obstacle5 = new OneDLineObstacle(new float[]{200.0f, 200.0f}, new float[]{25.0f}, 2.0, 0.0);
        obstacle5.move();

        TwoDLineObstacle obstacle6 = new TwoDLineObstacle(new float[]{100.0f, 400.0f}, new float[]{25.0f}, 2.0, 0.0);
        obstacle6.move();

        OctagonObstacle obstacle7 = new OctagonObstacle(new float[]{300.0f, 450.0f}, new float[]{100.0f}, 2.0, 0.0);
        obstacle7.move();

        root.getChildren().addAll(obstacle1.getParts());
        root.getChildren().addAll(obstacle2.getParts());
        root.getChildren().addAll(obstacle3.getParts());
        root.getChildren().addAll(obstacle4.getParts());
        root.getChildren().addAll(obstacle5.getParts());
        root.getChildren().addAll(obstacle6.getParts());
        root.getChildren().addAll(obstacle7.getParts());

        Scene scene = new Scene(root, 500, 600);

        primaryStage.setTitle("Color Switch");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
