package sample;

import javafx.application.Application;
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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameScreen extends Application implements Serializable {
    private int starScore;
    private Level currentLevel;
    private final int[] SCREEN_SIZE = new int[]{500,500};
    private Ball ball;
    private ArrayList<Obstacle> obstacleList;
    private ArrayList<ColorSwitcher> colorSwitchers;
    private ArrayList<Star> stars;
    @FXML
    AnchorPane rootAnchor;
    @FXML
    Label scoreTitle, scoreValue, playLabel;
    @FXML
    ImageView pauseImage, homeImage, playImage, reviveImage, restartImage;

//    GameScreen() throws IOException {
//        SCREEN_SIZE = new int[]{500,500};
//    }

    public int getScore() {
        return starScore;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public int[] getScreenSize() {
        return SCREEN_SIZE;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Obstacle> getObstaclesList() {
        return obstacleList;
    }

    public ArrayList<ColorSwitcher> getColorSwitchers() {
        return colorSwitchers;
    }

    public ArrayList<Star> getStars() {
        return stars;
    }

    public void updateLevel() {

    }

    public void nextObstacle() {

    }

    public void loseGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameOverScreen.fxml"));
        Scene scene = new Scene(root);

        Stage gameOvereStage = new Stage();
        gameOvereStage.setTitle("COLOR SWITCH");
        gameOvereStage.initStyle(StageStyle.UTILITY);
        gameOvereStage.initModality(Modality.APPLICATION_MODAL);
        gameOvereStage.setScene(scene);
        gameOvereStage.setResizable(false);
        gameOvereStage.show();
    }

    public void pauseGame() throws IOException {
        System.out.println("Paused!");
        Parent root = FXMLLoader.load(getClass().getResource("pauseScreen.fxml"));
        Scene scene = new Scene(root);

        Stage pauseStage = new Stage();
        pauseStage.setTitle("COLOR SWITCH");
        pauseStage.initStyle(StageStyle.UTILITY);
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setScene(scene);
        pauseStage.setResizable(false);
        pauseStage.show();
    }

    public void playGame() {
        System.out.println("Play!");
        Stage screen = (Stage)playImage.getScene().getWindow();
        screen.close();
    }

    public void newGame() {
        System.out.println("New Game!");
        Stage screen = (Stage)restartImage.getScene().getWindow();
        screen.close();
    }

    public void saveGame() throws IOException {
        Stage screen = (Stage)homeImage.getScene().getWindow();
        screen.close();
        HomeScreen.serialise();
    }

    public void revive() {
        System.out.println("Revived!");
        Stage screen = (Stage)reviveImage.getScene().getWindow();
        screen.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void jump(){
        System.out.println("Ball Jumped!");
        //ball.jump();
    }

    @Override
    public void start(Stage gameStage) throws Exception{
        HomeScreen.deserialise();

        rootAnchor = FXMLLoader.load(getClass().getResource("gameScreen.fxml"));
        Scene scene = new Scene(rootAnchor);

        CircleObstacle obstacle1 = new CircleObstacle(new float[]{250.0f,150.0f}, new float[]{50.0f}, 2.0, 0.0);
        RectangleObstacle obstacle2 = new RectangleObstacle(new float[]{250.0f,150.0f}, new float[]{50.0f}, 2.0, 0.0);
        EllipseObstacle obstacle3 = new EllipseObstacle(new float[]{250.0f,150.0f}, new float[]{50.0f, 25.0f}, 2.0, 0.0);
        HexagonObstacle obstacle4 = new HexagonObstacle(new float[]{250.0f,150.0f}, new float[]{50.0f}, 2.0, 0.0);
        OneDLineObstacle obstacle5 = new OneDLineObstacle(new float[]{250.0f,150.0f}, new float[]{25.0f}, 2.0, 0.0);
        TwoDLineObstacle obstacle6 = new TwoDLineObstacle(new float[]{250.0f,150.0f}, new float[]{25.0f}, 2.0, 0.0);
        OctagonObstacle obstacle7 = new OctagonObstacle(new float[]{250.0f,150.0f}, new float[]{100.0f}, 2.0, 0.0);
        obstacle1.move();
        obstacle2.move();
        obstacle3.move();
        obstacle4.move();
        obstacle5.move();
        obstacle6.move();
        obstacle7.move();

        obstacleList = new ArrayList<>();
        obstacleList.add(obstacle1);
        obstacleList.add(obstacle2);
        obstacleList.add(obstacle3);
        obstacleList.add(obstacle4);
        obstacleList.add(obstacle5);
        obstacleList.add(obstacle6);
        obstacleList.add(obstacle7);

        ball = new Ball(new float[]{250,400},new float[]{15.0f},0.0,0.0);
        ColorSwitcher switcher = new ColorSwitcher(new float[]{250.0f,300.0f},new float[]{10.0f});
        Star star = new Star(new float[]{250.0f,150.0f},new float[]{10.0f}, 1);
//        private ArrayList<ColorSwitcher> colorSwitchers;
//        private ArrayList<Star> stars;

        rootAnchor.getChildren().addAll(obstacle1.getParts());
        rootAnchor.getChildren().add(ball.getBall());
        rootAnchor.getChildren().add(switcher.getBall());
        rootAnchor.getChildren().add(star.getBall());
//        rootAnchor.getChildren().addAll(obstacle2.getParts());
//        rootAnchor.getChildren().addAll(obstacle3.getParts());
//        rootAnchor.getChildren().addAll(obstacle4.getParts());
//        rootAnchor.getChildren().addAll(obstacle5.getParts());
//        rootAnchor.getChildren().addAll(obstacle6.getParts());
//        rootAnchor.getChildren().addAll(obstacle7.getParts());

        gameStage.setTitle("COLOR SWITCH");
        //gameStage.initStyle(StageStyle.UTILITY);
        //gameStage.initModality(Modality.APPLICATION_MODAL);
        gameStage.setScene(scene);
        gameStage.setResizable(false);
        gameStage.show();
    }
}
