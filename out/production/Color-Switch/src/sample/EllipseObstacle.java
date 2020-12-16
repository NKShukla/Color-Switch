package sample;

import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class EllipseObstacle extends Obstacle{
    private Arc[] arcs;
    private Timeline[] animation;

    EllipseObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);

        arcs = new Arc[4];
        animation = new Timeline[4];

        for(int i = 0; i < 4; i++) {
            arcs[i] = new Arc(pos[0],pos[1],dim[0],dim[1],Math.toDegrees(xAngle+i*Math.PI/2),Math.PI*dim[0]/2);
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

    public EllipseObstacle clone() {
        EllipseObstacle ellipseObstacle = (EllipseObstacle) super.clone();
        ellipseObstacle.arcs = this.arcs.clone();
        ellipseObstacle.animation = this.animation.clone();
        return ellipseObstacle;
    }

    public Arc[] getParts() {
        return arcs;
    }

    public void move() {
        for(int i = 0; i < 4; i++)
            animation[i].play();
    }
}
