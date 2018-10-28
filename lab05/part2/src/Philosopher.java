
//
// Write your philosopher class here.  Feel free to write it in Kotlin.
//

import others.Watchdog;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {

    private Watchdog watchdog;
    private RunMode mode;
    private int philosopherID;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public Philosopher(int philosopherID, Watchdog watchdog, RunMode mode, Chopstick leftChopStick, Chopstick rightChopStick) {
        this.watchdog = watchdog;
        this.mode = mode;
        this.philosopherID = philosopherID;
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;

    }

    @Override
    public void run() {
        System.out.println("Running philosopher");

        try {
            while(!currentThread().isInterrupted()) {
                watchdog.notifyPhilosopherState(philosopherID, Watchdog.PhilosopherState.THINKING);
                watchdog.delayForMS(mode.getMaxThinkFor());
                watchdog.notifyPhilosopherState(philosopherID, Watchdog.PhilosopherState.HUNGRY);

                //tell watchdog about to ask for both chopsticks

                watchdog.aboutToRequest(leftChopStick.getChopStickID());
                watchdog.aboutToRequest(rightChopStick.getChopStickID());


                //eat for a while
                watchdog.delayForMS(mode.getMaxDineFor());



            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void takeChopSticks() {
        lock.lock();
        try {
            while (!leftChopStick.chopStickTaken && !rightChopStick.chopStickTaken) {
                condition.await();
            }
            watchdog.aboutToRequest(leftChopStick.getChopStickID());
            watchdog.aboutToRequest(rightChopStick.getChopStickID());
            leftChopStick.chopStickTaken = true;
            rightChopStick.chopStickTaken = true;
        } finally {
            lock.unlock();
        }
    }

    public void
}

//aquire a lock while its not taken
//so condition would be to await till its free

