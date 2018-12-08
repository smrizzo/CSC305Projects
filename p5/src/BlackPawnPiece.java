public class BlackPawnPiece extends Piece {
    private static BlackPawnPiece blackPawnPiece;

    private BlackPawnPiece(char text) {
        super(text);
    }

    @Override
    public char getText() {
        return text;
    }


    public static BlackPawnPiece getInstance() {
        if(blackPawnPiece == null) {
            blackPawnPiece = new BlackPawnPiece('p');
        }
        return blackPawnPiece;
    }
}
