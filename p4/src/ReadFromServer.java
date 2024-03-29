import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadFromServer implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private String response = "";
    private String initialStart = "";
    private Character mColorOfPlayer = '\0';
    private String initialCommand = "";
    private String initialBoard = "";
    private volatile boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Queue<String> myQueue = new LinkedList<>();
    private Queue<String> myOtherQueue = new LinkedList<>();

    public ReadFromServer(Socket socket) {
        this.socket = socket;
    }

    public void addString(String response) {
        //System.out.println("Adding: " + response);
        lock.lock();
        try {
            myQueue.add(response);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    //add something initial queue
    public void addToInitialQueue(String response) {
        lock.lock();
        try {
            myOtherQueue.add(response);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    //Method for returning intitial input from server after connection
    public List<String> getInitialResponse() throws InterruptedException {
        lock.lock();
        try {
            while(myOtherQueue.size() != 4 ) {
                condition.await();
            }

            List<String> otherResult = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                otherResult.add(myOtherQueue.remove());
            }

            return otherResult;
        } finally {
            lock.unlock();
        }
    }

    //This gets response from server
    public List<String> getResponse() throws InterruptedException {
        lock.lock();
        try {
            while(myQueue.size() != 2) {
                condition.await();
            }

            List<String> result = new LinkedList<>();
            for (int i = 0; i < 2; i++) {

                result.add(myQueue.remove());
            }

            return result;
        } finally {
            lock.unlock();
        }

    }

    public void shutDown() {
        done = true;
    }

    @Override
    public void run() {
        try {

            input = new DataInputStream(socket.getInputStream());

            //Getting initial input from server after connecting
            initialStart = input.readUTF();
            addToInitialQueue(initialStart);

            mColorOfPlayer = input.readChar();
            addToInitialQueue(mColorOfPlayer.toString());

            initialCommand = input.readUTF();
            addToInitialQueue(initialCommand);

            initialBoard = input.readUTF();
            addToInitialQueue(initialBoard);

            while(!done) {

                response = input.readUTF();
                if(response.equals("end")) {
                    addString(response);
                    addString(response);
                    shutDown();
                } else {
                    addString(response);
                    response = input.readUTF();
                    addString(response);
                }

            }
            input.close();

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
            Thread.currentThread().interrupt();


        }
    }
}
