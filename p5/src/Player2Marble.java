public class Player2Marble extends Piece {

    private static Player2Marble player2Marble;

    private Player2Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player2Marble getInstance() {
        if(player2Marble == null) {
            player2Marble = new Player2Marble('2');
        }
        return player2Marble;
    }
}
