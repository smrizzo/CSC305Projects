import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChessController implements ViewObserver{

    private ChessModel model;
    private Main main;
    private Integer controllerID;
    protected boolean initialClick;
    private boolean pieceClicked;
    protected boolean pawnMadeIt;
    protected int firstClickX;
    protected int firstClicky;
    protected boolean mWhiteBoard;
    protected boolean mBlackBoard;
    private Piece[][] pieces;
    private List<Thread> threads = new ArrayList<>();
    private Character[][] piecesFromServer = new Character[9][9];
    private Socket socket;
    private Thread write;
    private Thread readFrom;
    private WriteToServer writeToServer;
    private ReadFromServer readFromServer;
    private List<String> myInitialList;
    private List<String> myList;
    private boolean kibbitzerConnected = false;
    private boolean remoteGameConnected = false;
    protected volatile boolean endingGame;

    public ChessController(ChessModel model, int numberOfBlackBoards, int numberOfWhiteBoards, int numberOfKibbitzers) throws IOException {
        this.model = model;
        this.pieces = model.getPieces();
        model.registerObserver(this);
        this.initialClick = false;
        this.pieceClicked = false;
        this.pawnMadeIt = false;
        this.endingGame = false;

        if(numberOfBlackBoards > 0) {
            setBlackBoard();
            for(int i = 0; i < numberOfBlackBoards; i++) {
                addBlackView();
            }
        }

        if(numberOfWhiteBoards > 0) {
            setWhiteBoard();
            for(int i = 0; i < numberOfWhiteBoards; i++) {
                addWhiteview();
            }
        }

        if(numberOfKibbitzers > 0) {
            for(int i = 0; i < numberOfKibbitzers; i++) {
                addKibbitzerView();
            }
        }
        runThreads();
    }

    public ChessController(ChessModel model, Integer ControllerID, String ipAddress, Integer port, String SessionID) throws IOException, InterruptedException, CloneNotSupportedException {
        this.model = model;
        this.pieces = model.getPieces();
        this.controllerID = ControllerID;
        model.registerObserver(this);
        this.initialClick = false;
        this.pieceClicked = false;
        this.pawnMadeIt = false;
        this.endingGame = false;
        socket = new Socket(ipAddress, port);
        remoteGameConnected = true;
        writeToServer = new WriteToServer(socket, SessionID);
        readFromServer = new ReadFromServer(socket);
        write = new Thread(writeToServer);
        readFrom = new Thread(readFromServer);
        write.start();
        readFrom.start();
        InitialResponse();
        getResponseFromServer();
        writeToServer.closeWriteStream();
        socket.close();

    }

    public void InitialResponse() throws InterruptedException, IOException, CloneNotSupportedException {
        System.out.println("Waiting for other player....");
        myInitialList = readFromServer.getInitialResponse();
        if(myInitialList.get(1).charAt(0) == '\0') {
            kibbitzerConnected = true;
        } else if(myInitialList.get(1).charAt(0) == 'w') {
            setWhiteBoard();
        } else if(myInitialList.get(1).charAt(0) == 'b') {
            setBlackBoard();
        }

        parseBoard(myInitialList.get(3));
        if(kibbitzerConnected) {
            addKibbitzerView();
        } else if (mWhiteBoard) {
            addWhiteview();
        } else {
            addBlackView();
        }
        runThreads();
        model.setServerBoard(piecesFromServer);

    }


    public void getResponseFromServer() throws InterruptedException, CloneNotSupportedException {

        while(!endingGame) {

            myList = readFromServer.getResponse();
            if(myList.get(0).equals("board")) {
                parseBoard(myList.get(1));
                model.setServerBoard(piecesFromServer);
            } else if(myList.get(0).equals("end")) {
                break;
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

    public void endingGame() throws IOException {
        if(remoteGameConnected) {
            writeToServer.endGame();

        }
        endingGame = true;
    }

    public void clickedPiece(int x, int y) throws CloneNotSupportedException, IOException {
        if(pieces[x][y] != null && !initialClick && !pieceClicked) {
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

    }
}
