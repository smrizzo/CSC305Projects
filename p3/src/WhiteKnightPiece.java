public class WhiteKnightPiece extends Piece {
    private static WhiteKnightPiece whiteKnightPiece;

    private WhiteKnightPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static WhiteKnightPiece getInstance() {
        if(whiteKnightPiece == null) {
            whiteKnightPiece = new WhiteKnightPiece('N');
        }
        return whiteKnightPiece;
    }



}
