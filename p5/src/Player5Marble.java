public class Player5Marble extends Piece{

    private static Player5Marble player5Marble;

    private Player5Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player5Marble getInstance() {
        if(player5Marble == null) {
            player5Marble = new Player5Marble('5');
        }
        return player5Marble;
    }
}
