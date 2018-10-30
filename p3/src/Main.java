import java.io.IOException;

public class Main {

    public boolean whiteColor;
    public boolean blackColor;
    public boolean kibbitzers;
    public boolean connectingToServer;
    public int whiteBoardCount;
    public int blackBoardCount;
    public int kibbitzerBoardCount;
    String[] serverCredentials;
    private static void usage() {
        System.out.println("Usage:  java Main (test | chess white | chess black | chess white/black kibbitzer | chess 18.223.24.219 6002 MySessionID)");
        System.exit(1);
    }

    public void setCounters(String[] args) {
        System.out.println("Got inside here");
        for(int i = 1; i < args.length; i++) {
            if("white".equals(args[i])) {
                whiteBoardCount++;
            } else if("black".equals(args[i])) {
                blackBoardCount++;
            } else if("kibbitzer".equals(args[i])) {
                kibbitzerBoardCount++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            usage();
        }
        Main main = new Main();
        System.out.println(args.length);
        if("chess".equals(args[0])) {
            if("white".equals(args[1]) || "black".equals(args[1])) {
                main.setCounters(args);
                System.out.println("Number of whiteboards:" + main.whiteBoardCount);
                System.out.println("Number of blackboards:" + main.blackBoardCount);
                System.out.println("Number of kibbitzer boards:" + main.kibbitzerBoardCount);
            }
            else if (args[1].substring(0, 13).equals("18.223.24.219")){
                System.out.println("We are gonna connect to server now");
                main.serverCredentials = args[1].split(",");
                for(int i = 0; i < main.serverCredentials.length; i++) {
                    System.out.println(main.serverCredentials[i]);
                }
            }
            else {
                usage();
            }
        } else {
            usage();
        }
        ChessModel model = new ChessModel();
        ChessController controller = new ChessController(model, main.blackBoardCount, main.whiteBoardCount, main.kibbitzerBoardCount);
        //controller.runThreads();






//        ChessView view = new ChessView(false, model, controller);
//        ChessView view2 = new ChessView(true, model, controller);
//        Thread t = new Thread(view);
//        t.start();
//        Thread t2 = new Thread(view2);
//        t2.start();


        //view.printPieces();
//        Main m = new Main();
//        m.runMain(args);

    }
}
