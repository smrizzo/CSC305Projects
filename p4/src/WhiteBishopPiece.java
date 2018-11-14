public final class WhiteBishopPiece extends Piece {
    private static WhiteBishopPiece whiteBishopPiece;

    private WhiteBishopPiece(char text) {
        super(text);

    }

    public static WhiteBishopPiece getInstance() {
        if(whiteBishopPiece == null) {
            whiteBishopPiece = new WhiteBishopPiece('B');
        }
        return whiteBishopPiece;
    }

    public char getText() {
        return text;
    }


}
