import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public int whiteBoardCount;
    public int blackBoardCount;
    public int kibbitzerBoardCount;
    boolean remoteGame = false;
    boolean localGame = false;
    List<ChessController> myControllers = new ArrayList<>();
    public String[] serverCredentials;
    public List<String[]> listOfCredentials = new ArrayList<>();
    public HashMap<Integer, ChessController> mapOfCredentials = new HashMap<>();


    private static void usage() {
        System.out.println("Usage:  java Main (test | chess white | chess black | chess white/black kibbitzer | chess 18.223.24.219,6002,MySessionID)");
        System.exit(1);
    }


    public void parseArguments(String[] args) {
        for(int i = 1; i < args.length; i++) {
            if("white".equals(args[i])) {
                whiteBoardCount++;
                localGame = true;
            } else if("black".equals(args[i])) {
                blackBoardCount++;
                localGame = true;
            } else if("kibbitzer".equals(args[i])) {
                kibbitzerBoardCount++;
                localGame = true;
            } else if (args[i].substring(0, 13).equals("18.223.24.219")){
                parseCredentials(args[i]);
                remoteGame = true;
            }
        }
    }

    public void parseCredentials(String credientials) {
        listOfCredentials.add(credientials.split(","));

    }

    public void welcome() {
        System.out.println("To quit a specific a game press the q key while correct board is selected");
    }


    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {

        if (args.length < 1) {
            usage();
        }
        Main main = new Main();

        if("chess".equals(args[0])) {
            main.welcome();
            main.parseArguments(args);

        } else if ("test".equals(args[0])) {
            (new MyTest()).runTests();
            System.exit(0);
        } else {
            usage();
        }


        if(main.localGame) {
            main.myControllers.add(new ChessController(new ChessModel(), main.blackBoardCount, main.whiteBoardCount, main.kibbitzerBoardCount));
        }

        if(main.remoteGame) {
            for(int i = 0; i < main.listOfCredentials.size(); i++) {
                main.mapOfCredentials.put(i, new ChessController(new ChessModel(), i, main.listOfCredentials.get(i)[0], Integer.parseInt(main.listOfCredentials.get(i)[1]),
                        main.listOfCredentials.get(i)[2]));

            }
        }
    }
}
