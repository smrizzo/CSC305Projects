public class WhiteKingPiece extends Piece {
    private static WhiteKingPiece whiteKingPiece;

    private WhiteKingPiece(char text) {
        super(text);
    }


    public static WhiteKingPiece getInstance() {
        if(whiteKingPiece == null) {
            whiteKingPiece = new WhiteKingPiece('K');
        }
        return whiteKingPiece;
    }

    @Override
    public char getText() {
        return text;
    }

}
