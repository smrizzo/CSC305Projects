import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessController implements ViewObserver{

    private ChessModel model;
    private ChessView view;
    private boolean initialClick;
    private boolean pieceClicked;
    private boolean madeMove;
    protected boolean pawnMadeIt;
    private int firstClickX;
    private int firstClicky;
    protected boolean mWhiteBoard;
    protected boolean mBlackBoard;
    private int numberOfBlackBoards;
    private int numberOfWhiteBoards;
    private int numberOfKibbitzers;
    protected boolean connectToServer;
    protected String ipAddress;
    protected String port;
    protected String mySessionId;
    Piece[][] pieces;
    List<Thread> threads = new ArrayList<>();

    public ChessController(ChessModel model, int numberOfBlackBoards, int numberOfWhiteBoards, int numberOfKibbitzers) throws IOException {
        this.model = model;
        this.pieces = model.getPieces();
        model.registerObserver(this);
        this.numberOfBlackBoards = numberOfBlackBoards;
        this.numberOfWhiteBoards = numberOfWhiteBoards;
        this.numberOfKibbitzers = numberOfKibbitzers;
        this.initialClick = false;
        this.pieceClicked = false;
        this.madeMove = false;
        this.pawnMadeIt = false;

        if(numberOfBlackBoards > 0) {
            for(int i = 0; i < numberOfBlackBoards; i++) {
                addView(false, true, false);
            }
        }

        if(numberOfWhiteBoards > 0) {
            for(int i = 0; i < numberOfWhiteBoards; i++) {
                addView(false, false, true);
            }
        }

        if(numberOfKibbitzers > 0) {
            for(int i = 0; i < numberOfKibbitzers; i++) {
                addView(true, false, false);
            }
        }
        runThreads();
    }


    public void addView(boolean kibbitzer, boolean blackBoard, boolean whiteBoard) throws IOException {
        threads.add(new Thread(new ChessView(kibbitzer, model, this, blackBoard, whiteBoard)));
    }
    public void runThreads() {
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    public void setWhiteBoard() {
        this.mWhiteBoard = true;
        this.mBlackBoard = false;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMySessionId(String sessionId) {
        this.mySessionId = sessionId;
    }

    public void setBlackBoard() {
        this.mBlackBoard = true;
        this.mWhiteBoard = false;
    }

    public void clickedPiece(int x, int y) throws CloneNotSupportedException {
      System.out.println("We need to do something with this click: " + x + ", " + y);
        if(pieces[x][y] != null && !initialClick && !pieceClicked) {
            System.out.println("Initialclick");
            if((y == 1 && pieces[x][y] instanceof WhitePawnPiece)) {
                model.promoteWhitePawn();
                pawnMadeIt = true;
            } else if(y == 8 && pieces[x][y] instanceof BlackPawnPiece) {
                model.promoteBlackPawn();
                pawnMadeIt = true;
            }
            model.setFromXY(x, y);
            initialClick = true;
            pieceClicked = true;
            firstClickX = x;
            firstClicky = y;
            //System.out.println("first click: " + firstClickX + ", " + firstClicky);

        } else if(pieceClicked && pieces[x][y] == null && x != 0 && y != 0 && !pawnMadeIt) {
            System.out.println("Making regular Move");
            model.setToXY(x, y);
            model.regularMove();
            pieceClicked = false;
            initialClick = false;
        } else if(pieceClicked && pieces[x][y] != null && !pawnMadeIt) {
            model.setToXY(x, y);
            if(model.moveAndtakePiece()) {
                initialClick = false;
                pieceClicked = false;
            }

        } else if(pieceClicked && initialClick && x == this.firstClickX && y == this.firstClicky) {
            pieceClicked = true;
            initialClick = true;
            //System.out.println("Got here");

        } else if (pieceClicked && pawnMadeIt && y == 0 && (x >= 1 && x <= 4)){
            model.setToXY(x, y);
            model.promotingPawn();
            pawnMadeIt = false;
            pieceClicked = false;
            initialClick = false;
        } else {
            pieceClicked = false;
        }
    }


    @Override
    public void notifyBoardUpdate(ChessModel m) {
        this.model = m;
        this.pieces = m.getPieces();
        initialClick = false;
        pieceClicked = false;
        madeMove = false;
    }
}
