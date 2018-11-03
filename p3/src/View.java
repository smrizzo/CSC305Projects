import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.ImageTile;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SolidColorTile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

abstract public class View implements Runnable, ViewObserver {
    protected ChessModel model;//model reference
    protected ChessController controller;
    protected Piece[][] pieces;
    protected ChessPieces chessImagesFiles = new ChessPieces();
    protected HashMap<String, File> imagefiles;
    protected static int width = 9;
    protected static int pixelSize = 100;
    boolean boardChanged = false;
    private Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
    private List<Character> myCharList = Arrays.asList(borderChars);
    private Character borderNumbers[] = new Character[]{'8', '7', '6', '5', '4', '3', '2', '1'};
    private ArrayList<Character> textMove = new ArrayList<>(4);
    private List<Character> myNumList = Arrays.asList(borderNumbers);

    public View(ChessModel model, ChessController controller) {
        this.model = model;
        this.controller = controller;
        this.pieces = model.getPieces();
        this.imagefiles = chessImagesFiles.getImageFiles();
    }

    abstract public void createView(AnimationFrame frame) throws IOException;
    abstract public void run();

    protected void commonView(AnimationFrame frame) throws IOException {
        for(int row = 1; row < 9; row++) {
            if(row == 1) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("A"), new Size(pixelSize, pixelSize), 'a'));
            } else if (row == 2) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("B"), new Size(pixelSize, pixelSize), 'b'));
            } else if(row == 3) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("C"), new Size(pixelSize, pixelSize), 'c'));
            } else if(row == 4) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("D"), new Size(pixelSize, pixelSize), 'd'));
            } else if(row == 5) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("E"), new Size(pixelSize, pixelSize), 'e'));
            } else if(row == 6) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("F"), new Size(pixelSize, pixelSize), 'f'));
            } else if(row == 7) {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("G"), new Size(pixelSize, pixelSize), 'g'));
            } else {
                frame.addTile(row, 0, new ImageTile(imagefiles.get("H"), new Size(pixelSize, pixelSize), 'h'));
            }
        }


        if(controller.pawnMadeIt) {
            for(int i = 1; i < 5; i++) {
                frame.addTile(i, 0, new SolidColorTile(Color.GREEN, 't'));
            }
        }

    }

    public void updateBoard(AnimationFrame frame) throws IOException {
        createView(frame);
    }

    @Override
    public void notifyBoardUpdate(ChessModel m) {
        this.model = m;
        this.pieces = model.getPieces();
        boardChanged = true;
    }

    public void setKeyClick(char c) throws CloneNotSupportedException, IOException {
        System.out.println("Got inside setKeyclick");
        if(controller.pawnMadeIt) {
            System.out.println("Pawn made it");
        }
        textMove.add(c);

        if(textMove.get(0) == '\n') {
            textMove.remove(0);
        } else if (textMove.size() == 1 && textMove.get(0).equals('q')) {
            controller.endingGame();
        }

        if(textMove.size() == 4) {

            textMove.remove(0);
            textMove.remove(0);
            Integer finishRow = myCharList.indexOf(textMove.get(0)) + 1;
            Integer finishCol = myNumList.indexOf(textMove.get(1)) + 1;
            if(controller.mBlackBoard) {
                controller.clickedPiece(finishRow, 9 - finishCol);
            } else {
                controller.clickedPiece(finishRow, finishCol);
            }

            textMove.remove(0);
            textMove.remove(0);
            System.out.println("\n");

        } else if (textMove.size() == 2) {
            Integer startRow = myCharList.indexOf(textMove.get(0)) + 1;
            Integer startCol = myNumList.indexOf(textMove.get(1)) + 1;
            if(controller.mBlackBoard) {
                controller.clickedPiece(startRow, 9 - startCol);
            } else{
                controller.clickedPiece(startRow, startCol);
            }

        }
    }
}
