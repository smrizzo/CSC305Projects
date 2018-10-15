import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    static int width = 9;
    static int pixelSize = 100;
    //private SolidColorTile[][] Cells = new SolidColorTile[width][width];
    private ChessImageTile[][] Cells = new ChessImageTile[width][width];
    public boolean wasClicked = false;
    public Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};

    public void drawBoard(SpriteWindow window, File image, Size size) throws IOException {
        AnimationFrame frame = window.getInitialFrame();
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
        for(int x = 0; x < 9; x++) {
            for(int y = 0; y < 9; y++) {

                if((x + y) % 2 == 0 && x != 0 && y != 0) {
                    //Cells[x][y] = new SolidColorTile(Color.GRAY, 'w');
                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(whitePawn, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(image, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackRook, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteRook, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 7 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKnight, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 2 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKnight, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 3 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackBishop, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 6 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteBishop, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteQueen, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKing, size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(image, size, 'p', Color.GRAY, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }



                } else if((x + y) % 2 != 0 && x != 0 && y != 0 ) {
                    //Cells[x][y] = new SolidColorTile(Color.BLUE, 'b');
                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(whitePawn, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(image, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackRook, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteRook, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if (x == 2 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackKnight, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 7 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKnight, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if (x == 6 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackBishop, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 3 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteBishop, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 8) {
                        Cells[x][y] = new ChessImageTile(whiteKing, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 1) {
                        Cells[x][y] = new ChessImageTile(blackQueen, size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(image, size, 'p', Color.BLUE, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }

                }

            }
        }
        for(Integer x = 1; x < 9; x++) {
            Cells[x][0] = new ChessImageTile(image, size , 'p', Color.BLACK, false);
            Cells[x][0].setBorder(borderChars[x - 1].toString());
            frame.addTile(x, 0, Cells[x][0]);

        }
        Integer counter = 8;
        for(Integer y = 1; y < 9; y++, counter--) {
            Cells[0][y] = new ChessImageTile(image, size , 'p', Color.BLACK, false);
            Cells[0][y].setBorder(counter.toString());
            frame.addTile(0, y, Cells[0][y]);
        }
    }


    void runMain() throws IOException {
        System.out.println("Do stuff");
        Size windowSize = new Size(width, width);
        Size tileSize = new Size(pixelSize, pixelSize);
        File image = new File("./images/black_pawn.png");
        //ImageTile images = new ImageTile(image, tileSize, 'p');


        SpriteWindow window = new SpriteWindow("Window", windowSize);

        window.setTileSize(tileSize);
        drawBoard(window, image, tileSize);


        window.start();
        while (window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            if(newFrame == null) {
                window.stop();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.runMain();
    }
}
