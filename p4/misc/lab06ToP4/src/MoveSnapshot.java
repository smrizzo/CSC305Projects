import java.awt.*;

public class MoveSnapshot {
    private Point fromXY;
    private Point toXY;
    private Piece state;
    private boolean leftHome;

    public MoveSnapshot(Point fromXY, Point toXY, Piece state) {
        this.fromXY = fromXY;
        this.toXY = toXY;
        this.state = state;
    }


    public Point getFromXY() {
        return fromXY;
    }

    public Point getToXY() {
        return toXY;
    }

    public Piece getState() {
        return state;
    }

}
