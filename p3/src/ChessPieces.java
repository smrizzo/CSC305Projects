import java.io.File;
import java.util.HashMap;

public class ChessPieces {
    protected HashMap<String, File> pieces = new HashMap<>();
//    protected List<File> letters = new ArrayList<>();

    public ChessPieces() {
        pieces.put("blackPawn", new File("./images/black_pawn.png"));
        pieces.put("blackRook", new File("./images/black_rook.png"));
        pieces.put("blackKnight", new File("./images/black_knight.png"));
        pieces.put("blackBishop", new File("./images/black_bishop.png"));
        pieces.put("blackKing", new File("./images/black_king.png"));
        pieces.put("blackQueen", new File("./images/black_queen.png"));
        pieces.put("whitePawn", new File("./images/white_pawn.png"));
        pieces.put("whiteKnight", new File("./images/white_knight.png"));
        pieces.put("whiteBishop", new File("./images/white_bishop.png"));
        pieces.put("whiteKing", new File("./images/white_king.png"));
        pieces.put("whiteQueen", new File("./images/white_queen.png"));
        pieces.put("whiteRook", new File("./images/white_rook.png"));
        pieces.put("kibbitzer", new File("./images/kibitzer.jpg"));
        pieces.put("A", new File("./images/A.png"));
        pieces.put("B", new File("./images/B.png"));
        pieces.put("C", new File("./images/C.png"));
        pieces.put("D", new File("./images/D.png"));
        pieces.put("E", new File("./images/E.png"));
        pieces.put("F", new File("./images/F.png"));
        pieces.put("G", new File("./images/G.png"));
        pieces.put("H", new File("./images/H.png"));

        pieces.put("1", new File("./images/1.png"));
        pieces.put("2", new File("./images/2.png"));
        pieces.put("3", new File("./images/3.png"));
        pieces.put("4", new File("./images/4.png"));
        pieces.put("5", new File("./images/5.png"));
        pieces.put("6", new File("./images/6.png"));
        pieces.put("7", new File("./images/7.png"));
        pieces.put("8", new File("./images/8.png"));


    }

    public HashMap<String, File> getImageFiles() {
        return pieces;
    }

//    public List<File> getLetters() { return letters; }


}
