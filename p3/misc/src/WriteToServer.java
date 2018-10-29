import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WriteToServer implements Runnable {
    private Socket socket;
    private DataOutputStream output;
    private long password;
    private int protocolVersion;
    private String gameHeaderName;
    private int gameHeaderVersion;
    private String sessionID;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    String command = "";
    private Queue<Integer> myNumList = new LinkedList<>();
    boolean madeMove = false;



    public WriteToServer(Socket socket) {
        this.socket = socket;
        this.password = 0x5c34a15e25c9a63dL;
        this.protocolVersion = 2;
        this.gameHeaderName = "chess";
        this.gameHeaderVersion = 1;
        this.sessionID = "smrizzo345";
    }

    public void InitializeConnection() throws IOException {
        output.writeLong(password);
        output.writeInt(protocolVersion);
        output.writeUTF(gameHeaderName);
        output.writeInt(gameHeaderVersion);
        output.writeUTF(sessionID);
        output.flush();
    }

    public void move1() throws IOException {
        lock.lock();
        try {
            output.writeUTF("move");
            output.writeInt(0);
            output.writeInt(6);
            output.writeInt(1);
            output.writeInt(0);
            output.flush();
        } finally {
            lock.unlock();
        }

    }

    public void move2() throws IOException {
        lock.lock();
        try {
            output.writeUTF("move");
            output.writeInt(3);
            output.writeInt(0);
            output.writeInt(3);
            output.writeInt(7);
            output.flush();
        } finally {
            lock.unlock();
        }
    }

    public void promotePawn() throws IOException {
        lock.lock();
        try {
            output.writeUTF("promote pawn");
            output.writeInt(1);
            output.writeInt(0);
            output.writeChar('Q');
            output.flush();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            output = new DataOutputStream(socket.getOutputStream());
            lock.lock();
            InitializeConnection();
            lock.unlock();

            while(true) {
                System.out.println("Enter command: move, followed by x,y->x,y");

                    String m = scanner.next();
                    if(m.equals("m1")) {
                        move1();
                    } else if(m.equals("m2")) {
                        move2();
                    } else if(m.equals("end")) {
                        break;
                    } else if(m.equals("p")) {
                        promotePawn();
                    }
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }




    }
}
