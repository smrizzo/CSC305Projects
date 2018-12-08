import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public int whiteBoardCount;
    public int blackBoardCount;
    public int kibbitzerBoardCount;
    private boolean remoteGame = false;
    private boolean localGame = false;
    private boolean chineseCheckersGame = false;
    private List<ChessController> myControllers = new ArrayList<>();
    private List<CCController> myCCControllers = new ArrayList<>();
    private HashMap<String, Integer> myMap = new HashMap<>();
    public String[] serverCredentials;
    public List<String[]> listOfCredentials = new ArrayList<>();
    public List<List<String>> robotPlayers = new ArrayList<>();
    public HashMap<String , HashMap<Character, Double> > playerMap = new HashMap<>();
    public List<String> robotInfo;
    public String[] robotParse;
    public double secondsPerTurn;
    public boolean isRobot = false;
    public HashMap<Integer, ChessController> mapOfCredentials = new HashMap<>();

    private static void usage() {
        System.out.println("Usage:  java Main (test | chess white | chess black | chess white/black kibbitzer | cch one two etc.. | chess 18.223.24.219,6002,MySessionID)");
        System.exit(1);
    }

    public int parseArguments(String[] args, int index) {

        while (index < args.length) {
            if(args[index].equals("cch")) {
                break;
            }

            if (args[index].equals("chess")) {
                index++;
            }

            if ("white".equals(args[index])) {
                whiteBoardCount++;
                localGame = true;
            } else if ("black".equals(args[index])) {
                blackBoardCount++;
                localGame = true;
            } else if ("kibbitzer".equals(args[index])) {
                kibbitzerBoardCount++;
                localGame = true;
            } else if (args[index].substring(0, 13).equals("18.223.24.219")) {
                parseCredentials(args[index]);
                remoteGame = true;
            }

            index++;
        }
        return index - 1;
    }

    public int parseCCH(String[] args, int index) {

        while(index < args.length) {
            if(args[index].equals("chess")) {
                break;
            }

            if(args[index].equals("cch")) {
                index++;

            }

            if(args[index].length() > 5) {
                if(args[index].substring(0, 5).equals("robot"))
                    isRobot = true;
                index = parseRobot(args, index);
            } else {
                if(myMap.containsKey(args[index])) {
                    myMap.put(args[index], myMap.get(args[index]) + 1);
                } else {
                    myMap.put(args[index], 1);
                }
                index++;
            }

        }

        return index - 1;

    }

    public int parseRobot(String[] args, int index) {

        while(index < args.length) {
            robotParse = args[index].split(",");
            robotInfo = Arrays.asList(robotParse);
            robotPlayers.add(robotInfo);

            String firstKey = robotInfo.get(1);
            if(playerMap.containsKey(firstKey)) {
                double keyValue = Double.parseDouble(robotInfo.get(2));
                for(int i = 3; i < robotInfo.size(); i++) {
                    HashMap<Character, Double> tempMap = playerMap.get(firstKey);
                    Character secondKey = robotInfo.get(i).charAt(0);
                    if(!tempMap.containsKey(secondKey)) {
                        tempMap.put(secondKey, keyValue);
                    }
                }
            } else {
                double keyValue = Double.parseDouble(robotInfo.get(2));
                playerMap.put(firstKey, new HashMap<>());
                for(int i = 3; i < robotInfo.size(); i++) {
                    HashMap<Character, Double> tempMap = playerMap.get(firstKey);
                    Character secondKey = robotInfo.get(i).charAt(0);
                    if(!tempMap.containsKey(secondKey)) {
                        tempMap.put(secondKey, keyValue);
                    }
                }
            }
            System.out.print('\n');
            index++;
            break;
        }

        return index;
    }

    public void parseCredentials(String credientials) {
        listOfCredentials.add(credientials.split(","));

    }

    public void welcome() {
        System.out.println("For Chess Board Only");
        System.out.println("To quit a specific a chess game press the q key while correct board is selected");
    }


    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {

        if (args.length < 1) {
            usage();
        }

        Main main = new Main();
        main.welcome();
        if ("test".equals(args[0])) {
            (new MyTest()).runTests();
            System.exit(0);
        }

        for(int i = 0; i < args.length; i++) {
            if ("cch".equals(args[i])) {
                main.chineseCheckersGame = true;
                i = main.parseCCH(args, i);
            } else if ("chess".equals(args[i])) {
                i = main.parseArguments(args, i);
            } else {
                usage();
            }

        }

        if(main.chineseCheckersGame) {
            for(String key: main.myMap.keySet()) {
                Integer value = main.myMap.get(key);
                if(main.playerMap.containsKey(key)) {
                    main.myCCControllers.add(new CCController(new StarModel(), value, key, main.playerMap.get(key)));
                } else {
                    main.myCCControllers.add(new CCController(new StarModel(), value, key, null));
                }

            }
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
