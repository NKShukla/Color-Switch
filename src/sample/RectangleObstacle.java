package sample;

import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class RectangleObstacle extends Obstacle{
    private Line[] lines;
    private Timeline[][] animation;

    RectangleObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);
        lines = new Line[4];
        animation = new Timeline[4][4];

        float[][] vertices = new float[][]{
                {pos[0]+dim[0], pos[1]+dim[0], pos[0]+dim[0], pos[1]-dim[0]},
                {pos[0]+dim[0], pos[1]-dim[0], pos[0]-dim[0], pos[1]-dim[0]},
                {pos[0]-dim[0], pos[1]-dim[0], pos[0]-dim[0], pos[1]+dim[0]},
                {pos[0]-dim[0], pos[1]+dim[0], pos[0]+dim[0], pos[1]+dim[0]}
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
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), lines[(i+1)%4].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startXProperty(), lines[(i+2)%4].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startXProperty(), lines[(i+3)%4].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), lines[(i+1)%4].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startYProperty(), lines[(i+2)%4].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startYProperty(), lines[(i+3)%4].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), lines[(i+1)%4].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endXProperty(), lines[(i+2)%4].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endXProperty(), lines[(i+3)%4].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), lines[(i+1)%4].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endYProperty(), lines[(i+2)%4].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endYProperty(), lines[(i+3)%4].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endYProperty(), lines[i].getEndY(), Interpolator.LINEAR))
            );
            animation[i][0].setCycleCount(Animation.INDEFINITE);
            animation[i][1].setCycleCount(Animation.INDEFINITE);
            animation[i][2].setCycleCount(Animation.INDEFINITE);
            animation[i][3].setCycleCount(Animation.INDEFINITE);
        }
    }

    public RectangleObstacle clone() {
        RectangleObstacle rectangleObstacle = (RectangleObstacle) super.clone();
        rectangleObstacle.lines = this.lines.clone();
        rectangleObstacle.animation = this.animation.clone();
        return rectangleObstacle;
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
