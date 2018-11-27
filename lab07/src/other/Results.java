
package other;

import java.util.concurrent.atomic.AtomicInteger;

public class Results {

    private AtomicInteger oyster = new AtomicInteger();
    private AtomicInteger cloister = new AtomicInteger();
    private AtomicInteger thaid = new AtomicInteger();
    private AtomicInteger humble = new AtomicInteger();
    private AtomicInteger ecstasy = new AtomicInteger();
    private AtomicInteger devil = new AtomicInteger();

    public Results() {
    }

    public void makeTheWorldYourOyster() {
        oyster.incrementAndGet();
    }

    public void findAGodInEveryGoldenCloister() {
        cloister.incrementAndGet();
    }

    public void getThaidAndTalkToATouristWhoseEveryMovesAmongThePurest() {
        thaid.incrementAndGet();
    }

    public void makeAHardManHumble() {
        humble.incrementAndGet();
    }

    public void findNotMuchBetweenDespairAndEcstasy() {
        ecstasy.incrementAndGet();
    }

    public void feelTheDevilWalkingNextToYou() {
        devil.incrementAndGet();
    }

    public void printResults() {
        System.out.println();
        print("made the world your oyster", oyster);
        print("found a god in every golden cloister", cloister);
        print("got Thai'd and talked to a\n" +
              "\ttourist whose every move's among the purest", thaid);
        print("made a hard man humble", humble);
        print("made me feel the devil walking next to me", devil);
        System.out.println();
    }

    private void print(String msg, AtomicInteger count) {
        System.out.println("One night in Bangkok " + msg + ":  " + count);
    }
}
