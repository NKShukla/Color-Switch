package sample;

import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class CircleObstacle extends Obstacle {
    private Arc[] arcs;
    private Timeline[] animation;

    CircleObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);

        arcs = new Arc[4];
        animation = new Timeline[4];

        for (int i = 0; i < 4; i++) {
            arcs[i] = new Arc(pos[0], pos[1], dim[0], dim[0], Math.toDegrees(xAngle + i * Math.PI / 2), Math.PI * dim[0] / 2);
            arcs[i].setFill(Color.TRANSPARENT);
            arcs[i].setStroke(GameScreen.colors[i]);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setStrokeWidth(10.0);

            animation[i] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(arcs[i].startAngleProperty(), arcs[i].getStartAngle(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(arcs[i].startAngleProperty(), arcs[i].getStartAngle() + 360, Interpolator.LINEAR))
            );
            animation[i].setCycleCount(Animation.INDEFINITE);
        }
    }

    public CircleObstacle clone() {
        CircleObstacle circleObstacle = (CircleObstacle) super.clone();
        circleObstacle.arcs = this.arcs.clone();
        circleObstacle.animation = this.animation.clone();
        return circleObstacle;
    }

    public Node[] getParts() {
        return arcs;
    }

    public void move() {
        for (int i = 0; i < 4; i++)
            animation[i].play();
    }
}
