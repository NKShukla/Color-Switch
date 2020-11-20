package sample;

import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class CircleObstacle extends Obstacle{
    private final Arc[] arcs;
    private final Timeline[] animation;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    CircleObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        arcs = new Arc[4];
        animation = new Timeline[4];

        for(int i = 0; i < 4; i++) {
            arcs[i] = new Arc(pos[0],pos[1],dim[0],dim[0],Math.toDegrees(xAngle+i*Math.PI/2),Math.PI*dim[0]/2);
            arcs[i].setFill(Color.TRANSPARENT);
            arcs[i].setStroke(colors[i]);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setStrokeWidth(10.0);

            animation[i] = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(arcs[i].startAngleProperty(), arcs[i].getStartAngle(), Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(dur), new KeyValue(arcs[i].startAngleProperty(), arcs[i].getStartAngle() + 360, Interpolator.LINEAR))
            );
            animation[i].setCycleCount(Animation.INDEFINITE);
        }
    }

    public Arc[] getParts() {
        return arcs;
    }

    public void move() {
        for(int i = 0; i < 4; i++)
            animation[i].play();
    }
}
