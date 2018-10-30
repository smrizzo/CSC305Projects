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
    private Character mColorOfPlayer;
    private String initialCommand = "";
    String initialBoard = "";
    ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    Queue<String> myQueue = new LinkedList<>();
    Queue<String> myOtherQueue = new LinkedList<>();

    public ReadFromServer(Socket socket) {
        this.socket = socket;
    }

    public void addString(String response) {
        //System.out.println("Adding: " + response);
        lock.lock();
        try {
            myQueue.add(response);
            condition.signalAll();
            //checkSize();
        } finally {
            lock.unlock();
        }
    }

    public void addToInitialQueue(String response) {
        //System.out.println("Adding: " + response);
        lock.lock();
        try {
            myOtherQueue.add(response);
            condition.signalAll();
            //checkInitialQueue();
        } finally {
            lock.unlock();
        }
    }

    public List<String> getInitialResponse() throws InterruptedException {
        lock.lock();
        try {
            while(myOtherQueue.size() != 4 ) {
                //System.out.println("Checking condition for myOtherQueue size: " + myOtherQueue.size());
                condition.await();
            }
            //System.out.println("adding elements from OtherQueue to list and returning to main");
            List<String> otherResult = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                //System.out.println("adding queue item to list from myOtherQueue: " + myOtherQueue.peek());
                otherResult.add(myOtherQueue.remove());
            }

            return otherResult;
        } finally {
            lock.unlock();
        }
    }

    public List<String> getResponse() throws InterruptedException {
        lock.lock();
        try {
            while(myQueue.size() != 2) {
                //System.out.println("Checking length of myQueue which is size: " + myQueue.size());
                condition.await();
            }
            //System.out.println("adding elements from queue to list and returning to main");
            List<String> result = new LinkedList<>();
            for (int i = 0; i < 2; i++) {

                result.add(myQueue.remove());
            }

            return result;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {


        try {

            input = new DataInputStream(socket.getInputStream());

            System.out.println("Waiting...");
            initialStart = input.readUTF();
            addToInitialQueue(initialStart);

            mColorOfPlayer = input.readChar();

            addToInitialQueue(mColorOfPlayer.toString());

            initialCommand = input.readUTF();
            addToInitialQueue(initialCommand);

            initialBoard = input.readUTF();
            addToInitialQueue(initialBoard);

            while(true) {
                response = input.readUTF();

                addString(response);
                response = input.readUTF();
                addString(response);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
