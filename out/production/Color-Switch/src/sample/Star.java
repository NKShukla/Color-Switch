package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Star extends GameElements implements Animation{
    private int point;
    private boolean intersected;
    private transient Image image;
    private transient ImageView iv1;

    Star(float[] pos, float[] dim, int p) throws FileNotFoundException {
        setPosition(pos);
        setDimensions(dim);
        intersected = false;
        image = new Image("file:src/sample/Images/star.png", dim[0], dim[0], false, false);
        iv1 = new ImageView();
        iv1.setLayoutX(pos[0]);
        iv1.setLayoutY(pos[1]);
        iv1.setImage(image);
        this.point = p;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        float[] pos = getPosition();
        float[] dim = getDimensions();
        image = new Image("file:src/sample/Images/star.png", dim[0], dim[0], false, false);
        iv1 = new ImageView();
        iv1.setLayoutX(pos[0]);
        iv1.setLayoutY(pos[1]);
        iv1.setImage(image);
    }

    public int getPoint() {
        return point;
    }

    public Node getStar() {
        return iv1;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public boolean getIntersected() {
        return intersected;
    }

    public void setIntersected(boolean status) {
        intersected = status;
    }

    @Override
    public void disappear() {
        getStar().setVisible(false);
    }
}
