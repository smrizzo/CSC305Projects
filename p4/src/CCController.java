import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.*;
import java.util.concurrent.locks.ReentrantLock;

public class CCController implements CCViewObserver{

    private List<Ellipse2D> marbleShapes = new ArrayList<>();
    private List<MarbleViewTracker> marbleViewTracker = new ArrayList<>();
    private HashMap<Integer,MarbleViewTracker> marbleMapTracker = new HashMap<>();
    private List<StarUI> starUIS = new ArrayList<>();
    protected boolean initialClick = false;
    private boolean pieceClicked = false;
    protected boolean gotAllMoves = false;
    private boolean gameIsOver = false;
    private Thread myModelThread;
    private ReentrantLock lock = new ReentrantLock();
    //List<Point> listOfMoves;
    HashMap<Point, Color> listOfMoves;

    private CCMove movePiece;

    private StarModel model;
    private Marble[][] marbles;

    public CCController(StarModel model, int numOfUI, String keyName) {
        this.model = model;
        this.marbles = model.getMarbles();
        movePiece = new CCMove(this.model);
        model.registerCCViewObserver(this);

        for(int i = 0; i < numOfUI; i++) {
            starUIS.add(new StarUI(this, model ,keyName));
        }
        for(int i = 0; i < numOfUI; i++) {
            starUIS.get(i).setFrameVisible(true);
        }

        myModelThread = new Thread(model);
        myModelThread.start();

    }

    public void addMarbleTracker(MarbleViewTracker marble) {
        marbleViewTracker.add(marble);
    }

    public void foundClick(int x, int y) {
        if(!gameIsOver) {
            for(MarbleViewTracker marble: marbleViewTracker) {
                if(doesContain(marble,x,y) && isPlayersTurn(marble.getPoint().x, marble.getPoint().y)) {

                    model.setFromXY(marble.getPoint().x, marble.getPoint().y);
                    if(model.hasMoves()) {
                        model.getAllMoves();
                        gotAllMoves = true;
                        pieceClicked = true;
                    }


                    break;

                } else if (doesContain(marble, x, y) && !isPlayersTurn(marble.getPoint().x, marble.getPoint().y) && pieceClicked) {
                    model.setToXY(marble.getPoint().x, marble.getPoint().y);

                    if(model.validMove()) {
                        gotAllMoves = false;
                        movePiece.execute();
                        pieceClicked = false;
                    }
                    break;

                }
            }
        }
    }


    public void buttonClicked(String command) {
        lock.lock();
        try {
            gotAllMoves = false;
            pieceClicked = false;
            movePiece.undo();
        } finally {
            lock.unlock();
        }

    }

    public boolean isPlayersTurn(int x, int y) {
        return (marbles[y][x].getHasMarble()) && (marbles[y][x].getPlayer().getText() == model.getState().getText());

    }

    public boolean doesContain(MarbleViewTracker marble, int x, int y) {
        return marble.getMyShape().contains(x, y - marble.getMyShape().getHeight());
    }


    @Override
    public void updateStar(StarModel m) {
        this.model = m;
        this.marbles = m.getMarbles();
        this.gameIsOver = m.getIfGameIsOver();
        movePiece = new CCMove(this.model);
    }


}
