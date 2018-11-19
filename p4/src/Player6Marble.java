public class Player6Marble extends Piece {
    private static Player6Marble player6Marble;

    private Player6Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player6Marble getInstance() {
        if(player6Marble == null) {
            player6Marble = new Player6Marble('6');
        }
        return player6Marble;
    }
}
