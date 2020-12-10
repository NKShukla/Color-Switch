package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class Star extends GameElements implements Animation{
    private int point;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
    private boolean intersected = false;

    Image image;
    ImageView iv1;

    Star(float[] pos, float[] dim, int p) throws FileNotFoundException {
        image = new Image("file:src/sample/Images/star.png", 25, 25, false, false);
        iv1 = new ImageView();
        iv1.setLayoutX(pos[0]);
        iv1.setLayoutY(pos[1]);
//        iv1.setLayoutX(235);
//        iv1.setLayoutY(135);
        iv1.setImage(image);
        this.point = p;
    }

    public int getPoint() {
        return point;
    }

    public Node getStar() {
        return iv1;
    }

    public Image getImage() {
        return image;
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
