import java.util.concurrent.locks.ReentrantLock;

//
// Make your chopstick class here. Feel free to write this in Kotlin.
//
public class Chopstick {
    private int chopStickID;
    private ReentrantLock lock = new ReentrantLock();

    public Chopstick(int chopStickID) {
        this.chopStickID = chopStickID;
    }

    public void aquireChopstick() {

        lock.lock();

    }

    public void releaseChopstick() {

        lock.unlock();

    }

    public int getChopStickID() {

        return chopStickID;

    }
}
