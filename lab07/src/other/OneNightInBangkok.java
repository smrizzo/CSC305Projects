
package other;

public class OneNightInBangkok implements Runnable {

    private final long num;
    private final Results results;

    public OneNightInBangkok(long num, Results results) {
        this.num = num;
        this.results = results;
    }

    public void run() {
        switch(Calculator.numFactors(num)) {
            case 2:     results.makeTheWorldYourOyster();
                        break;
            case 3:     results.findAGodInEveryGoldenCloister();
                        break;
            case 4:     results.getThaidAndTalkToATouristWhoseEveryMovesAmongThePurest();
                        break;
            case 5:     results.makeAHardManHumble();
                        break;
            case 6:     results.findNotMuchBetweenDespairAndEcstasy();
                        break;
            case 7:     results.feelTheDevilWalkingNextToYou();
                        break;
            default:    { }
        }
    }
}
