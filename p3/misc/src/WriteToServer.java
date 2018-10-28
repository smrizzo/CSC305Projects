import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class WriteToServer implements Runnable {
    Socket socket;
    DataOutputStream output;
    long password;
    int protocolVersion;
    String gameHeaderName;
    int gameHeaderVersion;
    String sessionID;
    ReentrantLock lock = new ReentrantLock();
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


    public void setCommand(String command) {
        lock.lock();
        try {
            this.command = command;
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
    public void addToQueue(Integer num) throws InterruptedException {
        //System.out.println("Adding," + num + " ,something queue");
        lock.lock();
        try {
            myNumList.add(num);
            System.out.println("queue size" + myNumList.size());
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void writeMoveToServer() throws InterruptedException, IOException {
        lock.lock();
        try {
            while(myNumList.size() != 4) {
                condition.await();
            }
            //System.out.println("command: " + command);
            output.writeUTF(command);
            for (int i = 0; i < 4; i++) {
                System.out.println("Outputing to server");
                output.writeInt(myNumList.remove());
            }
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
                try {

                    String m = scanner.next();
                    setCommand(m);
                    Integer fromX = scanner.nextInt();
                    addToQueue(fromX);
                    Integer fromY = scanner.nextInt();
                    addToQueue(fromY);
                    Integer toX = scanner.nextInt();
                    addToQueue(toX);
                    Integer toY = scanner.nextInt();
                    addToQueue(toY);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }




    }
}
