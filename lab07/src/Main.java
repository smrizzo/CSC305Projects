import other.OneNightInBangkok;
import other.Results;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private Integer threadCount = 0;
    private long startingPointInRange = 0;
    private long finishPointInRange = 0;

    public void usage() {
        System.out.println("Enter correct arguments: threadCount startNumber finishNumber");
    }

    public void setArguments(String [] args) {
        threadCount = Integer.parseInt(args[0]);
        startingPointInRange = Long.parseLong(args[1]);
        finishPointInRange = Long.parseLong(args[2]);
    }

    public long getStart() {
        return this.startingPointInRange;
    }

    public long getFinish() {
        return finishPointInRange;
    }

    public static void main(String [] args)  {
        Main main = new Main();
        Results result = new Results();
        ExecutorService executor;
        boolean done = false;

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

        try {
            if(!executor.awaitTermination(3, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result.printResults();


    }

}
