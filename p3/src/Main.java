import java.io.IOException;

public class Main {


    private static void usage() {
        System.out.println("Usage:  java Main (test | chess white | chess black | chess 18.223.24.219 6002 MySessionID)");
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            usage();
        }

        ChessModel model = new ChessModel();
        ChessController controller = new ChessController(model);

        if("chess".equals(args[0])) {
            if("white".equals(args[1])) {
                controller.setWhiteBoard();
            } else if("black".equals(args[1])) {
                controller.setBlackBoard();
            } else if (("18.223.24.219".equals(args[1])) && ("6002".equals(args[2])) && ("smrizzo345".equals(args[3]))) {
                //We are going to connect to server
                controller.setIpAddress(args[1]);
                controller.setPort(args[2]);
                controller.setMySessionId(args[3]);
                controller.connectToServer = true;
            } else {
                usage();
            }
        } else {
            usage();
        }

        controller.runThreads();






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
