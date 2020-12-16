package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class OctagonObstacle extends PolygonObstacle{
    private Line[] lines;
    private Timeline[][] animation;

    OctagonObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);
        lines = new Line[8];
        animation = new Timeline[8][4];

        float[][] vertices = new float[][]{
                {pos[0]+dim[0], pos[1], pos[0]+dim[0]/2, pos[1]-dim[0]/2},
                {pos[0]+dim[0]/2-2.5f, pos[1]-dim[0]/2-2.5f, pos[0]-2.5f, pos[1]-dim[0]-2.5f},
                {pos[0]-5, pos[1]-dim[0]-5, pos[0]-dim[0]/2-5, pos[1]-dim[0]/2-5},
                {pos[0]-dim[0]/2-7.5f,pos[1]-dim[0]/2-2.5f, pos[0]-dim[0]-7.5f, pos[1]-2.5f},
                {pos[0]-dim[0]-10, pos[1], pos[0]-dim[0]/2-10, pos[1]+dim[0]/2},
                {pos[0]-dim[0]/2-7.5f, pos[1]+dim[0]/2+2.5f, pos[0]-7.5f, pos[1]+dim[0]+2.5f},
                {pos[0]-5, pos[1]+dim[0]+5, pos[0]+dim[0]/2-5, pos[1]+dim[0]/2+5},
                {pos[0]+dim[0]/2-2.5f, pos[1]+dim[0]/2+2.5f, pos[0]+dim[0]-2.5f, pos[1]+2.5f}
        };
        lines[0] = new Line(vertices[0][0], vertices[0][1], vertices[0][2], vertices[0][3]);
        lines[1] = new Line(vertices[1][0], vertices[1][1], vertices[1][2], vertices[1][3]);
        lines[2] = new Line(vertices[2][0], vertices[2][1], vertices[2][2], vertices[2][3]);
        lines[3] = new Line(vertices[3][0], vertices[3][1], vertices[3][2], vertices[3][3]);
        lines[4] = new Line(vertices[4][0], vertices[4][1], vertices[4][2], vertices[4][3]);
        lines[5] = new Line(vertices[5][0], vertices[5][1], vertices[5][2], vertices[5][3]);
        lines[6] = new Line(vertices[6][0], vertices[6][1], vertices[6][2], vertices[6][3]);
        lines[7] = new Line(vertices[7][0], vertices[7][1], vertices[7][2], vertices[7][3]);

        for(int i = 0; i < 8; i++) {
            lines[i].setFill(Color.TRANSPARENT);
            lines[i].setStroke(GameScreen.colors[i%4]);
            lines[i].setStrokeWidth(10.0);

            animation[i][0] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startXProperty(), lines[(i+1)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startXProperty(), lines[(i+2)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startXProperty(), lines[(i+3)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startXProperty(), lines[(i+4)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].startXProperty(), lines[(i+5)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].startXProperty(), lines[(i+6)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(7*dur), new KeyValue(lines[i].startXProperty(), lines[(i+7)%8].getStartX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(8*dur), new KeyValue(lines[i].startXProperty(), lines[i].getStartX(), Interpolator.LINEAR))
            );
            animation[i][1] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].startYProperty(), lines[(i+1)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].startYProperty(), lines[(i+2)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].startYProperty(), lines[(i+3)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].startYProperty(), lines[(i+4)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].startYProperty(), lines[(i+5)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].startYProperty(), lines[(i+6)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(7*dur), new KeyValue(lines[i].startYProperty(), lines[(i+7)%8].getStartY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(8*dur), new KeyValue(lines[i].startYProperty(), lines[i].getStartY(), Interpolator.LINEAR))
            );
            animation[i][2] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endXProperty(), lines[(i+1)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endXProperty(), lines[(i+2)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endXProperty(), lines[(i+3)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endXProperty(), lines[(i+4)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].endXProperty(), lines[(i+5)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].endXProperty(), lines[(i+6)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(7*dur), new KeyValue(lines[i].endXProperty(), lines[(i+7)%8].getEndX(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(8*dur), new KeyValue(lines[i].endXProperty(), lines[i].getEndX(), Interpolator.LINEAR))
            );
            animation[i][3] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(lines[i].endXProperty(), lines[i].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(lines[i].endYProperty(), lines[(i+1)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(2*dur), new KeyValue(lines[i].endYProperty(), lines[(i+2)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(3*dur), new KeyValue(lines[i].endYProperty(), lines[(i+3)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(4*dur), new KeyValue(lines[i].endYProperty(), lines[(i+4)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(5*dur), new KeyValue(lines[i].endYProperty(), lines[(i+5)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(6*dur), new KeyValue(lines[i].endYProperty(), lines[(i+6)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(7*dur), new KeyValue(lines[i].endYProperty(), lines[(i+7)%8].getEndY(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(8*dur), new KeyValue(lines[i].endYProperty(), lines[i].getEndY(), Interpolator.LINEAR))
            );
            animation[i][0].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][1].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][2].setCycleCount(javafx.animation.Animation.INDEFINITE);
            animation[i][3].setCycleCount(javafx.animation.Animation.INDEFINITE);
        }
    }

    public OctagonObstacle clone() {
        OctagonObstacle octagonObstacle = (OctagonObstacle) super.clone();
        octagonObstacle.lines = this.lines.clone();
        octagonObstacle.animation = this.animation.clone();
        return octagonObstacle;
    }

    public Line[] getParts() {
        return lines;
    }

    public void move() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 4; j++)
                animation[i][j].play();
    }
}
