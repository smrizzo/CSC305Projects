import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class WriteToServer implements Runnable{

    private Socket socket;
    private DataOutputStream output;
    private long password;
    private int protocolVersion;
    private String gameHeaderName;
    private int gameHeaderVersion;
    private String sessionID;
    private volatile boolean done;
    private ReentrantLock lock = new ReentrantLock();


    public WriteToServer(Socket socket, String SessionID) {
        this.socket = socket;
        this.password = 0x5c34a15e25c9a63dL;
        this.protocolVersion = 2;
        this.gameHeaderName = "chess";
        this.gameHeaderVersion = 1;
        this.sessionID = SessionID;
        this.done = false;
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

    public void endGame() throws IOException {
        lock.lock();
        try {
            output.writeUTF("end");
            output.flush();
            done = true;
        } finally {
            lock.unlock();
        }
    }

    public void closeWriteStream() throws IOException {
        output.close();
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
            System.out.println("catch in write");
            Thread.currentThread().interrupt();
        }

    }
}
