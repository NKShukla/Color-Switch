package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Serializable {
    private int starScore;
    private final Level currentLevel;
    private final int[] SCREEN_SIZE;
    private final Ball ball;
    private final ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private final ArrayList<ColorSwitcher> colorSwitchers = new ArrayList<>();
    private final ArrayList<Star> stars = new ArrayList<>();
    private boolean collision = false;
    private AnimationTimer gameTimer, losingTimer;
    private PlayerController playerController;
    public static final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    GameScreen() throws IOException {
        SCREEN_SIZE = new int[]{500, 500}; // will use it later or maybe not
        currentLevel = new Level(1); //will use in future
        //scoreValue.setText(Long.toString(HomeScreen.currentPlayer.getScore()));

        ball = new Ball(new float[]{250, 300}, new float[]{15.0f}, 0.0, 0.0);
        colorSwitchers.add(new ColorSwitcher(new float[]{250.0f, 100.0f}, new float[]{10.0f}));
        stars.add(new Star(new float[]{237.0f, -112.0f}, new float[]{25.0f}, 1));

        CircleObstacle obstacle1 = new CircleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 4.0, 0.0);
        RectangleObstacle obstacle2 = new RectangleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 3.0, 0.0);
        EllipseObstacle obstacle3 = new EllipseObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f, 50.0f}, 5.0, 0.0);
        HexagonObstacle obstacle4 = new HexagonObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 2.0, 0.0);
        OneDLineObstacle obstacle5 = new OneDLineObstacle(new float[]{250.0f, -65.0f}, new float[]{50.0f}, 3.0, 0.0);
        TwoDLineObstacle obstacle6 = new TwoDLineObstacle(new float[]{220.0f, -100.0f}, new float[]{50.0f}, 2.0, 0.0);
        OctagonObstacle obstacle7 = new OctagonObstacle(new float[]{250.0f, -100.0f}, new float[]{90.0f}, 1.0, 0.0);

        obstacleList.add(obstacle1);
        obstacleList.add(obstacle2);
        obstacleList.add(obstacle3);
        obstacleList.add(obstacle4);
        obstacleList.add(obstacle5);
        obstacleList.add(obstacle6);
        obstacleList.add(obstacle7);

        Timeline tl = new Timeline();
        tl.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(0.1), event -> {
            ball.setCenterY(ball.getCenterY() + 3);
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

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void setGameTimer(AnimationTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public AnimationTimer getGameTimer() {
        return gameTimer;
    }

    public void setLosingTimer(AnimationTimer losingTimer) {
        this.losingTimer = losingTimer;
    }

    public AnimationTimer getLosingTimer() {
        return losingTimer;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public boolean isCollision() {
        return collision;
    }

    public void updateLevel() {

    }

    public Obstacle nextObstacle() {
        Random rand = new Random();
        switch (rand.nextInt(7)+1) {
            case 1: return new CircleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 4.0, 0.0);
            case 2: return new RectangleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 3.0, 0.0);
            case 3: return new EllipseObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f, 50.0f}, 5.0, 0.0);
            case 4: return new HexagonObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 2.0, 0.0);
            case 5: return new OneDLineObstacle(new float[]{250.0f, -65.0f}, new float[]{50.0f}, 3.0, 0.0);
            case 6: return new TwoDLineObstacle(new float[]{220.0f, -100.0f}, new float[]{50.0f}, 2.0, 0.0);
            case 7: return new OctagonObstacle(new float[]{250.0f, -100.0f}, new float[]{90.0f}, 1.0, 0.0);
            default: return null;
        }
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
