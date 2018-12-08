public class EmptyBoardSlot extends Piece {

    private static EmptyBoardSlot emptyBoardSlot;

    private EmptyBoardSlot(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static EmptyBoardSlot getInstance() {
        if(emptyBoardSlot == null) {
            emptyBoardSlot = new EmptyBoardSlot('0');
        }
        return emptyBoardSlot;
    }
}
