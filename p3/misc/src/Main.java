import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Main {
    boolean madeMoveBlack;
    boolean madeMoveWhite;
    Character[][] piecesFromServer = new Character[9][9];

    public void parseBoard(String board) {
        String getCol;
        int beginIndex = 0;
        int endIndex = 8;
        int colCounter = 0;
        while(colCounter < 9 && endIndex <= board.length()) {
            System.out.println("Going to parse this Board now");
            getCol = board.substring(beginIndex, endIndex);
            for(int i = 0; i < getCol.length(); i++) {
                piecesFromServer[colCounter + 1][i + 1] = getCol.charAt(i);
            }

            System.out.println("Column: " + colCounter + ":" + getCol + ":::::");
            beginIndex += 8;
            endIndex += 8;
            colCounter++;
        }

    }

    public void printPieces() {

        String ss = "";
        System.out.print("\n");
        for(int col = 0; col < 9; col++) {
            for(int row = 0; row < 9; row++) {
                ss = ss.concat(piecesFromServer[row][col] + " ");
            }
            ss = ss.concat("\n");
        }
        ss = ss.concat("\n");
        System.out.println(ss);

    }


    public static void main(String[] args) throws IOException, InterruptedException {

//        Client client = new Client();
//        client.Connect();
        Main main = new Main();
        Socket socket = new Socket("18.223.24.219", 6002);
        System.out.println("Waiting for socket to connect");
        WriteToServer writeToServer = new WriteToServer(socket);
        ReadFromServer readFromServer = new ReadFromServer(socket);
        Thread write = new Thread(writeToServer);
        Thread readFrom = new Thread(readFromServer);
        write.start();
        readFrom.start();
        System.out.println("Started threads");


        //System.out.println("Waiting at step 1...");
        List<String> myInitialList = readFromServer.getInitialResponse();

        if(myInitialList.get(1).charAt(0) == '\0') {
            System.out.println("We found a kibbitzer");
        }
        for(int i = 0; i < myInitialList.size(); i++) {
            //System.out.println("Item returned from intial list:" + myInitialList.get(i));
            System.out.println(myInitialList.get(i));
        }
        System.out.print("\n");


        while(true) {

            System.out.println("waiting for board to change");
            List<String> myList = readFromServer.getResponse();
            System.out.println("Recieved from server");
            if(myList.get(0).equals("board")) {
                main.parseBoard(myList.get(1));
                main.printPieces();

            }

            for(int i = 0; i < myList.size(); i++) {
                //System.out.println("Item returned from list:" + myList.get(i));
                System.out.println(myList.get(i));
            }

        }
    }
}
