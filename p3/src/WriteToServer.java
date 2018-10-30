import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
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

    public void serverMove(int fromx, int fromy, int toX, int toY) throws IOException {
        lock.lock();
        try {
            output.writeUTF("move");
            output.writeInt(fromx);
            output.writeInt(fromy);
            output.writeInt(toX);
            output.writeInt(toY);
            output.flush();
        } finally {
            lock.unlock();
        }

    }

    public void serverPromotePawn(int x, int y, char c) throws IOException {
        lock.lock();
        try {
            output.writeUTF("promote pawn");
            output.writeInt(x);
            output.writeInt(y);
            output.writeChar(c);
            output.flush();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {

        try {
            output = new DataOutputStream(socket.getOutputStream());
            lock.lock();
            InitializeConnection();
            lock.unlock();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }




    }
}
