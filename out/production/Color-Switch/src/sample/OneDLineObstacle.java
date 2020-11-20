package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class OneDLineObstacle extends AbstractLineObstacle{
    private final Line[] lines;
    private final Timeline[][] animation;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    OneDLineObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        lines = new Line[4];
        Line[] tempLines = new Line[6];
        animation = new Timeline[6][4];

        float[][] vertices = new float[][]{
                {pos[0]+3*dim[0], pos[1], pos[0]+2*dim[0], pos[1]},
                {pos[0]+2*dim[0], pos[1], pos[0]+dim[0], pos[1]},
                {pos[0]+dim[0], pos[1], pos[0], pos[1]},
                {pos[0], pos[1], pos[0]-dim[0], pos[1]},
                {pos[0]-dim[0], pos[1], pos[0]-2*dim[0], pos[1]},
                {pos[0]-2*dim[0], pos[1], pos[0]-3*dim[0], pos[1]}
        };
        lines[0] = new Line(vertices[1][0], vertices[1][1], vertices[1][2], vertices[1][3]);
        lines[1] = new Line(vertices[2][0], vertices[2][1], vertices[2][2], vertices[2][3]);
        lines[2] = new Line(vertices[3][0], vertices[3][1], vertices[3][2], vertices[3][3]);
        lines[3] = new Line(vertices[4][0], vertices[4][1], vertices[4][2], vertices[4][3]);

        tempLines[0] = new Line(vertices[0][0], vertices[0][1], vertices[0][2], vertices[0][3]);
        tempLines[1] = lines[0];
        tempLines[2] = lines[1];
        tempLines[3] = lines[2];
        tempLines[4] = lines[3];
        tempLines[5] = new Line(vertices[5][0], vertices[5][1], vertices[5][2], vertices[5][3]);

        for(int i = 0; i < 4; i++) {
            lines[i].setFill(Color.TRANSPARENT);
            lines[i].setStrokeWidth(10.0);
            lines[i].setStroke(colors[i]);

            animation[i][0] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), tempLines[i].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), lines[(i+1)%4].getStartX(), Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), lines[(i+1)%4].getStartY(), Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), lines[(i+1)%4].getEndX(), Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), lines[(i+1)%4].getEndY(), Interpolator.LINEAR))
            );
            animation[i][0].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][1].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][2].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][3].setCycleCount(javafx.animation.Animation.INDEFINITE);
        }
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
