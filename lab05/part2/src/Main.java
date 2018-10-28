
import others.Watchdog;

/**
 * Main class.  DO NOT MODIFY THIS FILE!  The autgrader will swap in its
 * own version of this file.
 */

public class Main {

    private static void usage() {
	System.out.println();
	System.out.println("Usage:  \"java Main <configuration>\""
		           + " <configuration> is one of:");
	for (RunMode mode : RunMode.values()) {
	    System.out.println("            " + mode.toString().toLowerCase());
	}
	System.out.println();
	System.exit(1);
    }

    public static void main(String[] args) {
	if (args.length != 1) {
	    usage();
	}
	boolean found = false;
	for (RunMode mode : RunMode.values()) {
	    if (mode.toString().equalsIgnoreCase(args[0])) {
                found = true;
                System.out.println("Number of philosophers:  "+mode.numberOfPhilosophers);
                System.out.println("Right chopstick first:  " + mode.rightChopstickFirst);
                System.out.println("Max dine for:  " + mode.maxDineFor + " ms.");
                System.out.println("Max think for:  " + mode.maxThinkFor + " ms.");
                System.out.println();
		Watchdog watchdog = new Watchdog(mode);
		(new PhilosophersTable(mode, watchdog)).start();
		watchdog.watch();
	    }
	}
	if (!found) {
            usage();
	}
    }
}

