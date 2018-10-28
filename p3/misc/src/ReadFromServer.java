import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReadFromServer implements Runnable {

    Socket socket;
    DataInputStream input;
    String response = "";
    String initialStart = "";
    Character mColorOfPlayer = '\0';
    String initialCommand = "";
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

    public void checkInitialQueue() {
        if(myOtherQueue.size() == 4) {
            condition.signalAll();
        }
    }

    public void checkSize() {
        if(myQueue.size() == 2) {
            condition.signalAll();
        }
    }

    public List<String> getInitialResponse() throws InterruptedException {
        lock.lock();
        try {
            while(myOtherQueue.size() != 4 ) {
                System.out.println("Checking condition for myOtherQueue size: " + myOtherQueue.size());
                condition.await();
            }
            System.out.println("adding elements from OtherQueue to list and returning to main");
            List<String> otherResult = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                System.out.println("adding queue item to list from myOtherQueue: " + myOtherQueue.peek());
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
                System.out.println("Checking length of myQueue which is size: " + myQueue.size());
                condition.await();
            }
            //System.out.println("adding elements from queue to list and returning to main");
            List<String> result = new LinkedList<>();
            for (int i = 0; i < 2; i++) {
                System.out.println("adding queue item to list from myQueue: " + myQueue.peek());
                result.add(myQueue.remove());
            }
            if(!myQueue.isEmpty()) {
                System.out.println("Myqueue is not empty" + myQueue.size());
                //System.out.println("What was found in queue: " + myQueue.remove());
                //myQueue.remove();
            }

            return result;
        } finally {
            lock.unlock();
        }

    }

    public String getInitialStart() throws InterruptedException {
        lock.lock();
        try {
            while(initialStart.isEmpty()) {
                condition.await();
            }
            return initialStart;
        } finally {
            lock.unlock();
        }
    }
    public Character getInitialColor() throws InterruptedException {
        lock.lock();
        try {
            while(mColorOfPlayer != '\0') {
                condition.await();
            }
            return mColorOfPlayer;
        } finally {
            lock.unlock();
        }
    }
    public String getInitialCommand() throws InterruptedException {
        lock.lock();
        try {
            while(initialCommand.isEmpty()) {
                condition.await();
            }
            return initialCommand;
        } finally {
            lock.unlock();
        }
    }

    public String getInitialBoard() throws InterruptedException {
        lock.lock();
        try {
            while(initialBoard.isEmpty()) {
                condition.await();
            }
            return initialBoard;
        } finally {
            lock.unlock();
        }
    }

    public void setUpInitalRead(DataInputStream input) throws IOException {
        initialStart = input.readUTF();
        mColorOfPlayer = input.readChar();
        initialCommand = input.readUTF();
        initialBoard = input.readUTF();
    }

    @Override
    public void run() {


        try {
            //System.out.println("Should be reading in from server");
            input = new DataInputStream(socket.getInputStream());
            //setUpInitalRead(input);
            System.out.println("Waiting...");
            initialStart = input.readUTF();
            addToInitialQueue(initialStart);
            //System.out.println(response);
            mColorOfPlayer = input.readChar();
            //addString(Color.toString());
            addToInitialQueue(mColorOfPlayer.toString());

            initialCommand = input.readUTF();
            addToInitialQueue(initialCommand);
            //System.out.println("Getting response from read: " + response);
            //addString(response);
            initialBoard = input.readUTF();
            addToInitialQueue(initialBoard);
            //System.out.println("Getting response from read: " + response);
            //addString(response);
            //System.out.println("Got passed initial reads");

            //System.out.println(Color);
            //System.out.println("Queue size: " + myQueue.size());
            while(true) {
                response = input.readUTF();
                //System.out.println("Getting response from read: " + response);
                addString(response);
                response = input.readUTF();
                //System.out.println("Getting response from read: " + response);
                addString(response);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
