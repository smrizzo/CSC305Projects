import java.awt.*;

public class MoveSnapshot {
    private Point fromXY;
    private Point toXY;
    private Piece state;
    private boolean leftHome;
    private boolean enteredAHome;

    public MoveSnapshot(Point fromXY, Point toXY, Piece state, boolean leftHome, boolean enteredAHome) {
        this.fromXY = fromXY;
        this.toXY = toXY;
        this.state = state;
        this.leftHome = leftHome;
        this.enteredAHome = enteredAHome;
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

    public boolean isLeftHome() {
        return leftHome;
    }

    public boolean isEnteredAHome() {
        return enteredAHome;
    }
}
