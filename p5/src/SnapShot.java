import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class SnapShot {

    private Marble[][] marbles;
    private StarModel model;
    private Point fromXY;
    private Point toXY;
    private MoveSnapshot move;
    private Piece state;
    private Stack<MoveSnapshot> moves;
    private List<MoveSnapshot> moveList;


    public SnapShot(StarModel model) {
        this.fromXY = new Point(model.getFromXY().x, model.getFromXY().y);
        this.toXY = new Point(model.getToXY().x, model.getToXY().y);
        this.state = model.getState();
        this.marbles = new Marble[17][25];
        this.model = new StarModel();

        if(!model.getMoveHistory().isEmpty()) {
            this.moveList = new ArrayList<>(model.getMoveHistory());

            for(MoveSnapshot move: moveList) {
                this.model.setFromXY(move.getFromXY().x, move.getFromXY().y);
                this.model.setToXY(move.getToXY().x, move.getToXY().y);
                this.model.regularMove();
            }
        }
    }

    public Marble[][] getMarbles() {
        return this.model.getMarbles();
    }

    public Point getFromXY() {
        return this.model.getFromXY();
    }

    public Point getToXY() {
        return this.model.getToXY();
    }

    public MoveSnapshot getMove() {
        return move;
    }

    public Piece getState() {
        return this.model.getState();
    }

    public StarModel getModel() {
        return this.model;
    }
}
