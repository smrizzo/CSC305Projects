public class Player3Marble extends Piece {

    private static Player3Marble player3Marble;

    private Player3Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player3Marble getInstance() {
        if(player3Marble == null) {
            player3Marble = new Player3Marble('3');
        }
        return player3Marble;
    }
}
