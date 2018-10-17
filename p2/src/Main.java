import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    static int width = 9;
    static int pixelSize = 100;
    private ChessImageTile[][] Cells = new ChessImageTile[width][width];
    public boolean pieceClicked = false;
    public boolean madeMove = false;
    public boolean initialMove = false;
    public boolean pawnMadeIt = false;
    public boolean stopGame = false;
    public Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
    List<Character> myCharList = Arrays.asList(borderChars);
    public Character borderNumbers[] = new Character[]{'8', '7', '6', '5', '4', '3', '2', '1'};
    public ArrayList<Character> textMove = new ArrayList<>(4);
    List<Character> myNumList = Arrays.asList(borderNumbers);
    //Character textMove [] = new Character[4];

    public int firstClickx;
    public int firstClicky;
    File blackPawn = new File("./images/black_pawn.png");
    File blackRook = new File("./images/black_rook.png");
    File blackKnight = new File("./images/black_knight.png");
    File blackBishop = new File("./images/black_bishop.png");
    File blackKing = new File("./images/black_king.png");
    File blackQueen = new File("./images/black_queen.png");
    File whitePawn = new File("./images/white_pawn.png");
    File whiteKnight = new File("./images/white_knight.png");
    File whiteBishop = new File("./images/white_bishop.png");
    File whiteKing = new File("./images/white_king.png");
    File whiteQueen = new File("./images/white_queen.png");
    File whiteRook = new File("./images/white_rook.png");


    public void drawBoard(SpriteWindow window, Size size) throws IOException {
        AnimationFrame frame = window.getInitialFrame();

        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {

                if((x + y) % 2 == 0 && x != 0 && y != 0) {

                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(whitePawn, size, 'P', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(blackPawn, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackRook, size, 'r', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteRook, size, 'R', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 7 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKnight, size, 'n', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 2 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKnight, size, 'N', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 3 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackBishop, size, 'b', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 6 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteBishop, size, 'B', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteQueen, size, 'Q', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKing, size, 'k', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(blackPawn, size, '.', Color.GRAY, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }


                } else if((x + y) % 2 != 0 && x != 0 && y != 0 ) {

                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(whitePawn, size, 'P', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(blackPawn, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackRook, size, 'r', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteRook, size, 'R', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if (x == 2 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKnight, size, 'n', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 7 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKnight, size, 'N', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if (x == 6 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackBishop, size, 'b', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 3 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteBishop, size, 'B', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKing, size, 'K', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackQueen, size, 'q', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(blackPawn, size, '.', Color.BLUE, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }

                }

            }
        }
        for(Integer x = 1; x < 9; x++) {
            Cells[x][0] = new ChessImageTile(blackPawn, size , borderChars[x-1], Color.BLACK, false);
            Cells[x][0].setBorder(borderChars[x - 1].toString());
            frame.addTile(x, 0, Cells[x][0]);

        }
        Integer counter = 8;
        for(Integer y = 1; y < 9; y++, counter--) {
            String stringCounter = counter.toString();
            Cells[0][y] = new ChessImageTile(blackPawn, size ,stringCounter.charAt(0) , Color.BLACK, false);
            Cells[0][y].setBorder(counter.toString());
            frame.addTile(0, y, Cells[0][y]);
        }
    }

    public void updateBoard(AnimationFrame frame) {

        for(int x = 1; x < 9; x++) {
            for(int y = 1; y < 9; y++) {
                frame.addTile(x, y, Cells[x][y]);
            }
        }
        for(Integer x = 1; x < 9; x++) {
            frame.addTile(x, 0, Cells[x][0]);
        }
        for(Integer y = 1; y < 9; y++) {
            frame.addTile(0, y, Cells[0][y]);
        }

    }


//    private static void usage() {
//        System.out.println("Usage:  java Main (test | chess | chess kibbitzer kibbitzer etc | run)");
//        System.exit(1);
//    }
//
//    void runMain(String[] args) throws IOException {
//        if (args.length < 1) {
//            usage();
//        }
//
//        Size windowSize = new Size(width, width);
//        Size tileSize = new Size(pixelSize, pixelSize);
//        SpriteWindow window = new SpriteWindow("Window", windowSize);
//
//        if("run".equals(args[0])) {
//            window.setTextMode();
//            window.setKeyTypedHandler((c) -> setKeyClick(c));
//        } else if("chess".equals(args[0])){
//            window.setMouseClickedHandler((x, y) -> {
//                try {
//                    setMouseclick(x, y);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            if(args.length > 1 && "kibbitzer".equals(args[1])) {
//                System.out.println("Print out board when something changes");
//            }
//        }
//
//        window.setTileSize(tileSize);
//        drawBoard(window, tileSize);
//        window.setFps(30);
//        window.start();
//        while (window.isRunning()) {
//            AnimationFrame newFrame = window.waitForNextFrame();
//            if(newFrame == null) {
//                window.stop();
//            }
//
//            if(madeMove) {
//                //System.out.println("\n");
//                updateBoard(newFrame);
//                window.showNextFrame();
//                madeMove = false;
//                //System.out.println("\n");
//            } else if(initialMove) {
//                updateBoard(newFrame);
//                window.showNextFrame();
//                initialMove = false;
//            } else if(stopGame) {
//                window.stop();
//            }
//        }
//    }

    public void moveTextPiece(Integer startRow, Integer startCol, Integer finishRow, Integer finishCol) {

        if(Cells[startRow][startCol].hasPiece && !Cells[finishRow][finishCol].hasPiece && finishRow != 0 && finishCol != 0) {
            ChessImageTile finish = Cells[finishRow][finishCol];
            ChessImageTile start = Cells[startRow][startCol];

            Cells[finishRow][finishCol] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                    finish.getTileColor(), true, false);
            Cells[startRow][startCol] = new ChessImageTile((BufferedImage) null, start.getTileSize(),
                    '.', start.getTileColor(), false, false);

            madeMove = true;

        }
    }
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
            moveTextPiece(startRow, startCol, finishRow, finishCol);


        }

    }

    private void setMouseclick(int x, int y) throws IOException {
        if(Cells[x][y].hasPiece && !pieceClicked && !initialMove) {
            if((Cells[x][y].getText() == 'P' && y == 1)) {
                ChessImageTile temp = Cells[x][y];
                Cells[1][0] = new ChessImageTile(whiteQueen, Cells[x][y].getTileSize(), 'Q', Color.GREEN, true);
                Cells[2][0] = new ChessImageTile(whiteRook, Cells[x][y].getTileSize(), 'R', Color.GREEN, true);
                Cells[3][0] = new ChessImageTile(whiteBishop, Cells[x][y].getTileSize(), 'B', Color.GREEN, true);
                Cells[4][0] = new ChessImageTile(whiteKnight, Cells[x][y].getTileSize(), 'N', Color.GREEN, true);
                Cells[x][y] = new ChessImageTile(temp.getTileImage(), temp.getTileSize(), temp.getText(), temp.getTileColor(), true, true);
                pawnMadeIt = true;
                pieceClicked = true;
                initialMove = true;
                this.firstClickx = x;
                this.firstClicky = y;
            } else if (Cells[x][y].getText() == 'p' && y == 8) {
                ChessImageTile temp = Cells[x][y];
                Cells[1][0] = new ChessImageTile(blackQueen, Cells[x][y].getTileSize(), 'q', Color.GREEN, true);
                Cells[2][0] = new ChessImageTile(blackRook, Cells[x][y].getTileSize(), 'r', Color.GREEN, true);
                Cells[3][0] = new ChessImageTile(blackBishop, Cells[x][y].getTileSize(), 'b', Color.GREEN, true);
                Cells[4][0] = new ChessImageTile(blackKnight, Cells[x][y].getTileSize(), 'n', Color.GREEN, true);
                Cells[x][y] = new ChessImageTile(temp.getTileImage(), temp.getTileSize(), temp.getText(), temp.getTileColor(), true, true);
                pawnMadeIt = true;
                pieceClicked = true;
                initialMove = true;
                this.firstClickx = x;
                this.firstClicky = y;
            } else {
                ChessImageTile start = Cells[x][y];
                Cells[x][y] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                        start.getTileColor(), true, true);
                pieceClicked = true;
                initialMove = true;
                this.firstClickx = x;
                this.firstClicky = y;
            }
        } else if(pieceClicked && !Cells[x][y].hasPiece && x != 0 && y != 0 && !pawnMadeIt) {
            ChessImageTile finish = Cells[x][y];
            ChessImageTile start = Cells[this.firstClickx][this.firstClicky];

            Cells[x][y] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                    finish.getTileColor(), true, false);
            Cells[this.firstClickx][this.firstClicky] = new ChessImageTile((BufferedImage) null, start.getTileSize(),
                    '.', start.getTileColor(), false, false);

            pieceClicked = false;
            madeMove = true;
        } else if(pieceClicked && Cells[x][y].hasPiece && !pawnMadeIt) {
            if(Character.isLowerCase(Cells[this.firstClickx][this.firstClicky].getText()) && Character.isUpperCase(Cells[x][y].getText()) ||
                    (Character.isUpperCase(Cells[this.firstClickx][this.firstClicky].getText()) && Character.isLowerCase(Cells[x][y].getText()))) {

                ChessImageTile finish = Cells[x][y];
                ChessImageTile start = Cells[this.firstClickx][this.firstClicky];
                Cells[x][y] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                        finish.getTileColor(), true, false);
                Cells[this.firstClickx][this.firstClicky] = new ChessImageTile((BufferedImage) null,
                        start.getTileSize(), '.', start.getTileColor(), false, false);
                pieceClicked = false;
                madeMove = true;
            }

        } else if (pieceClicked && (x == this.firstClickx && y == this.firstClicky) && !pawnMadeIt) {
            pieceClicked = true;
            initialMove = true;

        } else if (pieceClicked && pawnMadeIt && y == 0 && (x >= 1 && x <= 4)) {
            ChessImageTile start = Cells[this.firstClickx][this.firstClicky];
            ChessImageTile finish = Cells[x][y];

            Cells[this.firstClickx][this.firstClicky] = new ChessImageTile(finish.getTileImage(), finish.getTileSize(),
                    finish.getText(), start.getTileColor(), true, false);

            for(Integer z = 1; z < 5; z++) {
                Cells[z][0] = new ChessImageTile(start.getTileImage(), finish.getTileSize() , borderChars[z-1], Color.BLACK, false, false);
                Cells[z][0].setBorder(borderChars[z - 1].toString());
            }
            pawnMadeIt = false;
            pieceClicked = false;
            madeMove = true;
        } else {
            pieceClicked = false;
        }

    }

    private static void usage() {
        System.out.println("Usage:  java Main (test | chess | chess kibbitzer kibbitzer etc | run)");
        System.exit(1);
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
                System.out.println("Print out board when something changes");
            }
        } else if ("test".equals(args[0])) {
            System.out.println("Run some test");
        }

        window.setTileSize(tileSize);
        drawBoard(window, tileSize);
        window.setFps(30);
        window.start();
        while (window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            if(newFrame == null) {
                window.stop();
            }

            if(madeMove) {
                updateBoard(newFrame);
                window.showNextFrame();
                madeMove = false;
            } else if(initialMove) {
                updateBoard(newFrame);
                window.showNextFrame();
                initialMove = false;
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
