import java.awt.*;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Robot extends MonteCarloAlgorithm<SnapShot, MoveSnapshot, Piece> implements Runnable, CCViewObserver {
    /**
     * Create an  object to calculate a more in a game.  One second/play
     * is reasonable, and for Chinese checkers, 500 is a reasonable value
     * for max moves/play.
     *
     * @param secondsPerTurn
     * @param maxMovesPerPlay
     */
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    StarModel model;
    MoveSnapshot bestMove;
    Piece AIPlayer;
    boolean isTurn = false;
    boolean isDone = false;
    protected Robot(double secondsPerTurn, int maxMovesPerPlay, Piece player, StarModel model) {
        super(secondsPerTurn, maxMovesPerPlay);
        this.AIPlayer = player;
        this.model = model;
        model.registerCCViewObserver(this);

    }

    @Override
    protected List<MoveSnapshot> getLegalMoves(SnapShot board) {
        List<MoveSnapshot> allMoves;
        lock.lock();
        try {
            allMoves = board.getModel().getAllPossibleMoves(board, this.AIPlayer);
        } finally {
            lock.unlock();
        }

        return allMoves;
    }

    @Override
    protected SnapShot applyMove(SnapShot snap, MoveSnapshot move) {

        lock.lock();
        try {
            snap.getModel().setFromXY(move.getFromXY().x, move.getFromXY().y);
            snap.getModel().setToXY(move.getToXY().x, move.getToXY().y);
            snap.getModel().regularMove();
        } finally {
            lock.unlock();
        }

        return snap;
    }

    @Override
    protected Piece getWinningTeam(SnapShot state) {
        Piece player;
        lock.lock();
        try {
            player = state.getModel().getWinningPlayer();
        } finally {
            lock.unlock();
        }
        return player;

    }

    @Override
    protected Piece getLastTeamToMove(SnapShot state) {
        Piece winningTeam;
        lock.lock();
        try {
            winningTeam = state.getModel().getMoveHistory().peek().getState();
        } finally {
            lock.unlock();
        }
        return winningTeam;
    }

    public double getDistance(int x1, int x2, int y1, int y2) {

        return Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }


    @Override
    protected MoveRecord<MoveSnapshot, SnapShot> heuristicChoice(List<MoveRecord<MoveSnapshot, SnapShot>> moves) {

        double lowestDistance = 100;
        double tempDistance = 100;
        Character destination;
        MoveRecord<MoveSnapshot, SnapShot> bestMove = null;
        Point dest;
        Point player1Point = new Point(12, 16);
        Point player4Point = new Point(12, 0);

        Point player2Point = new Point(24, 12);
        Point player5Point = new Point(0, 4);

        Point player3Point = new Point(24, 4);
        Point player6Point = new Point(0, 12);

        lock.lock();
        try {
            for (MoveRecord<MoveSnapshot, SnapShot> move : moves) {
                SnapShot temp = move.state;
                MoveSnapshot tempMove = move.move;
                if (temp.getModel().getState() instanceof Player1Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player4Point.x, tempMove.getToXY().y, player4Point.y);
                } else if (temp.getModel().getState() instanceof Player2Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player5Point.x, tempMove.getToXY().y, player5Point.y);
                } else if (temp.getModel().getState() instanceof Player3Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player6Point.x, tempMove.getToXY().y, player6Point.y);
                } else if (temp.getModel().getState() instanceof Player4Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player1Point.x, tempMove.getToXY().y, player1Point.y);
                } else if (temp.getModel().getState() instanceof Player5Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player2Point.x, tempMove.getToXY().y, player2Point.y);
                } else if (temp.getModel().getState() instanceof Player6Marble) {

                    tempDistance = getDistance(tempMove.getToXY().x, player3Point.x, tempMove.getToXY().y, player3Point.y);

                }

                if (tempDistance <= lowestDistance) {
                    lowestDistance = tempDistance;
                    bestMove = move;
                }

            }
        } finally {
            lock.unlock();
        }

        return bestMove;
    }

    @Override
    public void updateStar(StarModel m) {
        lock.lock();
        try {
            this.model = m;

            if(model.getState().getText() == this.AIPlayer.getText()) {
                isTurn = true;

            }
            condition.signalAll();

        } finally {
            lock.unlock();
        }

    }

    public void shutDown() {
        this.isDone = true;
    }

    @Override
    public void run() {
        if(model.getState().getText() == this.AIPlayer.getText()) {
            isTurn = true;
        }

        while(!isDone) {

            lock.lock();
            try {
                while(!isTurn) {
                    condition.await();

                }
                bestMove = getPlay(new SnapShot(this.model));
                this.model.setFromXY(bestMove.getFromXY().x, bestMove.getFromXY().y);
                this.model.setToXY(bestMove.getToXY().x, bestMove.getToXY().y);
                this.model.regularMove();
                isTurn = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }


}
