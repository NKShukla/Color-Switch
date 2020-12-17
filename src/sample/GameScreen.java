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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Serializable {
    private int starScore;
    private Level currentLevel;
    private final Ball ball;
    private final ArrayList<ColorSwitcher> colorSwitchers = new ArrayList<>();
    private final ArrayList<Star> stars = new ArrayList<>();
    private ArrayList<Obstacle> obstacleList = new ArrayList<>();
    private Obstacle nextObstacle, prevObstacle;
    private boolean collision = false;
    private transient AnimationTimer losingTimer;
    private transient GameTimer gameTimer;
    private transient PlayerController playerController;
    private transient Timeline ballTimer;
    private transient Stage gameStage;
    public static final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    GameScreen() throws IOException, ClassNotFoundException {
        currentLevel = new Level(1); //will use in future
        ball = new Ball(new float[]{250, 300}, new float[]{7.5f}, 0.0, 0.0);
        colorSwitchers.add(new ColorSwitcher(new float[]{250.0f, 100.0f}, new float[]{10.0f}));
        stars.add(new Star(new float[]{237.0f, -112.0f}, new float[]{25.0f}, 1));

        double dur = currentLevel.getObstacleDuration();
        for(String obstacleName: currentLevel.getAvailableObstacles()) {
            switch (obstacleName) {
                case "CircleObstacle":
                    obstacleList.add(new CircleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.8 * dur, 0.0));
                    break;
                case "RectangleObstacle":
                    obstacleList.add(new RectangleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.6 * dur, 0.0));
                    break;
                case "EllipseObstacle":
                    obstacleList.add(new EllipseObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f, 50.0f}, dur, 0.0));
                    break;
                case "HexagonObstacle":
                    obstacleList.add(new HexagonObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.4 * dur, 0.0));
                    break;
                case "OneDLineObstacle":
                    obstacleList.add(new OneDLineObstacle(new float[]{250.0f, -65.0f}, new float[]{50.0f}, 0.6 * dur, 0.0));
                    break;
                case "TwoDLineObstacle":
                    obstacleList.add(new TwoDLineObstacle(new float[]{220.0f, -100.0f}, new float[]{50.0f}, 0.4 * dur, 0.0));
                    break;
                case "OctagonObstacle":
                    new OctagonObstacle(new float[]{250.0f, -100.0f}, new float[]{90.0f}, 0.2 * dur, 0.0);
                    break;
            }
        }

        ballTimer = new Timeline();
        ballTimer.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(currentLevel.getFallTime()), event -> {
            ball.setCenterY(ball.getCenterY() + 3);
        });
        ballTimer.getKeyFrames().add(moveBall);
        ballTimer.play();

        losingTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (isCollision()) {
                    try {
                        loseGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        stop();
                    }
                }
            }
        };
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        ballTimer = new Timeline();
        ballTimer.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(currentLevel.getFallTime()), event -> {
            ball.setCenterY(ball.getCenterY() + 3);
        });
        ballTimer.getKeyFrames().add(moveBall);

        losingTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (isCollision()) {
                    try {
                        loseGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        stop();
                    }
                }
            }
        };
    }

    public int getScore() {
        return starScore;
    }

    public void setStarScore(int starScore) throws ClassNotFoundException {
        this.starScore = starScore;
        this.gameTimer.getGameScreenController().setScoreValue(starScore);
        if(starScore >= currentLevel.getLevelNumber()*5)
            updateLevel();
    }

    public Stage getGameStage() {
        return gameStage;
    }

    public void setGameStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public Ball getBall() {
        return ball;
    }

    public void pauseAnimation() {
        ballTimer.stop();
        gameTimer.getNextObstacle().stop();
        if(gameTimer.getPrevObstacle() != null)
            gameTimer.getPrevObstacle().stop();
    }

    public void playAnimation() {
        ballTimer.play();
        gameTimer.getNextObstacle().move();
        if(gameTimer.getPrevObstacle() != null)
            gameTimer.getPrevObstacle().move();
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

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public AnimationTimer getLosingTimer() {
        return losingTimer;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public Obstacle getNextObstacle() {
        return nextObstacle;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public boolean isCollision() {
        return collision;
    }

    private void updateLevel() throws ClassNotFoundException {
        currentLevel = new Level(starScore/5+1);

        double dur = currentLevel.getObstacleDuration();
        obstacleList = new ArrayList<>();
        for(String obstacleName: currentLevel.getAvailableObstacles()) {
            switch (obstacleName) {
                case "CircleObstacle":
                    obstacleList.add(new CircleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.8 * dur, 0.0));
                    break;
                case "RectangleObstacle":
                    obstacleList.add(new RectangleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.6 * dur, 0.0));
                    break;
                case "EllipseObstacle":
                    obstacleList.add(new EllipseObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f, 50.0f}, dur, 0.0));
                    break;
                case "HexagonObstacle":
                    obstacleList.add(new HexagonObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.4 * dur, 0.0));
                    break;
                case "OneDLineObstacle":
                    obstacleList.add(new OneDLineObstacle(new float[]{250.0f, -65.0f}, new float[]{50.0f}, 0.6 * dur, 0.0));
                    break;
                case "TwoDLineObstacle":
                    obstacleList.add(new TwoDLineObstacle(new float[]{220.0f, -100.0f}, new float[]{50.0f}, 0.4 * dur, 0.0));
                    break;
                case "OctagonObstacle":
                    new OctagonObstacle(new float[]{250.0f, -100.0f}, new float[]{90.0f}, 0.2 * dur, 0.0);
                    break;
            }
        }

        ballTimer = new Timeline();
        ballTimer.setCycleCount(javafx.animation.Animation.INDEFINITE);
        KeyFrame moveBall = new KeyFrame(Duration.seconds(currentLevel.getFallTime()), event -> {
            ball.setCenterY(ball.getCenterY() + 3);
        });
        ballTimer.getKeyFrames().add(moveBall);
        ballTimer.play();
    }

    public Obstacle nextObstacle() {
        Random rand = new Random();
        nextObstacle = obstacleList.get(rand.nextInt(obstacleList.size()));
        double dur = currentLevel.getObstacleDuration();
        if (CircleObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new CircleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.8 * dur, 0.0);
        } else if (RectangleObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new RectangleObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.6 * dur, 0.0);
        } else if (EllipseObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new EllipseObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f, 50.0f}, dur, 0.0);
        } else if (HexagonObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new HexagonObstacle(new float[]{250.0f, -100.0f}, new float[]{70.0f}, 0.4 * dur, 0.0);
        } else if (OneDLineObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new OneDLineObstacle(new float[]{250.0f, -65.0f}, new float[]{50.0f}, 0.6 * dur, 0.0);
        } else if (TwoDLineObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new TwoDLineObstacle(new float[]{220.0f, -100.0f}, new float[]{50.0f}, 0.4 * dur, 0.0);
        } else if (OctagonObstacle.class.equals(nextObstacle.getClass())) {
            nextObstacle = new OctagonObstacle(new float[]{250.0f, -100.0f}, new float[]{90.0f}, 0.2 * dur, 0.0);
        }
        return nextObstacle;
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
