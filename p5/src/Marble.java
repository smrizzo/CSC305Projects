import java.awt.*;

public class Marble {
    public Marble[] adjMarbles = new Marble[6];
    private Integer x;
    private Integer y;
    private double pixelsHeight;
    private double pixelsWidth;

    private Piece player;

    private Color color;
    private Integer marbleID;
    private boolean hasMarble;
    private boolean pieceClicked = false;

    public Marble(Integer id, Integer y, Integer x, Piece player, boolean hasMarble) {
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

    public void setPlayer(Piece player) {
        this.player = player;
    }

    public void pieceClicked() {
        pieceClicked = true;
    }

    public Piece getPlayer() {
        return this.player;
    }

    public void setHasMarble(boolean hasMarble) {
        this.hasMarble = hasMarble;
    }

    public boolean getHasMarble() {
        return this.hasMarble;
    }

    public Integer getMarbleID() {
        return marbleID;
    }

    public Integer getMarbleX() {
        return x;
    }

    public Integer getMarbleY() {
        return y;
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
