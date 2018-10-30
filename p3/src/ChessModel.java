import java.util.ArrayList;
import java.util.List;

public class ChessModel {

    private final Piece [][] pieces = new Piece[9][9];
    private List<ViewObserver> Observers = new ArrayList<>();
    private Integer[] fromXY = new Integer[2];
    private Integer[] toXY = new Integer[2];
    Character[][] piecesFromServer = new Character[9][9];
    String ipAddress;
    String port;
    String SessionID;

    public ChessModel() {
        initializeBoard();
    }

    public ChessModel(String ipAddress, String port, String SessionID) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.SessionID = SessionID;

    }

    private void initializeBoard() {
        for(int row = 1; row < 9; row++) {
            for(int col = 1; col < 9; col++) {

                if(col == 2) {
                    pieces[row][col] = BlackPawnPiece.getInstance();
                } else if(col == 7) {
                    pieces[row][col] = WhitePawnPiece.getInstance();
                } else if(row == 1 && col == 1) {
                    pieces[row][col] = BlackRookPiece.getInstance();
                } else if(row == 1 && col == 8) {
                    pieces[row][col] = WhiteRookPiece.getInstance();
                } else if(row == 2 && col == 1) {
                    pieces[row][col] = BlackKnightPiece.getInstance();
                } else if(row == 2 && col == 8) {
                    pieces[row][col] = WhiteKnightPiece.getInstance();
                } else if(row == 3 && col == 1) {
                    pieces[row][col] = BlackBishopPiece.getInstance();
                } else if(row == 3 && col == 8) {
                    pieces[row][col] = WhiteBishopPiece.getInstance();
                } else if(row == 4 && col == 1) {
                    pieces[row][col] = BlackQueenPiece.getInstance();
                } else if(row == 4 && col == 8) {
                    pieces[row][col] = WhiteQueenPiece.getInstance();
                } else if(row == 5 && col == 1) {
                    pieces[row][col] = BlackKingPiece.getInstance();
                } else if (row == 5 && col == 8) {
                    pieces[row][col] = WhiteKingPiece.getInstance();
                } else if(row == 6 && col == 1) {
                    pieces[row][col] = BlackBishopPiece.getInstance();
                } else if(row == 6 && col == 8) {
                    pieces[row][col] = WhiteBishopPiece.getInstance();
                } else if(row == 7 && col == 1) {
                    pieces[row][col] = BlackKnightPiece.getInstance();
                } else if(row == 7 && col == 8) {
                    pieces[row][col] = WhiteKnightPiece.getInstance();
                } else if(row == 8 && col == 1) {
                    pieces[row][col] = BlackRookPiece.getInstance();
                } else if(row == 8 && col == 8) {
                    pieces[row][col] = WhiteRookPiece.getInstance();
                } else {
                    pieces[row][col] = null;
                }
            }
        }
    }


    public void registerObserver(ViewObserver o) {
        Observers.add(o);
    }

    public void removeObserver(ViewObserver o) {
        int i = Observers.indexOf(o);
        if(i > 0) {
            Observers.remove(i);
        }
    }

    public void notifyObservers() throws CloneNotSupportedException {
        for(ViewObserver o: Observers) {
            o.notifyBoardUpdate(this);
        }
    }

    public void setFromXY(int x, int y) {
        fromXY[0] = x;
        fromXY[1] = y;
    }

    public void setToXY(int x, int y) {
        System.out.println("Setting destination");
        toXY[0] = x;
        toXY[1] = y;
    }

    public void regularMove() throws CloneNotSupportedException {
        //Make regular move on board and notify observers
        System.out.println("Making regular move");
        int fromX = fromXY[0];
        int fromY = fromXY[1];
        int toX = toXY[0];
        int toY = toXY[1];
        pieces[toX][toY] = pieces[fromX][fromY];
        pieces[fromX][fromY] = null;
        boardChanged();


    }

    public boolean moveAndtakePiece() throws CloneNotSupportedException {
        if(Character.isLowerCase(pieces[fromXY[0]][fromXY[1]].getText()) && Character.isUpperCase(pieces[toXY[0]][toXY[1]].getText()) ||
                (Character.isUpperCase(pieces[fromXY[0]][fromXY[1]].getText()) && Character.isLowerCase(pieces[toXY[0]][toXY[1]].getText()))) {
            System.out.println("Taking piece");
            int fromX = fromXY[0];
            int fromY = fromXY[1];
            int toX = toXY[0];
            int toY = toXY[1];
            pieces[toX][toY] = pieces[fromX][fromY];
            pieces[fromX][fromY] = null;
            boardChanged();
            return true;
        } else {
            return false;
        }

    }

    public void promoteWhitePawn() throws CloneNotSupportedException {
        pieces[1][0] = WhiteQueenPiece.getInstance();
        pieces[2][0] = WhiteRookPiece.getInstance();
        pieces[3][0] = WhiteBishopPiece.getInstance();
        pieces[4][0] = WhiteKnightPiece.getInstance();
        boardChanged();

        //promote white pawn at the x and y location

    }

    public void promoteBlackPawn() throws CloneNotSupportedException {
        //promote black pawn at the x and y location
        pieces[1][0] = BlackQueenPiece.getInstance();
        pieces[2][0] = BlackRookPiece.getInstance();
        pieces[3][0] = BlackBishopPiece.getInstance();
        pieces[4][0] = BlackKnightPiece.getInstance();
        boardChanged();
    }

    public void promotingPawn() throws CloneNotSupportedException {
        int fromX = fromXY[0];
        int fromY = fromXY[1];
        int toX = toXY[0];
        int toY = toXY[1];
        pieces[fromX][fromY] = pieces[toX][toY];
        for(int i = 1; i <= 4; i++) {
            pieces[i][0] = null;
        }
        boardChanged();
    }

    public void boardChanged() throws CloneNotSupportedException {
        notifyObservers();
    }

    public int getFromX() {
        return fromXY[0];
    }

    public int getFromY() {
        return fromXY[1];
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}
