package others;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * A watchdog watches over the dining philosophers.  It provides a graphical
 * visualization of what they're doing.  If nothing happens for a certain
 * amount of time, it prints a message, but it doesn't do anything to
 * fix the problem.  This lets the UI stay up until you close it.
 * <p>
 * DO NOT MODIFY THIS FILE!
 */

public class Watchdog {

    public static enum PhilosopherState { THINKING, HUNGRY, EATING };

    public static interface Mode {
        public int getNumberOfPhilosophers();
        public int getMaxDineFor();
        public int getMaxThinkFor();
    }

    private final static long NANOS_PER_MS = 1_000_000L;

    /**
     * If nothing happens after this long, abort.
     */

    public final int numberOfChopsticks;
    public final int abortAfterMS;
    private final long startTimeNanos;
    private final AtomicBoolean somethingHappened = new AtomicBoolean(false);
    private final AtomicInteger[] requestCounts;
    private final AtomicReferenceArray<PhilosopherState> philosopherStates;

    private final WatchdogUI view;
    
    public Watchdog(Mode mode) {
	this.numberOfChopsticks = mode.getNumberOfPhilosophers();
	this.abortAfterMS = (mode.getMaxDineFor() + mode.getMaxThinkFor()) * 5;
	startTimeNanos = System.nanoTime();
        requestCounts = new AtomicInteger[numberOfChopsticks];
	philosopherStates 
	    = new AtomicReferenceArray<PhilosopherState>(numberOfChopsticks);
        for (int i = 0; i < numberOfChopsticks; i++) {
            requestCounts[i] = new AtomicInteger(0);
        }
        view = new WatchdogUI(this);
    }

    public int getRequestCount(int chopstickNumber) {
        return requestCounts[chopstickNumber].get();
    }

    public PhilosopherState getPhilosopherState(int philosopherNumber) {
	return philosopherStates.get(philosopherNumber);
    }

    /**
     * Call this method before requesting a chopstick.
     *
     * @param chopstickNumber   The chopstick's number, between in the range
     *                          0..(num-1) inclusive.
     */
    public void aboutToRequest(int chopstickNumber) {
        requestCounts[chopstickNumber].incrementAndGet();
        somethingHappened.set(true);
        view.repaint(10);
    }

    /**
     * Call this method just before you release a chopstick
     */
    public void aboutToRelease(int chopstickNumber) {
        requestCounts[chopstickNumber].decrementAndGet();
        somethingHappened.set(true);
        view.repaint(10);
    }

    /**
     * Call this method when a philosopher's state is set.
     * 
     *
     * @param	num	The philosopher's number, counting from zero.
     * @param   state   The philosopher's state now.
     */
    public void notifyPhilosopherState(int num, PhilosopherState newState) {
	philosopherStates.set(num, newState);
        somethingHappened.set(true);
        view.repaint(10);
    }


    /**
     * Delay for the specified number of milliseconds.  A philosopher must
     * call this while dining, or while thinking.
     *
     * @param ms Delay amount, in milliseconds.
     * @throws InterruptedException	if the current thread is interrupted.
     */
    public void delayForMS(int ms) {
        if (ms == 0) {
            return;
        }
	try {
	    Thread.sleep(ms);
	} catch (InterruptedException ex) {
	    // You should never, ever swallow InterruptedException without
	    // doing something about it.  Here's a minimal, easy thing to
	    // do that at least isn't wrong:
	    Thread.currentThread().interrupt();
	}
    }

    /**
     * Get the total elapsed time since start, in milliseconds.
     */
    public int getElapsedTimeMS()  {
	long elapsed = System.nanoTime() - startTimeNanos;
	assert(elapsed >= 0L);
	return (int) (elapsed / NANOS_PER_MS);
    }

    /**
     * Called by Main to watch over the philosophers.
     */
    public void watch() {
        view.setFrameVisible(true);
	int lastTime = getElapsedTimeMS();
	while (true) {
	    if (somethingHappened.get()) {
		somethingHappened.set(false);
		lastTime = getElapsedTimeMS();
	    } else if (getElapsedTimeMS() - lastTime > abortAfterMS) {
		System.out.println("Watchdog:  Aborting due to inactivity.");
                String ms = String.format("%.2f", (getElapsedTimeMS()/1000.0));
                System.out.println("Total run time:  " + ms + " ms.");
                System.out.println();
                System.out.println("I'll leave the UI up until you close it.");
		return;
	    }
	    delayForMS(1000);
	}
    }
}

