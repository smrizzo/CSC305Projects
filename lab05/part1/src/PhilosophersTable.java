
import others.Watchdog;

//
// Make your table class here.  Feel free to write this in Kotlin.
//
public class PhilosophersTable {

    public PhilosophersTable(RunMode mode, Watchdog watchdog) {

        Thread[] philosophers = new Philosopher[mode.getNumberOfPhilosophers()];
        Chopstick[] chopsticks = new Chopstick[mode.getNumberOfPhilosophers()];

        for(int i = 0;i < philosophers.length; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        for(int i = 0; i < philosophers.length; i++) {

            philosophers[i] = new Philosopher(i, watchdog, mode,chopsticks[i], chopsticks[(i + 1) % mode.getNumberOfPhilosophers()]);
            philosophers[i].start();
        }


    }

    public void start() {
        System.out.println("Somebody needs to create one thread per philosopher here!");
    }
}
