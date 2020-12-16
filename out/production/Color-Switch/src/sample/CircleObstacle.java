package sample;

import javafx.animation.Animation;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CircleObstacle extends Obstacle {
    private transient Arc[] arcs;
    private transient Timeline[] animation;

    CircleObstacle(float[] pos, float[] dim, double dur, double xAngle) {
        setPosition(pos);
        setDimensions(dim);
        setAngle(xAngle);

        arcs = new Arc[4];
        animation = new Timeline[4];

        for (int i = 0; i < 4; i++) {
            arcs[i] = new Arc(pos[0], pos[1], dim[0], dim[0], Math.toDegrees(getAngle() + i * Math.PI / 2), Math.PI * dim[0] / 2);
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

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(animation[0].getCycleDuration().toSeconds()-2);
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        double dur = (double) ois.readObject();
        float[] pos = getPosition();
        float[] dim = getDimensions();

        arcs = new Arc[4];
        animation = new Timeline[4];

        for (int i = 0; i < 4; i++) {
            arcs[i] = new Arc(pos[0], pos[1], dim[0], dim[0], Math.toDegrees(getAngle() + i * Math.PI / 2), Math.PI * dim[0] / 2);
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

    public Node[] getParts() {
        return arcs;
    }

    public void move() {
        for (int i = 0; i < 4; i++)
            animation[i].play();
    }

    public void stop() {
        for (int i = 0; i < 4; i++)
            animation[i].pause();
    }
}
