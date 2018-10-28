abstract public class Piece {

    protected final char text;
    public Piece(char text) {
        this.text = text;
    }

    abstract public char getText();

    //abstract public void setPiece(int x, int y);

}
