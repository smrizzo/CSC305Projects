public class BlackBishopPiece extends Piece {
    private static BlackBishopPiece blackBishopPiece;

    private BlackBishopPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }


    public static BlackBishopPiece getInstance() {
        if(blackBishopPiece == null) {
            blackBishopPiece = new BlackBishopPiece('b');
        }
        return blackBishopPiece;
    }
}
