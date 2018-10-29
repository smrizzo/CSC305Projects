import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Main {
    boolean madeMoveBlack;
    boolean madeMoveWhite;

    public static void main(String[] args) throws IOException, InterruptedException {

//        Client client = new Client();
//        client.Connect();
        Socket socket = new Socket("18.223.24.219", 6002);
        WriteToServer writeToServer = new WriteToServer(socket);
        ReadFromServer readFromServer = new ReadFromServer(socket);
        Thread write = new Thread(writeToServer);
        Thread readFrom = new Thread(readFromServer);
        write.start();
        readFrom.start();


        //readFromServer.getInitialStart();

//        String initialStart = readFromServer.getInitialStart();
//        System.out.println(initialStart);
//        Character mColorOfPlayer = readFromServer.getInitialColor();
//        System.out.println(mColorOfPlayer);
//        String initialCommand = readFromServer.getInitialCommand();
//        System.out.println(initialCommand);
//        String initialBoard = readFromServer.getInitialBoard();
//        System.out.println(initialBoard);


        //System.out.println("Waiting at step 1...");
        List<String> myInitialList = readFromServer.getInitialResponse();
        for(int i = 0; i < myInitialList.size(); i++) {
            //System.out.println("Item returned from intial list:" + myInitialList.get(i));
            System.out.println(myInitialList.get(i));
        }
        System.out.print("\n");

        while(true) {

            System.out.println("waiting for board to change");
            List<String> myList = readFromServer.getResponse();
            System.out.println("Recieved from server");
            for(int i = 0; i < myList.size(); i++) {
                //System.out.println("Item returned from list:" + myList.get(i));
                System.out.println(myList.get(i));
            }

        }




//       String response =  readFromServer.getResponse();
//       System.out.println(response);


//        try(Socket socket = new Socket("18.223.24.219", 6002)) {
//            //Socket socket2 = new Socket("18.223.24.219", 6002);
////            BufferedReader input = new BufferedReader(
////                    new InputStreamReader(socket.getInputStream())
////            );
//            //PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//            Main main = new Main();
//            long password = 0x5c34a15e25c9a63dL;
//            int protocolVersion = 2;
//            String gameHeaderName = "chess";
//            int gameHeaderVersion = 1;
//            String sessionID = "smrizzo345";
//            String m = "move";
//            int fromX;
//            int fromY;
//            int toX;
//            int toY;
//            DataInputStream input = new DataInputStream(socket.getInputStream());
//            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
//            output.writeLong(password);
//            output.writeInt(protocolVersion);
//            output.writeUTF(gameHeaderName);
//            output.writeInt(gameHeaderVersion);
//            output.writeUTF(sessionID);
//            output.flush();
//            String response;
//            int mWhoseMove = 0;
//            char Color;
//            String echoString;
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("client: waiting...");
//            response = input.readUTF();
//            System.out.println(response);
//            Color = input.readChar();
//            System.out.println(Color);
//            response = input.readUTF();
//            System.out.println("response: " + response);
//            response = input.readUTF();
//            System.out.println("response: " + response);
//            if(Color == 'b') {
//                main.madeMoveBlack = true;
//                main.madeMoveWhite = true;
//            } else if (Color == 'w') {
//                main.madeMoveBlack = true;
//                main.madeMoveWhite = false;
//            }
//
//            while(true) {
//
//
//                if(!main.madeMoveWhite && main.madeMoveBlack) {
//                    System.out.println("Enter command: move, followed by x,y->x,y");
//                    m = scanner.next();
//                    System.out.println("m now has: " + m);
//                    System.out.println("Waiting for coordinates");
//                    fromX = scanner.nextInt();
//                    fromY = scanner.nextInt();
//                    toX = scanner.nextInt();
//                    toY = scanner.nextInt();
//                    output.writeUTF(m);
//                    output.writeInt(fromX);
//                    output.writeInt(fromY);
//                    output.writeInt(toX);
//                    output.writeInt(toY);
//                    output.flush();
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    main.madeMoveWhite = true;
//                    main.madeMoveBlack = true;
//                } else if (!main.madeMoveBlack && main.madeMoveWhite){
//                    System.out.println("Enter command: move, followed by x,y->x,y");
//                    m = scanner.next();
//                    System.out.println("m now has: " + m);
//                    System.out.println("Waiting for coordinates");
//                    fromX = scanner.nextInt();
//                    fromY = scanner.nextInt();
//                    toX = scanner.nextInt();
//                    toY = scanner.nextInt();
//                    output.writeUTF(m);
//                    output.writeInt(fromX);
//                    output.writeInt(fromY);
//                    output.writeInt(toX);
//                    output.writeInt(toY);
//                    output.flush();
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    main.madeMoveWhite = true;
//                    main.madeMoveBlack = true;
//
//                } else {
//                    System.out.println("Waiting for response...");
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    response = input.readUTF();
//                    System.out.println("response: " + response);
//                    System.out.println("Got passed responses after if statement");
//                    //if it was
//                    if(Color == 'b') {
//                        main.madeMoveWhite = true;
//                        main.madeMoveBlack = false;
//                    } else if (Color == 'w') {
//                        main.madeMoveBlack = true;
//                        main.madeMoveWhite = false;
//                    }
//                }
//
//
//            }
//
//        } catch (IOException e) {
//            System.out.println("Client error: " + e.getMessage());
//        }
    }
}
