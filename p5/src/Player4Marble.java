public class Player4Marble extends Piece {

    private static Player4Marble player4Marble;

    private Player4Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player4Marble getInstance() {
        if(player4Marble == null) {
            player4Marble = new Player4Marble('4');
        }
        return player4Marble;
    }
}
