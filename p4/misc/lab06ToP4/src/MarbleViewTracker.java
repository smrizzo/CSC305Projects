import java.awt.*;
import java.awt.geom.Ellipse2D;

public class MarbleViewTracker {
    private Ellipse2D myShape;
    private Color marbleColor;
    private Point point;
    public MarbleViewTracker(Ellipse2D myShape, Color color, Point point) {
        this.myShape = myShape;
        this.marbleColor = color;
        this.point = point;
    }

    public Ellipse2D getMyShape() {
        return myShape;
    }

    public Color getColor() {
        return marbleColor;
    }

    public Point getPoint() {
        return point;
    }
}
