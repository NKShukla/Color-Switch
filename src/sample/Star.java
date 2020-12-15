package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;

public class Star extends GameElements implements Animation{
    private int point;
    protected final Color[] colors = new Color[] {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};

    Image image;
    ImageView iv1;

    Star(float[] pos, float[] dim, int p) throws FileNotFoundException {
        image = new Image("file:src/sample/Images/star.png", 25, 25, false, false);
        iv1 = new ImageView();
        iv1.setLayoutX(pos[0]);
        iv1.setLayoutY(pos[1]);
        iv1.setImage(image);
        this.point = p;
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

    @Override
    public void disappear() {

    }
}
