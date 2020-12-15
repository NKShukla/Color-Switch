package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GameScreen implements Serializable {
    private int starScore;
    private Level currentLevel;
    private final int[] SCREEN_SIZE;
    private final Ball ball;
    private final ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private final ArrayList<ColorSwitcher> colorSwitchers = new ArrayList<>();
    private final ArrayList<Star> stars = new ArrayList<>();

    GameScreen() throws IOException {
        SCREEN_SIZE = new int[]{500, 500}; // will use it later or maybe not
        currentLevel = new Level(1); //will use in future
        //scoreValue.setText(Long.toString(HomeScreen.currentPlayer.getScore()));

        ball = new Ball(new float[]{250, 400}, new float[]{15.0f}, 0.0, 0.0);
        colorSwitchers.add(new ColorSwitcher(new float[]{250.0f, 300.0f}, new float[]{10.0f}));
        stars.add(new Star(new float[]{238.0f, 137.0f}, new float[]{10.0f}, 1));

        CircleObstacle obstacle1 = new CircleObstacle(new float[]{250.0f, 150.0f}, new float[]{50.0f}, 2.0, 0.0);
        RectangleObstacle obstacle2 = new RectangleObstacle(new float[]{250.0f, 150.0f}, new float[]{50.0f}, 2.0, 0.0);
        EllipseObstacle obstacle3 = new EllipseObstacle(new float[]{250.0f, 150.0f}, new float[]{50.0f, 25.0f}, 2.0, 0.0);
        HexagonObstacle obstacle4 = new HexagonObstacle(new float[]{250.0f, 150.0f}, new float[]{50.0f}, 2.0, 0.0);
        OneDLineObstacle obstacle5 = new OneDLineObstacle(new float[]{250.0f, 150.0f}, new float[]{25.0f}, 2.0, 0.0);
        TwoDLineObstacle obstacle6 = new TwoDLineObstacle(new float[]{250.0f, 150.0f}, new float[]{25.0f}, 2.0, 0.0);
        OctagonObstacle obstacle7 = new OctagonObstacle(new float[]{250.0f, 150.0f}, new float[]{100.0f}, 2.0, 0.0);

        obstacleList.add(obstacle1);
        obstacleList.add(obstacle2);
        obstacleList.add(obstacle3);
        obstacleList.add(obstacle4);
        obstacleList.add(obstacle5);
        obstacleList.add(obstacle6);
        obstacleList.add(obstacle7);

        Timeline tl = new Timeline();
        tl.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {

                double xMin = ball.getBall().getBoundsInParent().getMinX();
                double yMin = ball.getBall().getBoundsInParent().getMinY();
                double xMax = ball.getBall().getBoundsInParent().getMaxX();
                double yMax = ball.getBall().getBoundsInParent().getMaxY();

//                        if (xMin < 0 || xMax > scene.getWidth()) {
//                            dx = dx * -1;
//                        }
//                        if (yMin < 0 || yMax > scene.getHeight()) {
//                            dy = dy * -1;
//                        }

                //ball.getBall().setTranslateY(ball.getBall().getTranslateY() + 3);

//                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        ball.setCenterY(ball.getCenterY() - 1);
//                    }
//                });

                ball.setCenterY(ball.getCenterY() + 3);
            }
        });

        tl.getKeyFrames().add(moveBall);
        tl.play();
    }

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

        Stage gameOverStage = new Stage();
        gameOverStage.setTitle("COLOR SWITCH");
        //gameOverStage.initStyle(StageStyle.UTILITY);
        gameOverStage.initModality(Modality.APPLICATION_MODAL);
        gameOverStage.setScene(scene);
        gameOverStage.setResizable(false);
        gameOverStage.show();
    }
}
