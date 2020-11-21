package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class HexagonObstacle extends PolygonObstacle{
    private final Line[] lines;
    private final Timeline[][] animation;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    HexagonObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        lines = new Line[6];
        animation = new Timeline[6][4];

        float[][] vertices = new float[][]{
                {pos[0]+dim[0], pos[1], pos[0]+dim[0]/2, pos[1]-(float)Math.sqrt(3)*dim[0]/2},
                {pos[0]+dim[0]/2-2.5f, pos[1]-(float)Math.sqrt(3)*dim[0]/2-2.5f, pos[0]-dim[0]/2-2.5f, pos[1]-(float)Math.sqrt(3)*dim[0]/2-2.5f},
                {pos[0]-dim[0]/2-5, pos[1]-(float)Math.sqrt(3)*dim[0]/2, pos[0]-dim[0]-5, pos[1]},
                {pos[0]-dim[0]-2.5f,pos[1]+2.5f, pos[0]-dim[0]/2-2.5f, pos[1]+(float)Math.sqrt(3)*dim[0]/2+2.5f},
                {pos[0]-dim[0]/2, pos[1]+(float)Math.sqrt(3)*dim[0]/2+5, pos[0]+dim[0]/2, pos[1]+(float)Math.sqrt(3)*dim[0]/2+5},
                {pos[0]+dim[0]/2+2.5f, pos[1]+(float)Math.sqrt(3)*dim[0]/2+2.5f, pos[0]+dim[0]+2.5f, pos[1]+2.5f}
        };
        lines[0] = new Line(vertices[0][0], vertices[0][1], vertices[0][2], vertices[0][3]);
        lines[1] = new Line(vertices[1][0], vertices[1][1], vertices[1][2], vertices[1][3]);
        lines[2] = new Line(vertices[2][0], vertices[2][1], vertices[2][2], vertices[2][3]);
        lines[3] = new Line(vertices[3][0], vertices[3][1], vertices[3][2], vertices[3][3]);
        lines[4] = new Line(vertices[4][0], vertices[4][1], vertices[4][2], vertices[4][3]);
        lines[5] = new Line(vertices[5][0], vertices[5][1], vertices[5][2], vertices[5][3]);

        lines[0].setStroke(colors[0]);
        lines[1].setStroke(colors[1]);
        lines[2].setStroke(colors[2]);
        lines[3].setStroke(colors[0]);
        lines[4].setStroke(colors[1]);
        lines[5].setStroke(colors[3]);

        for(int i = 0; i < 6; i++) {
            lines[i].setFill(Color.TRANSPARENT);
            lines[i].setStrokeWidth(10.0);

            animation[i][0] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), lines[(i+1)%6].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startXProperty(), lines[(i+2)%6].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startXProperty(), lines[(i+3)%6].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startXProperty(), lines[(i+4)%6].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].startXProperty(), lines[(i+5)%6].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), lines[(i+1)%6].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startYProperty(), lines[(i+2)%6].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startYProperty(), lines[(i+3)%6].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startYProperty(), lines[(i+4)%6].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].startYProperty(), lines[(i+5)%6].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), lines[(i+1)%6].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endXProperty(), lines[(i+2)%6].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endXProperty(), lines[(i+3)%6].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endXProperty(), lines[(i+4)%6].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].endXProperty(), lines[(i+5)%6].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), lines[(i+1)%6].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endYProperty(), lines[(i+2)%6].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endYProperty(), lines[(i+3)%6].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endYProperty(), lines[(i+4)%6].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].endYProperty(), lines[(i+5)%6].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].endYProperty(), lines[i].getEndY(), Interpolator.LINEAR))
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
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 4; j++)
                animation[i][j].play();
    }
}
