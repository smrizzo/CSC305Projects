import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;


public class StarModel {

    private Marble[][] marbles = new Marble[17][25];
    private List<ViewObserver> Observers = new ArrayList<>();
    private List<CompObserver> compObservers = new ArrayList<>();
    private Stack<MoveSnapshot> moveHistory = new Stack<>();
    private List<Point> moveList = new ArrayList<>();
    private HashMap<Point, Color> listMap = new HashMap<>();
    private Marble srcMarble;
    final static int kDir = 6;
    static Integer triangle1Tracker = 10;
    static Integer triangle2Tracker = 10;
    static Integer triangle3Tracker = 10;
    static Integer triangle4Tracker = 10;
    static Integer triangle5Tracker = 10;
    static Integer triangle6Tracker = 10;

    final static Piece PLAYER_1 = Player1Marble.getInstance();
    final static Piece PLAYER_2 = Player2Marble.getInstance();
    final static Piece PLAYER_3 = Player3Marble.getInstance();
    final static Piece PLAYER_4 = Player4Marble.getInstance();
    final static Piece PLAYER_5 = Player5Marble.getInstance();
    final static Piece PLAYER_6 = Player6Marble.getInstance();
    private Piece state = null;

    private HashMap<Point, Piece> playersHomes = new HashMap<>();
    private HashMap<Point, Piece> dstTriangles = new HashMap<>();

    private HashMap<Integer, Marble> mapForMarbles = new HashMap<>();
    private List<Marble> myMarbles = new ArrayList<>();
    private Point fromXY = new Point();
    private Point toXY = new Point();
    private Integer marbleID = 0;

    public StarModel() {
        initializeStar();
        this.state = PLAYER_1;
    }

    public void initializeStar(){

        for(int y = 0; y <= 16; y++) {
            for(int x = 0; x <= 24; x++) {
                if(y - 8 == -8 && x - 12 == 0 || y - 8 == 8 && x - 12 == 0) {
                    marbleID++;
                    marbles[y][x] = new Marble(marbleID, y, x, EmptyBoardSlot.getInstance(), false);
                    mapForMarbles.put(marbleID, marbles[y][x]);
                    myMarbles.add(marbles[y][x]);
                } else if((y - 8 == -7 && x - 12 == -1) || (y - 8 == -6 && x - 12 == -2) || (y - 8 == -5 && x - 12 == -3) ||
                        (y -8 == -4 && x - 12 == -12) || (y - 8 == -3 && x - 12 == -11) || (y - 8 == -2 && x - 12 == -10) ||
                        (y - 8 == -1 && x - 12 == -9) || (y - 8 == 0 && x - 12 == -8) || (y - 8 == 1 && x - 12 == -9) ||
                        (y - 8 == 2 && x - 12 == -10) || (y - 8 == 3 && x - 12 == -11) || (y - 8 == 4 && x - 12 == -12) ||
                        (y - 8 == 5 && x - 12 == -3) || (y - 8 == 6 && x - 12 == -2) || (y - 8 == 7 && x - 12 == -1)) {
                    int num = x-12;
                    x = settingUpMarbles(y,num, (num*-1));
                } else {
                    marbles[y][x] = null;
                }
            }
        }
        setUpPlayers();
        setUpAdjMarbles();
    }

    private int settingUpMarbles(int y, int start, int finish) {
        for(;start<=finish;start++) {
            if(y % 2 == 0 && start % 2 == 0) {
                marbleID++;
                marbles[y][start + 12] = new Marble(marbleID ,y, start + 12, EmptyBoardSlot.getInstance(), false);
                myMarbles.add(marbles[y][start + 12]);
                mapForMarbles.put(marbleID, marbles[y][start + 12]);
            } else if(y % 2 != 0 && start % 2 != 0){
                marbleID++;
                marbles[y][start + 12] = new Marble(marbleID, y, start + 12, EmptyBoardSlot.getInstance(), false);
                myMarbles.add(marbles[y][start + 12]);
                mapForMarbles.put(marbleID, marbles[y][start + 12]);
            }

        }
        return finish + 12;

    }

    private void setUpPlayers() {
        for(int i = 1; i < 122; i++) {
            if (i <= 10) {
                mapForMarbles.get(i).setPlayer(Player4Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player4Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player1Marble.getInstance());
            } else if((i >= 11 && i <= 14) || (i >= 24 && i <= 26) || (i >=36 && i <= 37) || i == 47) {
                mapForMarbles.get(i).setPlayer(Player5Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player5Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player2Marble.getInstance());
            } else if ((i >= 20 && i <= 23) || (i >= 33 && i <= 35) || (i >= 45 && i <= 46) || i == 56){
                mapForMarbles.get(i).setPlayer(Player3Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player3Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player6Marble.getInstance());
            } else if ((i == 66) || (i >= 76 && i <= 77) || (i >= 87 && i <= 89) || (i >= 99 && i <= 102)) {
                mapForMarbles.get(i).setPlayer(Player6Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player6Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player3Marble.getInstance());
            } else if ((i == 75) || (i >= 85 && i <= 86) || (i >= 96 && i <= 98) || (i >= 108 && i <= 111)) {
                mapForMarbles.get(i).setPlayer(Player2Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player2Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player5Marble.getInstance());
            } else if((i >= 112 && i <= 115) || (i >= 116 && i <= 118) || (i >= 119 && i <= 120) || i == 121) {
                mapForMarbles.get(i).setPlayer(Player1Marble.getInstance());
                mapForMarbles.get(i).setHasMarble(true);
                playersHomes.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player1Marble.getInstance());
                dstTriangles.put(new Point(mapForMarbles.get(i).getMarbleX(), mapForMarbles.get(i).getMarbleY()), Player4Marble.getInstance());
            }
        }
    }

    public void setUpAdjMarbles() {
        Marble marble;
        for(int y = 0; y <=16; y++) {
            for(int x = 0; x <= 24; x++) {
                if(marbles[y][x] != null) {
                    marble = marbles[y][x];
                    if(y == 0 && x == 12) {
                        marble.adjMarbles[0] = null;
                        marble.adjMarbles[1] = null;
                        marble.adjMarbles[2] = marbles[y][x+2];
                        marble.adjMarbles[3] = marbles[y + 1][x + 1];
                        marble.adjMarbles[4] = marbles[y + 1][x - 1];
                        marble.adjMarbles[5] = marbles[y][x - 2];
                    } else if (y == 16 && x == 12) {
                        marble.adjMarbles[0] = marbles[y - 1][x - 1];
                        marble.adjMarbles[1] = marbles[y - 1][x + 1];
                        marble.adjMarbles[2] = marbles[y][x + 2];
                        marble.adjMarbles[3] = null;
                        marble.adjMarbles[4] = null;
                        marble.adjMarbles[5] = marbles[y][x - 2];
                    } else if ((y == 4 && x == 0) || (y == 12 && x == 0)) {
                        marble.adjMarbles[0] = null;
                        marble.adjMarbles[1] = marbles[y - 1][x + 1];
                        marble.adjMarbles[2] = marbles[y][x + 2];
                        marble.adjMarbles[3] = marbles[y + 1][x + 1];
                        marble.adjMarbles[4] = null;
                        marble.adjMarbles[5] = null;
                    } else if((y == 5 && x == 1) || (y == 11 && x == 1)) {
                        marble.adjMarbles[0] = marbles[y - 1][x - 1];
                        marble.adjMarbles[1] = marbles[y - 1][x + 1];
                        marble.adjMarbles[2] = marbles[y][x + 2];
                        marble.adjMarbles[3] = marbles[y + 1][x + 1];
                        marble.adjMarbles[4] = marbles[y + 1][x - 1];
                        marble.adjMarbles[5] = null;
                    } else if ((y == 4 && x == 24) || (y == 12 && x == 24)) {
                        marble.adjMarbles[0] = marbles[y - 1][x - 1];
                        marble.adjMarbles[1] = null;
                        marble.adjMarbles[2] = null;
                        marble.adjMarbles[3] = null;
                        marble.adjMarbles[4] = marbles[y + 1][x - 1];
                        marble.adjMarbles[5] = marbles[y][x - 2];
                    } else if ((y == 5 && x == 23) || (y == 11 && x == 23)) {
                        marble.adjMarbles[0] = marbles[y - 1][x - 1];
                        marble.adjMarbles[1] = marbles[y - 1][x + 1];
                        marble.adjMarbles[2] = null;
                        marble.adjMarbles[3] = marbles[y + 1][x + 1];
                        marble.adjMarbles[4] = marbles[y + 1][x - 1];
                        marble.adjMarbles[5] = marbles[y][x - 2];
                    } else {
                        marble.adjMarbles[0] = marbles[y - 1][x - 1];
                        marble.adjMarbles[1] = marbles[y - 1][x + 1];
                        marble.adjMarbles[2] = marbles[y][x + 2];
                        marble.adjMarbles[3] = marbles[y + 1][x + 1];
                        marble.adjMarbles[4] = marbles[y + 1][x - 1];
                        marble.adjMarbles[5] = marbles[y][x - 2];
                    }
                }
            }
        }
    }

    private boolean regMove(Marble marble, int dir) {
        if(!isValidRegMove(marble, dir)) {
            return false;
        }

        if (marble.adjMarbles[dir] != null && !marble.adjMarbles[dir].getHasMarble()) {
            return true;
        }
        return false;
    }

    private boolean canJump(Marble marble, int dir) {

        if(marble.adjMarbles[dir] == null || marble.adjMarbles[dir].adjMarbles[dir] == null){
            return false;
        }
        Point srcPoint = new Point(marble.getMarbleX(), marble.getMarbleY());
        Point nextPoint = new Point(marble.adjMarbles[dir].adjMarbles[dir].getMarbleX(), marble.adjMarbles[dir].adjMarbles[dir].getMarbleY());

        if(marble.adjMarbles[dir] != null && !marble.adjMarbles[dir].getHasMarble()) {
            return false;
        } else if (marble.adjMarbles[dir] != null && marble.adjMarbles[dir].getHasMarble() &&
                marble.adjMarbles[dir].adjMarbles[dir].getHasMarble()) {
            return false;
        } else if (marble.adjMarbles[dir].getHasMarble() && !marble.adjMarbles[dir].adjMarbles[dir].getHasMarble() &&
                listMap.containsKey(nextPoint)) {
            return false;
        } else if (dstTriangles.containsKey(srcPoint) && !dstTriangles.containsKey(nextPoint)) {
            Piece dstPlayer = dstTriangles.get(srcPoint);
            if((srcMarble.getPlayer() instanceof Player1Marble && dstPlayer instanceof Player1Marble) ||
                    (srcMarble.getPlayer() instanceof Player2Marble && dstPlayer instanceof Player2Marble) ||
                    (srcMarble.getPlayer() instanceof Player3Marble && dstPlayer  instanceof Player3Marble) ||
                    (srcMarble.getPlayer() instanceof Player4Marble && dstPlayer instanceof Player4Marble) ||
                    (srcMarble.getPlayer() instanceof Player5Marble && dstPlayer instanceof Player5Marble) ||
                    (srcMarble.getPlayer() instanceof Player6Marble && dstPlayer instanceof Player6Marble)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidRegMove(Marble marble, int dir) {
        if(marble.adjMarbles[dir] == null){
            return false;
        }
        Marble dstMarble = marble.adjMarbles[dir];
        Point srcPoint = new Point(marble.getMarbleX(), marble.getMarbleY());
        Point dstPoint = new Point(dstMarble.getMarbleX(), dstMarble.getMarbleY());
        if(!playersHomes.containsKey(srcPoint) && playersHomes.containsKey(dstPoint)) {
            if (playersHomes.get(dstPoint).getText() == marble.getPlayer().getText()) {
                return false;
            }
        }

        if(dstTriangles.containsKey(srcPoint) && !dstTriangles.containsKey(dstPoint)) {
            System.out.println("found move not in triangle");
            Piece dstPlayer = dstTriangles.get(srcPoint);
            if((srcMarble.getPlayer() instanceof Player1Marble && dstPlayer instanceof Player1Marble) ||
                    (srcMarble.getPlayer() instanceof Player2Marble && dstPlayer instanceof Player2Marble) ||
                    (srcMarble.getPlayer() instanceof Player3Marble && dstPlayer  instanceof Player3Marble) ||
                    (srcMarble.getPlayer() instanceof Player4Marble && dstPlayer instanceof Player4Marble) ||
                    (srcMarble.getPlayer() instanceof Player5Marble && dstPlayer instanceof Player5Marble) ||
                    (srcMarble.getPlayer() instanceof Player6Marble && dstPlayer instanceof Player6Marble)) {
                return false;
            }
        }

        return true;
    }

    public boolean validMove() {
        return listMap.containsKey(toXY);
    }

    public void getAllMoves() {

        if(!listMap.isEmpty()) {
            listMap.clear();
        }
        Marble marble = marbles[fromXY.y][fromXY.x];
        this.srcMarble = marbles[fromXY.y][fromXY.x];

        for (int dir = 0; dir < kDir; dir++) {
            if(regMove(marble, dir)) {
                listMap.put(new Point(marble.adjMarbles[dir].getMarbleX(), marble.adjMarbles[dir].getMarbleY()), Color.WHITE);

            } else if(canJump(marble, dir)) {
                Marble nextMarble = marble.adjMarbles[dir].adjMarbles[dir];
                listMap.put(new Point(nextMarble.getMarbleX(), nextMarble.getMarbleY()), Color.WHITE);
                addingJumps(nextMarble);
            }
        }
        starChanged();
    }

    private void addingJumps(Marble marble) {
        Marble jumpToMarble;
        Point currentPoint = new Point(marble.getMarbleX(), marble.getMarbleY());
        for(int dir = 0; dir < kDir; dir++) {
            if(canJump(marble, dir)) {
                jumpToMarble = marble.adjMarbles[dir].adjMarbles[dir];
                listMap.put(new Point(jumpToMarble.getMarbleX(), jumpToMarble.getMarbleY()), Color.WHITE);
                addingJumps(jumpToMarble);

            }
        }

        if(playersHomes.containsKey(currentPoint)) {
            if(playersHomes.get(currentPoint).getText() == srcMarble.getPlayer().getText()) {
                listMap.remove(currentPoint);
            }
        }

    }

    public List<Point> getMoveList() {
        return this.moveList;
    }

    public HashMap<Point, Color> getListMap() {
        return listMap;
    }

    public Piece getState() {
        return state;
    }

    public void setFromXY(int x, int y) {
        System.out.println("x: " + x + ", y :" + y);
        fromXY.setLocation(x, y);
        System.out.println("after x: " + fromXY.x + ", after y :" + fromXY.y);
    }

    public void setToXY(int x, int y) {
        toXY.setLocation(x, y);
    }

    public void setPlayersTurn() {
        if(this.state instanceof Player1Marble) {
            this.state = PLAYER_2;
        } else if (this.state instanceof Player2Marble) {
            this.state = PLAYER_3;
        } else if (this.state instanceof Player3Marble) {
            this.state = PLAYER_4;
        } else if (this.state instanceof Player4Marble) {
            this.state = PLAYER_5;
        } else if (this.state instanceof Player5Marble) {
            this.state = PLAYER_6;
        } else if (this.state instanceof Player6Marble) {
            this.state = PLAYER_1;
        }
    }

    public void addToMoveHistory() {
        moveHistory.push(new MoveSnapshot(new Point(fromXY.x, fromXY.y), new Point(toXY.x, toXY.y), getState()));
    }

    public void regularMove() {

        marbles[toXY.y][toXY.x].setPlayer(marbles[fromXY.y][fromXY.x].getPlayer());
        marbles[toXY.y][toXY.x].setHasMarble(true);

        marbles[fromXY.y][fromXY.x].setPlayer(EmptyBoardSlot.getInstance());
        marbles[fromXY.y][fromXY.x].setHasMarble(false);
        addToMoveHistory();
        setPlayersTurn();
        starChanged();

    }

    public void undoLastMove() {

        if(moveHistory.size() > 0) {
            MoveSnapshot snapshot = moveHistory.pop();
            this.fromXY = snapshot.getFromXY();
            this.toXY = snapshot.getToXY();
            this.state = snapshot.getState();

            marbles[fromXY.y][fromXY.x].setPlayer(marbles[toXY.y][toXY.x].getPlayer());
            marbles[fromXY.y][fromXY.x].setHasMarble(true);

            marbles[toXY.y][toXY.x].setPlayer(EmptyBoardSlot.getInstance());
            marbles[toXY.y][toXY.x].setHasMarble(false);
            if(!listMap.isEmpty()) {
                listMap.clear();
            }
            starChanged();
        }
    }

    public Marble[][] getMarbles() {
        return marbles;
    }

    public void removeViewObserver(ViewObserver o) {
        int i = Observers.indexOf(o);
        if(i >= 0) {
            Observers.remove(i);
        }
    }

    public void removeCompObserver(CompObserver o) {
        int i = compObservers.indexOf(o);
        if(i >= 0) {
            compObservers.remove(i);
        }
    }

    public void registerViewObserver(ViewObserver o) {
        Observers.add(o);
    }

    public void registerCompObserver(CompObserver o) {
        compObservers.add(o);
    }

    public void notifyViewObservers() {
        for(ViewObserver o: Observers) {
            o.updateStar(this);
        }
    }
    public void notifyCompObservers() {
        for(CompObserver o: compObservers) {
            o.updateComponent(this);
        }
    }

    public void starChanged() {
        notifyCompObservers();
        notifyViewObservers();
    }

    public HashMap<Integer, Marble> getMapForMarbles() {
        return mapForMarbles;
    }


}
