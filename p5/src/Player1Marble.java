public class Player1Marble extends Piece{
    private static Player1Marble player1Marble;

    private Player1Marble(char text) { super(text); }

    @Override
    public char getText() {
        return text;
    }

    public static Player1Marble getInstance() {
        if(player1Marble == null) {
            player1Marble = new Player1Marble('1');
        }
        return player1Marble;
    }

}
