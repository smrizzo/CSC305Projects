public class BlackQueenPiece extends Piece {
    private static BlackQueenPiece blackQueenPiece;

    private BlackQueenPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static BlackQueenPiece getInstance() {
        if(blackQueenPiece == null) {
            blackQueenPiece = new BlackQueenPiece('q');
        }
        return blackQueenPiece;
    }

}
