import java.util.concurrent.locks.ReentrantLock;

//
// Make your chopstick class here. Feel free to write this in Kotlin.
//
public class Chopstick {

    private int chopStickID;
    boolean chopStickTaken;
    private ReentrantLock lock = new ReentrantLock();

    public Chopstick(int chopStickID, boolean chopStickTaken) {

        this.chopStickID = chopStickID;
        this.chopStickTaken = chopStickTaken;
    }


    public int getChopStickID() {
//        lock.lock();
//        try {
//            return chopStickID;
//        } finally {
//            lock.unlock();
//        }
        return chopStickID;

    }
}
