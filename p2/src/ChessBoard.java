import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ChessBoard {
    static int width = 9;
    static ChessImageTile[][] Cells = new ChessImageTile[width][width];
    private boolean pieceClicked = false;
    protected boolean madeMove = false;
    protected boolean initialMove = false;
    public boolean pawnMadeIt = false;
    public boolean stopGame = false;
    public Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
    private ChessPieces chessPieces = new ChessPieces();
    private HashMap<String, File> pieces = chessPieces.getPieces();


    public int firstClickx;
    public int firstClicky;


    public void InitBoard(SpriteWindow window, Size size) throws IOException {
        AnimationFrame frame = window.getInitialFrame();

        for(int x = 1; x < 9; x++) {
            for(int y = 1; y < 9; y++) {
                if((x + y) % 2 == 0) {

                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whitePawn"), size, 'P', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackPawn"), size, 'p', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackRook"), size, 'r', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteRook"), size, 'R', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 7 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackKnight"), size, 'n', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 2 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteKnight"), size, 'N', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 3 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackBishop"), size, 'b', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 6 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteBishop"), size, 'B', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteQueen"), size, 'Q', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackKing"), size, 'k', Color.GRAY, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackPawn"), size, '.', Color.GRAY, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }


                } else if((x + y) % 2 != 0) {

                    if(y == 7) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whitePawn"), size, 'P', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(y == 2) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackPawn"), size, 'p', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 8 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackRook"), size, 'r', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 1 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteRook"), size, 'R', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if (x == 2 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackKnight"), size, 'n', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 7 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteKnight"), size, 'N', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if (x == 6 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackBishop"), size, 'b', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else if(x == 3 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteBishop"), size, 'B', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 5 && y == 8) {
                        Cells[x][y] = new ChessImageTile(pieces.get("whiteKing"), size, 'K', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    } else if(x == 4 && y == 1) {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackQueen"), size, 'q', Color.BLUE, true);
                        frame.addTile(x, y, Cells[x][y]);
                    }
                    else {
                        Cells[x][y] = new ChessImageTile(pieces.get("blackPawn"), size, '.', Color.BLUE, false);
                        frame.addTile(x, y, Cells[x][y]);
                    }

                }

            }
        }
        for(Integer x = 1; x < 9; x++) {
            Cells[x][0] = new ChessImageTile(pieces.get("blackPawn"), size , borderChars[x-1], Color.BLACK, false);
            Cells[x][0].setBorder(borderChars[x - 1].toString());
            frame.addTile(x, 0, Cells[x][0]);

        }
        Integer counter = 8;
        for(Integer y = 1; y < 9; y++, counter--) {
            String stringCounter = counter.toString();
            Cells[0][y] = new ChessImageTile(pieces.get("blackPawn"), size ,stringCounter.charAt(0) , Color.BLACK, false);
            Cells[0][y].setBorder(counter.toString());
            frame.addTile(0, y, Cells[0][y]);
        }
        Cells[0][0] = new ChessImageTile(pieces.get("blackPawn"), size ,' ' , Color.BLACK, false);
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

    public void InitialMove(int x, int y) throws IOException {
        if((Cells[x][y].getText() == 'P' && y == 1)) {
            ChessImageTile temp = Cells[x][y];
            Cells[1][0] = new ChessImageTile(pieces.get("whiteQueen"), Cells[x][y].getTileSize(), 'Q', Color.GREEN, true);
            Cells[2][0] = new ChessImageTile(pieces.get("whiteRook"), Cells[x][y].getTileSize(), 'R', Color.GREEN, true);
            Cells[3][0] = new ChessImageTile(pieces.get("whiteBishop"), Cells[x][y].getTileSize(), 'B', Color.GREEN, true);
            Cells[4][0] = new ChessImageTile(pieces.get("whiteKnight"), Cells[x][y].getTileSize(), 'N', Color.GREEN, true);
            Cells[x][y] = new ChessImageTile(temp.getTileImage(), temp.getTileSize(), temp.getText(), temp.getTileColor(), true, true);
            pawnMadeIt = true;

        } else if (Cells[x][y].getText() == 'p' && y == 8) {
            ChessImageTile temp = Cells[x][y];
            Cells[1][0] = new ChessImageTile(pieces.get("blackQueen"), Cells[x][y].getTileSize(), 'q', Color.GREEN, true);
            Cells[2][0] = new ChessImageTile(pieces.get("blackRook"), Cells[x][y].getTileSize(), 'r', Color.GREEN, true);
            Cells[3][0] = new ChessImageTile(pieces.get("blackBishop"), Cells[x][y].getTileSize(), 'b', Color.GREEN, true);
            Cells[4][0] = new ChessImageTile(pieces.get("blackKnight"), Cells[x][y].getTileSize(), 'n', Color.GREEN, true);
            Cells[x][y] = new ChessImageTile(temp.getTileImage(), temp.getTileSize(), temp.getText(), temp.getTileColor(), true, true);
            pawnMadeIt = true;

        } else {
            ChessImageTile start = Cells[x][y];
            Cells[x][y] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                    start.getTileColor(), true, true);

        }
        pieceClicked = true;
        initialMove = true;
        this.firstClickx = x;
        this.firstClicky = y;
    }

    public void moveToEmptyTile(int x, int y) {
        ChessImageTile finish = Cells[x][y];
        ChessImageTile start = Cells[this.firstClickx][this.firstClicky];

        Cells[x][y] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                finish.getTileColor(), true, false);
        Cells[this.firstClickx][this.firstClicky] = new ChessImageTile((BufferedImage) null, start.getTileSize(),
                '.', start.getTileColor(), false, false);

        pieceClicked = false;
        madeMove = true;
    }

    public void takePiece(int x, int y) {
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
    }

    public void promotePawn(int x, int y) {
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
    }

    public void MoveChessPiece(int x, int y) throws IOException {
        if(Cells[x][y].hasPiece && !pieceClicked && !initialMove) {
            InitialMove(x, y);

        } else if(pieceClicked && !Cells[x][y].hasPiece && x != 0 && y != 0 && !pawnMadeIt) {
            moveToEmptyTile(x, y);

        } else if(pieceClicked && Cells[x][y].hasPiece && !pawnMadeIt) {
            takePiece(x, y);

        } else if (pieceClicked && (x == this.firstClickx && y == this.firstClicky) && !pawnMadeIt) {
            pieceClicked = true;
            initialMove = true;

        } else if (pieceClicked && pawnMadeIt && y == 0 && (x >= 1 && x <= 4)) {
            promotePawn(x, y);
        } else {
            pieceClicked = false;
        }
    }



}
