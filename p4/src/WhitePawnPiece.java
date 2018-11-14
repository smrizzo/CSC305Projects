public class WhitePawnPiece extends Piece {
    private static WhitePawnPiece whitePawnPiece;

    private WhitePawnPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }

    public static WhitePawnPiece getInstance() {
        if(whitePawnPiece == null) {
            whitePawnPiece = new WhitePawnPiece('P');
        }

        return whitePawnPiece;
    }


}
