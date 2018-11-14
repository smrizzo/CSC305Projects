public class WhiteRookPiece extends Piece {
    private static WhiteRookPiece whiteRookPiece;

    private WhiteRookPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static WhiteRookPiece getInstance() {
        if(whiteRookPiece == null) {
            whiteRookPiece = new WhiteRookPiece('R');
        }
        return whiteRookPiece;
    }
}
