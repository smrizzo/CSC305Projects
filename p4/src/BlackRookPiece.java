public class BlackRookPiece extends Piece {
    private static BlackRookPiece blackRookPiece;

    private BlackRookPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static BlackRookPiece getInstance() {
        if(blackRookPiece == null) {
            blackRookPiece = new BlackRookPiece('r');
        }
        return blackRookPiece;
    }
}
