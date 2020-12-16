package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OneDLineObstacle extends AbstractLineObstacle{
    private transient Line[] lines;
    private transient Timeline[][] animation;

    OneDLineObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);
        lines = new Line[4];
        animation = new Timeline[6][4];

        float[][] vertices = new float[][]{
                {pos[0]+4*dim[0]+30, pos[1], pos[0]+3*dim[0]+30, pos[1]},
                {pos[0]+3*dim[0]+20, pos[1], pos[0]+2*dim[0]+20, pos[1]},
                {pos[0]+2*dim[0]+10, pos[1], pos[0]+dim[0]+10, pos[1]},
                {pos[0]+dim[0], pos[1], pos[0], pos[1]},
                {pos[0], pos[1], pos[0]-dim[0], pos[1]},
                {pos[0]-dim[0]-10, pos[1], pos[0]-2*dim[0]-10, pos[1]},
                {pos[0]-2*dim[0]-20, pos[1], pos[0]-3*dim[0]-20, pos[1]},
                {pos[0]-3*dim[0]-30, pos[1], pos[0]-4*dim[0]-30, pos[1]}
        };
        lines[0] = new Line(vertices[0][0], vertices[0][1], vertices[0][2], vertices[0][3]);
        lines[1] = new Line(vertices[1][0], vertices[1][1], vertices[1][2], vertices[1][3]);
        lines[2] = new Line(vertices[2][0], vertices[2][1], vertices[2][2], vertices[2][3]);
        lines[3] = new Line(vertices[3][0], vertices[3][1], vertices[3][2], vertices[3][3]);

        for(int i = 0; i < 4; i++) {
            lines[i].setFill(Color.TRANSPARENT);
            lines[i].setStrokeWidth(10.0);
            lines[i].setStroke(GameScreen.colors[i]);

            animation[i][0] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), vertices[i][0], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), vertices[(i+4)%8][0], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startXProperty(), vertices[i][0], Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), vertices[i][1], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), vertices[(i+4)%8][1], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startYProperty(), vertices[i][1], Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), vertices[i][2], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), vertices[(i+4)%8][2], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endXProperty(), vertices[i][2], Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), vertices[i][3], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), vertices[(i+4)%8][3], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endYProperty(), vertices[i][3], Interpolator.LINEAR))
            );
            animation[i][0].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][1].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][2].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][3].setCycleCount(javafx.animation.Animation.INDEFINITE);
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(animation[0][0].getCycleDuration().toSeconds());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        float[] pos = getPosition();
        float[] dim = getDimensions();
        double dur = (double) ois.readObject();

        lines = new Line[4];
        animation = new Timeline[6][4];

        float[][] vertices = new float[][]{
                {pos[0]+4*dim[0]+30, pos[1], pos[0]+3*dim[0]+30, pos[1]},
                {pos[0]+3*dim[0]+20, pos[1], pos[0]+2*dim[0]+20, pos[1]},
                {pos[0]+2*dim[0]+10, pos[1], pos[0]+dim[0]+10, pos[1]},
                {pos[0]+dim[0], pos[1], pos[0], pos[1]},
                {pos[0], pos[1], pos[0]-dim[0], pos[1]},
                {pos[0]-dim[0]-10, pos[1], pos[0]-2*dim[0]-10, pos[1]},
                {pos[0]-2*dim[0]-20, pos[1], pos[0]-3*dim[0]-20, pos[1]},
                {pos[0]-3*dim[0]-30, pos[1], pos[0]-4*dim[0]-30, pos[1]}
        };
        lines[0] = new Line(vertices[0][0], vertices[0][1], vertices[0][2], vertices[0][3]);
        lines[1] = new Line(vertices[1][0], vertices[1][1], vertices[1][2], vertices[1][3]);
        lines[2] = new Line(vertices[2][0], vertices[2][1], vertices[2][2], vertices[2][3]);
        lines[3] = new Line(vertices[3][0], vertices[3][1], vertices[3][2], vertices[3][3]);

        for(int i = 0; i < 4; i++) {
            lines[i].setFill(Color.TRANSPARENT);
            lines[i].setStrokeWidth(10.0);
            lines[i].setStroke(GameScreen.colors[i]);

            animation[i][0] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), vertices[i][0], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), vertices[(i+4)%8][0], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startXProperty(), vertices[i][0], Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), vertices[i][1], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), vertices[(i+4)%8][1], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startYProperty(), vertices[i][1], Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), vertices[i][2], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), vertices[(i+4)%8][2], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endXProperty(), vertices[i][2], Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), vertices[i][3], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), vertices[(i+4)%8][3], Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endYProperty(), vertices[i][3], Interpolator.LINEAR))
            );
            animation[i][0].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][1].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][2].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][3].setCycleCount(javafx.animation.Animation.INDEFINITE);
        }
    }

    public OneDLineObstacle clone() {
        OneDLineObstacle oneDLineObstacle = (OneDLineObstacle) super.clone();
        oneDLineObstacle.lines = this.lines.clone();
        oneDLineObstacle.animation = this.animation.clone();
        return oneDLineObstacle;
    }

    public Line[] getParts() {
        return lines;
    }

    public void move() {
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                animation[i][j].play();
    }
}
