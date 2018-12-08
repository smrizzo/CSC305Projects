public class BlackKnightPiece extends Piece {
    private static BlackKnightPiece blackKnightPiece;

    private BlackKnightPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static BlackKnightPiece getInstance() {
        if(blackKnightPiece == null) {
            blackKnightPiece = new BlackKnightPiece('n');
        }
        return blackKnightPiece;
    }
}
