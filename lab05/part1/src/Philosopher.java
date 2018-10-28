
//
// Write your philosopher class here.  Feel free to write it in Kotlin.
//

import others.Watchdog;

public class Philosopher extends Thread {

    private Watchdog watchdog;
    private RunMode mode;
    private int philosopherID;
    private Chopstick leftChopStick;
    private Chopstick rightChopStick;

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
                if(mode.rightChopstickFirst) {

                    watchdog.aboutToRequest(rightChopStick.getChopStickID());
                    rightChopStick.aquireChopstick();
                    watchdog.aboutToRequest(leftChopStick.getChopStickID());
                    leftChopStick.aquireChopstick();

                } else {
                    watchdog.aboutToRequest(leftChopStick.getChopStickID());
                    leftChopStick.aquireChopstick();
                    watchdog.aboutToRequest(rightChopStick.getChopStickID());
                    rightChopStick.aquireChopstick();
                }

                watchdog.notifyPhilosopherState(philosopherID, Watchdog.PhilosopherState.EATING);
                watchdog.delayForMS(mode.getMaxDineFor());


                watchdog.aboutToRelease(rightChopStick.getChopStickID());
                watchdog.aboutToRelease(leftChopStick.getChopStickID());
                rightChopStick.releaseChopstick();
                leftChopStick.releaseChopstick();



            }
        } catch(Exception e) {
            e.printStackTrace();
        }


    }
}

//            lock.lock();
//            try {
//                watchdog.aboutToRequest(rightChopStickNumber);
//            } finally {
//                lock.unlock();
//            }
//
//            lock.lock();
//            try {
//                watchdog.aboutToRequest(leftChopStickNumber);
//            } finally {
//                lock.unlock();
//            }