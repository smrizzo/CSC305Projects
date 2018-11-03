import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChessController implements ViewObserver{

    private ChessModel model;
    //private ChessView view;
    protected boolean initialClick;
    private boolean pieceClicked;
    private boolean madeMove;
    protected boolean pawnMadeIt;
    protected int firstClickX;
    protected int firstClicky;
    protected boolean mWhiteBoard;
    protected boolean mBlackBoard;
    private int numberOfBlackBoards;
    private int numberOfWhiteBoards;
    private int numberOfKibbitzers;
    protected boolean connectToServer;
    Piece[][] pieces;
    List<Thread> threads = new ArrayList<>();

    private Character[][] piecesFromServer = new Character[9][9];
    private String ipAddress;
    private Integer port;
    private String SessionID;
    private Socket socket;
    Thread write;
    Thread readFrom;
    private WriteToServer writeToServer;
    private ReadFromServer readFromServer;
    List<String> myInitialList;
    List<String> myList;
    boolean kibbitzerConnected = false;
    boolean remoteGameConnected = false;

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
            //this.mBlackBoard = true;
            setBlackBoard();
            for(int i = 0; i < numberOfBlackBoards; i++) {
                //addView(false,this.mWhiteBoard, this.mBlackBoard);
                addBlackView();
            }
        }

        if(numberOfWhiteBoards > 0) {
//            this.mWhiteBoard = true;
            setWhiteBoard();
            for(int i = 0; i < numberOfWhiteBoards; i++) {
                //addView(false, this.mWhiteBoard, this.mBlackBoard);
                addWhiteview();
            }
        }

        if(numberOfKibbitzers > 0) {
            for(int i = 0; i < numberOfKibbitzers; i++) {
                //addView(true, this.mWhiteBoard, this.mBlackBoard);
                addKibbitzerView();
            }
        }
        runThreads();
    }

    public ChessController(ChessModel model, String ipAddress, Integer port, String SessionID) throws IOException, InterruptedException, CloneNotSupportedException {
        this.model = model;
        this.pieces = model.getPieces();
        this.ipAddress = ipAddress;
        this.port = port;
        model.registerObserver(this);
        this.initialClick = false;
        this.pieceClicked = false;
        this.madeMove = false;
        this.pawnMadeIt = false;
        this.SessionID = SessionID;
        socket = new Socket(ipAddress, port);
        remoteGameConnected = true;
        writeToServer = new WriteToServer(socket, this.SessionID);
        readFromServer = new ReadFromServer(socket);
        write = new Thread(writeToServer);
        readFrom = new Thread(readFromServer);
        write.start();
        readFrom.start();
        InitialResponse();
        getResponseFromServer();

    }

    public void InitialResponse() throws InterruptedException, IOException, CloneNotSupportedException {
        myInitialList = readFromServer.getInitialResponse();
        if(myInitialList.get(1).charAt(0) == '\0') {
            System.out.println("We found a kibbitzer");
            kibbitzerConnected = true;
        } else if(myInitialList.get(1).charAt(0) == 'w') {
            setWhiteBoard();
            System.out.println("We are gonna be White Player");
        } else if(myInitialList.get(1).charAt(0) == 'b') {
            setBlackBoard();
            System.out.println("We are gonna be Black Player");
        }
        for(int i = 0; i < myInitialList.size(); i++) {
            //System.out.println("Item returned from intial list:" + myInitialList.get(i));
            System.out.println(myInitialList.get(i));
        }
        System.out.print("\n");
        parseBoard(myInitialList.get(3));
        if(kibbitzerConnected) {
            addKibbitzerView();
        } else if (mWhiteBoard) {
            addWhiteview();
        } else {
            addBlackView();
        }
        //addView(kibbitzerConnected, mWhiteBoard, mBlackBoard);
        runThreads();
        model.setServerBoard(piecesFromServer);


    }

    public void getResponseFromServer() throws InterruptedException, CloneNotSupportedException {

        while(true) {

            myList = readFromServer.getResponse();
            if(myList.get(0).equals("board")) {
                parseBoard(myList.get(1));
                model.setServerBoard(piecesFromServer);

            }

            for(int i = 0; i < myList.size(); i++) {
                //System.out.println("Item returned from list:" + myList.get(i));
                System.out.println(myList.get(i));
            }

        }
    }

    public void parseBoard(String board) {
        String getCol;
        int beginIndex = 0;
        int endIndex = 8;
        int colCounter = 0;
        while(colCounter < 9 && endIndex <= board.length()) {
            getCol = board.substring(beginIndex, endIndex);
            for(int i = 0; i < getCol.length(); i++) {
                piecesFromServer[colCounter + 1][i + 1] = getCol.charAt(i);
            }

            beginIndex += 8;
            endIndex += 8;
            colCounter++;
        }

    }


    private void addBlackView() {
        threads.add(new Thread(new BlackView(model, this)));
    }

    private void addWhiteview() {
        threads.add(new Thread(new WhiteView(false,model, this)));
    }

    private void addKibbitzerView() {
        threads.add(new Thread(new WhiteView(true,model, this)));
    }

    private void runThreads() {
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
    }

    private void setWhiteBoard() {
        this.mWhiteBoard = true;
        this.mBlackBoard = false;
    }

    private void setBlackBoard() {
        this.mBlackBoard = true;
        this.mWhiteBoard = false;
    }

    public void clickedPiece(int x, int y) throws CloneNotSupportedException, IOException {
      //System.out.println("We need to do something with this click: " + x + ", " + y);
        if(pieces[x][y] != null && !initialClick && !pieceClicked) {
            //System.out.println("Initialclick");
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

        } else if(pieceClicked && pieces[x][y] == null && x != 0 && y != 0 && !pawnMadeIt) {
            model.setToXY(x, y);
            if(!remoteGameConnected) {
                model.regularMove();
                pieceClicked = false;
                initialClick = false;
            } else {
                writeToServer.serverMove(model.getFromX() - 1, model.getFromY() -1, model.getToX() - 1, model.getToY() - 1);
                pieceClicked = false;
                initialClick = false;
            }

        } else if(pieceClicked && pieces[x][y] != null && !pawnMadeIt) {
            model.setToXY(x, y);
            if(!remoteGameConnected) {
                if(model.moveAndtakePiece()) {
                    initialClick = false;
                    pieceClicked = false;
                }
            } else if(remoteGameConnected){
                writeToServer.serverMove(model.getFromX() - 1, model.getFromY() -1, model.getToX() - 1, model.getToY() - 1);
                initialClick = false;
                pieceClicked = false;
            }


        } else if(pieceClicked && initialClick && x == this.firstClickX && y == this.firstClicky) {
            pieceClicked = true;
            initialClick = true;
            //System.out.println("Got here");

        } else if (pieceClicked && pawnMadeIt && y == 0 && (x >= 1 && x <= 4)){
            model.setToXY(x, y);
            if(!remoteGameConnected) {
                model.promotingPawn();
                pawnMadeIt = false;
                pieceClicked = false;
                initialClick = false;
            } else if (remoteGameConnected){
                writeToServer.serverPromotePawn(model.getFromX() - 1, model.getFromY() - 1, pieces[model.getToX()][model.getToY()].getText());
                pawnMadeIt = false;
                pieceClicked = false;
                initialClick = false;
            }

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
