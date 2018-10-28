public class BlackKingPiece extends Piece {
    private static BlackKingPiece blackKingPiece;

    private BlackKingPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static BlackKingPiece getInstance() {
        if(blackKingPiece == null) {
            blackKingPiece = new BlackKingPiece('k');
        }
        return blackKingPiece;
    }


}
