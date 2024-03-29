import java.awt.*;
import java.util.Random;

public class Circle {
    private Integer x;
    private Integer y;
    private double pixelsHeight;
    private double pixelsWidth;
    private Color color;
    private Color someColor;
    private boolean hasCircle;
    Random rand = new Random();

    public Circle(Integer y, Integer x, Color color, boolean hasCircle) {
        this.x = x;
        this.y = y;
        this.someColor = color;
        this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());

    }

    public void transformToPixels(double width, double height) {
        double pixelHeightTicks = (height /18);
        //System.out.println("pixel height ticks:" + pixelHeightTicks);
        double pixelWidthTicks = width /32;
        //System.out.println("pixel width ticks:" + pixelWidthTicks);
        this.pixelsHeight = pixelHeightTicks * y;
        //System.out.println("pixelsHeight" + this.pixelsHeight);
        this.pixelsWidth = pixelWidthTicks * x;
    }


    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setColors(Color color) {
        this.color = color;
    }

    public Integer getY() {

        return y;
    }

    public Color getColor() {
        return color;
    }

    public Integer getX() {
        return x;
    }

    public double getPixelsHeight() {
        return pixelsHeight;
    }

    public double getPixelsWidth() {
        return pixelsWidth;
    }

    public boolean isHasCircle() {
        return hasCircle;
    }
}
