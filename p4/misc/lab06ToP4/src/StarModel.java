import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class StarModel implements Runnable{

    enum Players
    {
        PLAYER1,PLAYER2,PLAYER3,PLAYER4,PLAYER5,PLAYER6;
    }
    private Marble[][] marbles = new Marble[17][25];
    private List<ViewObserver> Observers = new ArrayList<>();
    private List<CompObserver> compObservers = new ArrayList<>();
    private HashMap<Integer, Marble> mapForMarbles = new HashMap<>();
    private List<Marble> myMarbles = new ArrayList<>();
    private Integer[] fromXY = new Integer[2];
    private Integer[] toXY = new Integer[2];
    private int numOfUi;
    private boolean done = false;
    private Players player;
    private String mPlayer;
    private Integer[] players = {1, 2, 3, 4, 5, 6};
    Integer marbleID = 0;

    public StarModel() {
        initializeStar();
    }

    public void initializeStar(){

        for(int y = 0; y <= 16; y++) {
            for(int x = 0; x <= 24; x++) {
                if(y - 8 == -8 && x - 12 == 0 || y - 8 == 8 && x - 12 == 0) {
                    marbleID++;
                    marbles[y][x] = new Marble(marbleID, y, x, -1, false);
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
    }

    private int settingUpMarbles(int y, int start, int finish) {
        for(;start<=finish;start++) {
            if(y % 2 == 0 && start % 2 == 0) {
                marbleID++;

                marbles[y][start + 12] = new Marble(marbleID ,y, start + 12, -1, false);
                myMarbles.add(marbles[y][start + 12]);
                mapForMarbles.put(marbleID, marbles[y][start + 12]);
            } else if(y % 2 != 0 && start % 2 != 0){
                marbleID++;
                marbles[y][start + 12] = new Marble(marbleID, y, start + 12, -1, false);
                myMarbles.add(marbles[y][start + 12]);
                mapForMarbles.put(marbleID, marbles[y][start + 12]);
            }

        }
        return finish + 12;
    }

    private void setUpPlayers() {
        for(int i = 1; i < 122; i++) {
            if (i <= 10) {
                mapForMarbles.get(i).setPlayer(4);
                mapForMarbles.get(i).setHasMarble(true);
            } else if((i >= 11 && i <= 14) || (i >= 24 && i <= 26) || (i >=36 && i <= 37) || i == 47) {
                mapForMarbles.get(i).setPlayer(5);
                mapForMarbles.get(i).setHasMarble(true);
            } else if ((i >= 20 && i <= 23) || (i >= 33 && i <= 35) || (i >= 45 && i <= 46) || i == 56){
                mapForMarbles.get(i).setPlayer(3);
                mapForMarbles.get(i).setHasMarble(true);
            } else if ((i == 66) || (i >= 76 && i <= 77) || (i >= 87 && i <= 89) || (i >= 99 && i <= 102)) {
                mapForMarbles.get(i).setPlayer(6);
                mapForMarbles.get(i).setHasMarble(true);
            } else if ((i == 75) || (i >= 85 && i <= 86) || (i >= 96 && i <= 98) || (i >= 108 && i <= 111)) {
                mapForMarbles.get(i).setPlayer(2);
                mapForMarbles.get(i).setHasMarble(true);
            } else if((i >= 112 && i <= 115) || (i >= 116 && i <= 118) || (i >= 119 && i <= 120) || i == 121) {
                mapForMarbles.get(i).setPlayer(1);
                mapForMarbles.get(i).setHasMarble(true);
            }
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

    public void shutDown() {
        done = true;
    }

    public void changeRandomColor() {
        Random rand = new Random();
        Integer number = rand.nextInt(mapForMarbles.size()) + 1;
        mapForMarbles.get(number).setColors(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));

    }

    public HashMap<Integer, Marble> getMapForMarbles() {
        return mapForMarbles;
    }

    @Override
    public void run() {


//        long startTime = System.nanoTime();
//        long currentTime = 0;
//        while(!done) {
//            startTime = startTime + 1000000000;
//            lock.lock();
//            try {
//                while((currentTime = System.nanoTime()) - startTime < 0) {
//                    if(Observers.size() < 1) {
//                        shutDown();
//                    }
//                    condition.awaitNanos(startTime - currentTime);
//                }
//                changeRandomColor();
//                starChanged();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }
    }
}
