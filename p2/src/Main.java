import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private ChessBoard chessBoard = new ChessBoard();
    private ChessTextMode chessTextMode = new ChessTextMode();

    static int width = 9;
    private static int pixelSize = 100;
    private boolean stopGame = false;
    private boolean isKibbitzer = false;
    private Integer numOfKibbitzers;
    private Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
    private List<Character> myCharList = Arrays.asList(borderChars);
    private Character borderNumbers[] = new Character[]{'8', '7', '6', '5', '4', '3', '2', '1'};
    private ArrayList<Character> textMove = new ArrayList<>(4);
    private List<Character> myNumList = Arrays.asList(borderNumbers);


    private void setKeyClick(char c) {

        textMove.add(c);

        if(textMove.get(0) == '\n') {
            textMove.remove(0);
        } else if (textMove.size() == 1 && textMove.get(0).equals('q')) {
            stopGame = true;
        }

        if(textMove.size() == 4) {
            Integer startRow = myCharList.indexOf(textMove.get(0)) + 1;
            Integer startCol = myNumList.indexOf(textMove.get(1)) + 1;

            textMove.remove(0);
            textMove.remove(0);
            Integer finishRow = myCharList.indexOf(textMove.get(0)) + 1;
            Integer finishCol = myNumList.indexOf(textMove.get(1)) + 1;
            textMove.remove(0);
            textMove.remove(0);
            chessTextMode.MoveTextPiece(chessBoard, startRow, startCol, finishRow, finishCol);
            System.out.println("\n");

        }

    }

    private void setMouseclick(int x, int y) throws IOException {

        chessBoard.MoveChessPiece(x, y);

    }

    private static void usage() {
        System.out.println("Usage:  java Main (test | chess | chess kibbitzer kibbitzer etc | run)");
        System.exit(1);
    }

    private Integer getNumOfKibbitzers(String[] args) {
        Integer counter = 0;
        for(Integer start = 1; start < args.length; start++) {
            if(args[start].equals("kibbitzer")) {
                counter++;
            }
        }

        return counter;
    }

    public void Draw() {
        String ss = "";
        for(int i = 0; i < numOfKibbitzers; i++) {
            ss = ss.concat("Kibbitzer: " + (i + 1) + "\n");
            ss = ss.concat(chessTextMode.printBoard());

        }
        System.out.println(ss);
    }

    void runMain(String[] args) throws IOException {
        if (args.length < 1) {
            usage();
        }

        Size windowSize = new Size(width, width);
        Size tileSize = new Size(pixelSize, pixelSize);
        SpriteWindow window = new SpriteWindow("Window", windowSize);

        if("run".equals(args[0])) {
            window.setTextMode();
            window.setKeyTypedHandler((c) -> setKeyClick(c));
        } else if("chess".equals(args[0])){
            window.setMouseClickedHandler((x, y) -> {
                try {
                    setMouseclick(x, y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            if(args.length > 1 && "kibbitzer".equals(args[1])) {
                numOfKibbitzers = getNumOfKibbitzers(args);
                isKibbitzer = true;
                System.out.println(numOfKibbitzers);
            }
        } else if ("test".equals(args[0])) {
            System.out.println("Run some test");
        }

        window.setTileSize(tileSize);
        chessBoard.InitBoard(window, tileSize);
        window.setFps(30);
        window.start();
        while (window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            if(newFrame == null) {
                window.stop();
            }

            if(chessBoard.madeMove) {
                chessBoard.updateBoard(newFrame);
                window.showNextFrame();
                chessBoard.madeMove = false;
                if(isKibbitzer) {
                    Draw();
                }
            } else if(chessBoard.initialMove) {
                chessBoard.updateBoard(newFrame);
                window.showNextFrame();
                chessBoard.initialMove = false;
                if(isKibbitzer) {
                    Draw();
                }
            } else if(stopGame) {
                window.stop();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.runMain(args);
    }
}
