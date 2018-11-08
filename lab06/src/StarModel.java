import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StarModel implements Runnable{


    private Circle [][] circles = new Circle[17][25];
    private List<myObserver> Observers = new ArrayList<>();
    private HashMap<Integer, Circle> mapForRandom = new HashMap<>();
    private List<StarUI> starUIS = new ArrayList<>();
    private int numOfUi;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean done = false;
    Integer numOfCircles = 0;

    public StarModel(int numOfUI) {
        initializeStar();
        this.numOfUi = numOfUI;
        for(int i = 0; i < numOfUI; i++) {
            starUIS.add(new StarUI(this));
        }
        for(int i = 0; i < numOfUI; i++) {
            starUIS.get(i).setFrameVisible(true);
        }
    }

    public void initializeStar(){

        for(int y = 0; y <= 16; y++) {
            for(int x = 0; x <= 24; x++) {
                if(y - 8 == -8 && x - 12 == 0 || y - 8 == 8 && x - 12 == 0) {
                    circles[y][x] = new Circle(y, x, Color.BLUE, true);
                    numOfCircles++;
                    mapForRandom.put(numOfCircles, circles[y][x]);
                } else if((y - 8 == -7 && x - 12 == -1) || (y - 8 == -6 && x - 12 == -2) || (y - 8 == -5 && x - 12 == -3) ||
                        (y -8 == -4 && x - 12 == -12) || (y - 8 == -3 && x - 12 == -11) || (y - 8 == -2 && x - 12 == -10) ||
                        (y - 8 == -1 && x - 12 == -9) || (y - 8 == 0 && x - 12 == -8) || (y - 8 == 1 && x - 12 == -9) ||
                        (y - 8 == 2 && x - 12 == -10) || (y - 8 == 3 && x - 12 == -11) || (y - 8 == 4 && x - 12 == -12) ||
                        (y - 8 == 5 && x - 12 == -3) || (y - 8 == 6 && x - 12 == -2) || (y - 8 == 7 && x - 12 == -1)) {
                    int num = x-12;
                    x = settingUpCircles(y,num, (num*-1));
                } else {
                    circles[y][x] = null;
                }
            }
        }
        makeHollow();
    }


    private int settingUpCircles(int y, int start, int finish) {

        for(;start<=finish;start++) {
            if(y % 2 == 0 && start % 2 == 0) {
                circles[y][start + 12] = new Circle(y, start + 12, Color.BLUE, true);
                numOfCircles++;
                mapForRandom.put(numOfCircles, circles[y][start + 12]);
            } else if(y % 2 != 0 && start % 2 != 0){
                circles[y][start + 12] = new Circle(y, start + 12, Color.GREEN, true);
                numOfCircles++;
                mapForRandom.put(numOfCircles, circles[y][start + 12]);
            }

        }
        return finish + 12;
    }

    private void makeHollow() {
        circles[7][11] = null;
        circles[7][13] = null;
        circles[8][10] = null;
        circles[8][12] = null;
        circles[8][14] = null;
        circles[9][11] = null;
        circles[9][13] = null;
    }

    public Circle[][] getCircles() {
        return circles;
    }

    public void registerObserver(myObserver o) {
        Observers.add(o);
    }

    public void notifyObservers() {
        for(myObserver o: Observers) {
            o.updateStar(this);
        }
    }

    public void starChanged() {
        notifyObservers();
    }

    public void shutDown() {
        done = true;
    }

    public void changeRandomColor() {
        Random rand = new Random();
        Integer number = rand.nextInt(mapForRandom.size()) + 1;
        mapForRandom.get(number).setColors(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));

    }


    @Override
    public void run() {


        long startTime = System.nanoTime();
        while(!done) {
            startTime = startTime + 1000000000;
            lock.lock();
            try {
                while(System.nanoTime() - startTime < 0) {
                    condition.awaitNanos(startTime - System.nanoTime());
                }
                changeRandomColor();
                starChanged();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
