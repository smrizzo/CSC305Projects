import java.io.File;
import java.util.HashMap;

public class ChessPieces {
    protected HashMap<String, File> pieces = new HashMap<>();

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
    }

    public HashMap<String, File> getImageFiles() {
        return pieces;
    }


}
