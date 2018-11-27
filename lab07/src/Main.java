import other.OneNightInBangkok;
import other.Results;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private Integer threadCount = 0;
    private long startingPointInRange = 0;
    private long finishPointInRange = 0;
    private List<Thread> threads = new ArrayList<>();
    ArrayDeque<OneNightInBangkok> bangkockQueue = new ArrayDeque<>();

    public void usage() {
        System.out.println("Enter correct arguments: threadCount startNumber finishNumber");
    }

    public void setArguments(String [] args) {
        threadCount = Integer.parseInt(args[0]);
        startingPointInRange = Long.parseLong(args[1]);
        finishPointInRange = Long.parseLong(args[2]);
    }

    public void setThreads(Integer threadsCount) {
        for(int i = 0; i < threadsCount; i++) {

        }
    }

    public void addToQueue(long num, Results result) {
        bangkockQueue.add(new OneNightInBangkok(num, result))
;    }

    public OneNightInBangkok getBangkok() {
        return bangkockQueue.remove();
    }

    public long getStart() {
        return this.startingPointInRange;
    }

    public long getFinish() {
        return finishPointInRange;
    }

    public static void main(String [] args) {
        Main main = new Main();
        Results result = new Results();
        ExecutorService executor;

        if(args.length < 1) {
            main.usage();
        }

        main.setArguments(args);
        executor = Executors.newFixedThreadPool(main.threadCount);
        System.out.println("threadCount: " + main.threadCount);
        System.out.println("starting point: " + main.startingPointInRange);
        System.out.println("finish point: " + main.finishPointInRange);


        for(long i = main.getStart(); i < main.getFinish(); i++) {
            executor.execute(new OneNightInBangkok(i, result));
        }

        executor.shutdown();

        while(!executor.isTerminated()) {

        }
        result.printResults();


    }

}
