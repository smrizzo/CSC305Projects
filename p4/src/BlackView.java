import edu.calpoly.spritely.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackView extends View {

    private Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
    private java.util.List<Character> myCharList = Arrays.asList(borderChars);
    private Character borderNumbers[] = new Character[]{'8', '7', '6', '5', '4', '3', '2', '1'};
    private ArrayList<Character> textMove = new ArrayList<>(4);
    private List<Character> myNumList = Arrays.asList(borderNumbers);

    public BlackView(ChessModel model, ChessController controller) {
        super(model, controller);
        model.registerObserver(this);
    }

    @Override
    public void createView(AnimationFrame frame) throws IOException {
        commonView(frame);
        for(int row = 1; row < 9; row++) {
            for(int col = 1; col < 9; col++ ) {
                if((row + col) % 2 == 0) {
                    frame.addTile(row, col, new SolidColorTile(Color.BLUE, 't'));

                } else if ((row + col) % 2 != 0) {
                    frame.addTile(row, col, new SolidColorTile(Color.GRAY, 't'));
                }
            }
        }

        if(controller.initialClick) {
            frame.addTile(controller.firstClickX, 9 - controller.firstClicky, new SolidColorTile(Color.ORANGE, 'o'));
        }

        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if (col > 0) {
                    if (pieces[row][col] instanceof WhitePawnPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whitePawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackPawnPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackPawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteRookPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackRookPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteKnightPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackKnightPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteBishopPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackBishopPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteKingPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackKingPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteQueenPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackQueenPiece) {
                        frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (row == 0 && col == 1 ) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("1"), new Size(pixelSize, pixelSize), '1'));
                    } else if (row == 0 && col == 2) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("2"), new Size(pixelSize, pixelSize), '2'));
                    } else if (row == 0 && col == 3) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("3"), new Size(pixelSize, pixelSize), '3'));
                    } else if (row == 0 && col == 4) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("4"), new Size(pixelSize, pixelSize), '4'));
                    } else if (row == 0 && col == 5) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("5"), new Size(pixelSize, pixelSize), '5'));
                    } else if (row == 0 && col == 6) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("6"), new Size(pixelSize, pixelSize), '6'));
                    } else if (row == 0 && col == 7) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("7"), new Size(pixelSize, pixelSize), '7'));
                    } else if (row == 0 && col == 8) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("8"), new Size(pixelSize, pixelSize), '8'));
                    }
                } else {
                    //This is for promoting of the pawn
                    if (pieces[row][col] instanceof WhiteRookPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackRookPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteKnightPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackKnightPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteBishopPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackBishopPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteKingPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackKingPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof WhiteQueenPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    } else if (pieces[row][col] instanceof BlackQueenPiece) {
                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
                    }
                }

            }
        }
    }

    public void setKeyClick(char c) throws CloneNotSupportedException, IOException {
        textMove.add(c);

        if(textMove.get(0) == '\n') {
            textMove.remove(0);
        } else if (textMove.size() == 1 && textMove.get(0).equals('q')) {
            controller.endingGame();
            //System.exit(0);
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

    @Override
    public void run() {
        Size windowSize = new Size(width, width);
        Size tileSize = new Size(pixelSize, pixelSize);
        SpriteWindow window = new SpriteWindow("Black Player Window", windowSize);

        window.setKeyTypedHandler((c) -> {
            try {
                setKeyClick(c);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        window.setMouseClickedHandler((x, y) -> {
            try {
                if(y == 0) {
                    controller.clickedPiece(x, y);
                } else {
                    controller.clickedPiece(x, 9 - y);
                }
            } catch (CloneNotSupportedException | IOException e) {
                e.printStackTrace();
            }
        });


        window.setTileSize(tileSize);
        AnimationFrame frame = window.getInitialFrame();
        try {
            createView(frame);
        } catch (IOException e) {
            e.printStackTrace();
        }

        window.setFps(20);
        window.start();

        while (window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            if(newFrame == null) {
                window.stop();
            }

            if(boardChanged) {
                try {
                    updateBoard(newFrame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                window.showNextFrame();
                boardChanged = false;
            } else if(controller.initialClick) {
                try {
                    updateBoard(newFrame);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                window.showNextFrame();
            } else if(controller.endingGame) {
                window.stop();

            }

        }

    }
}
