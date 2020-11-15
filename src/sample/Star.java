package sample;

public class Star extends GameElements implements Animation{
    private int point;

    Star(float[] pos, float[] dim, int p) {

    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public void disappear() {

    }
}
