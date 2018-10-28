public class WhiteQueenPiece extends Piece {
    private static WhiteQueenPiece whiteQueenPiece;

    private WhiteQueenPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }


    public static WhiteQueenPiece getInstance() {
        if(whiteQueenPiece == null) {
            whiteQueenPiece = new WhiteQueenPiece('W');
        }
        return whiteQueenPiece;
    }

}
