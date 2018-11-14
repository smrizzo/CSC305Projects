import java.awt.*;
import java.util.Random;

public class Marble {
    private Marble[] adjacentMarbles = new Marble[5];
    private Integer x;
    private Integer y;
    private double pixelsHeight;
    private double pixelsWidth;
    private Color color;
    private Integer marbleID;
    private boolean hasMarble;
    private Integer player;
    Random rand = new Random();

    public Marble(Integer id, Integer y, Integer x, Integer player, boolean hasMarble) {
        this.marbleID = id;
        this.x = x;
        this.y = y;
        this.hasMarble = hasMarble;
        this.player = player;
    }

    public void transformToPixels(double width, double height) {
        double pixelHeightTicks = (height /18);
        double pixelWidthTicks = width /32;
        this.pixelsHeight = pixelHeightTicks * y;
        this.pixelsWidth = pixelWidthTicks * x;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

    public Integer getPlayer() {
        return this.player;
    }

    public void setHasMarble(boolean hasMarble) {
        this.hasMarble = hasMarble;
    }
    public Integer getMarbleID() {
        return marbleID;
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

    public Color getColor() {
        return color;
    }

    public double getPixelsHeight() {
        return pixelsHeight;
    }

    public double getPixelsWidth() {
        return pixelsWidth;
    }

}
