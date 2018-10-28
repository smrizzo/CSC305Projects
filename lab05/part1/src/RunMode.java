
/**
 * Different configurations of the philosopher's table.  Feel free to ADD
 * enum values, but don't change the ones that are here.
 */

public enum RunMode implements others.Watchdog.Mode {
    SLOW(5, true, 4000, 6000),
    RIGHT(5, true, 500, 10),
    LOWER(5, false, 500, 10),
    FT_RIGHT(5, true, 500, 0),
    FT_LOWER(5, false, 500, 0),	// Fast thinker
    FD_RIGHT(5, true, 25, 500),
    FD_LOWER(5, false, 25, 500),  // Fast diner
    MANY(21, true, 500, 10);

    /**
     * How many philosophers?
     */
    public final int numberOfPhilosophers;

    /** 
     * Should a philosopher take the right chopstick first?  If not,
     * they take the lower-numbered chopstick first.
     */
    public final boolean rightChopstickFirst;

    /**
     * Max amount of time a philosopher dines at a time, in milliseconds.
     * Each meal takes a random amount of time between 0 and this
     * value, inclusive.
     */
    public final int maxDineFor;

    /**
     * Max amount of time a thinks at a time, in milliseconds.
     * Each meal takes a random amount of time between 0 and this
     * value, inclusive.
     */
    public final int maxThinkFor;

    private RunMode(int num, boolean right, int dine, int think) {
	this.numberOfPhilosophers = num;
	this.rightChopstickFirst = right;
	this.maxDineFor = dine;
	this.maxThinkFor = think;
    }

    public int getNumberOfPhilosophers() {
        return numberOfPhilosophers;
    }

    public int getMaxDineFor() {
        return maxDineFor;
    }

    public int getMaxThinkFor() {
        return maxThinkFor;
    }
}
