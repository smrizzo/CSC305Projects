//import edu.calpoly.spritely.*;
//
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//
//public class ChessView implements ViewObserver , Runnable {
//
//    private ChessModel model;//model reference
//    private ChessController controller;
//    private boolean isKibbitzer;
//    private boolean mWhiteBoard;
//    private boolean mBlackBoard;
//    private Piece[][] pieces;
//    private ChessPieces chessImagesFiles = new ChessPieces();
//    private HashMap<String, File> imagefiles;
//    //private List<File> letters;
//    protected static int width = 9;
//    protected static int pixelSize = 100;
//    boolean boardChanged = false;
//    private Character borderChars[] = {'a', 'b', 'c', 'd','e','f','g','h'};
//    private List<Character> myCharList = Arrays.asList(borderChars);
//    private Character borderNumbers[] = new Character[]{'8', '7', '6', '5', '4', '3', '2', '1'};
//    private ArrayList<Character> textMove = new ArrayList<>(4);
//    private List<Character> myNumList = Arrays.asList(borderNumbers);
//
//    public ChessView(boolean isKibbitzer, ChessModel model, ChessController controller, boolean mWhiteBoard, boolean mBlackBoard) throws IOException {
//       this.isKibbitzer = isKibbitzer;
//       this.model = model;
//       this.controller = controller;
//       this.pieces = model.getPieces();
//       this.imagefiles = chessImagesFiles.getImageFiles();
//       //this.letters = chessImagesFiles.getLetters();
//       this.mWhiteBoard = mWhiteBoard;
//       this.mBlackBoard = mBlackBoard;
//       model.registerObserver(this);
//
//    }
//
//
//    private void createView(AnimationFrame frame) throws IOException {
//
//
//        for(int row = 1; row < 9; row++) {
//            if(row == 1) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("A"), new Size(pixelSize, pixelSize), 'a'));
//            } else if (row == 2) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("B"), new Size(pixelSize, pixelSize), 'b'));
//            } else if(row == 3) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("C"), new Size(pixelSize, pixelSize), 'c'));
//            } else if(row == 4) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("D"), new Size(pixelSize, pixelSize), 'd'));
//            } else if(row == 5) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("E"), new Size(pixelSize, pixelSize), 'e'));
//            } else if(row == 6) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("F"), new Size(pixelSize, pixelSize), 'f'));
//            } else if(row == 7) {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("G"), new Size(pixelSize, pixelSize), 'g'));
//            } else {
//                frame.addTile(row, 0, new ImageTile(imagefiles.get("H"), new Size(pixelSize, pixelSize), 'h'));
//            }
//        }
//
//        if(controller.pawnMadeIt) {
//            System.out.println("Pawn Made it");
//            for(int i = 1; i < 5; i++) {
//                frame.addTile(i, 0, new SolidColorTile(Color.GREEN, 't'));
//            }
//        }
//
//        //
//
//
//        if(mWhiteBoard || isKibbitzer) {
//            for(int row = 1; row < 9; row++) {
//                for(int col = 1; col < 9; col++ ) {
//                    if((row + col) % 2 == 0) {
//                        frame.addTile(row, col, new SolidColorTile(Color.GRAY, 't'));
//
//                    } else if ((row + col) % 2 != 0) {
//                        frame.addTile(row, col, new SolidColorTile(Color.BLUE, 't'));
//                    }
//                }
//            }
//
//            if(isKibbitzer) {
//                frame.addTile(0, 0, new ImageTile(imagefiles.get("kibbitzer"), new Size(pixelSize, pixelSize), '\0'));
//            }
//
//            for(int row = 0; row < 9; row++) {
//                for (int col = 0; col < 9; col++) {
//                    if (pieces[row][col] instanceof WhitePawnPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whitePawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackPawnPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackPawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof WhiteRookPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackRookPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof WhiteKnightPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackKnightPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof WhiteBishopPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackBishopPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof WhiteKingPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackKingPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof WhiteQueenPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("whiteQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (pieces[row][col] instanceof BlackQueenPiece) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("blackQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                    } else if (row == 0 && col == 1 ) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("8"), new Size(pixelSize, pixelSize), '8'));
//                    } else if (row == 0 && col == 2) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("7"), new Size(pixelSize, pixelSize), '7'));
//                    } else if (row == 0 && col == 3) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("6"), new Size(pixelSize, pixelSize), '6'));
//                    } else if (row == 0 && col == 4) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("5"), new Size(pixelSize, pixelSize), '5'));
//                    } else if (row == 0 && col == 5) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("4"), new Size(pixelSize, pixelSize), '4'));
//                    } else if (row == 0 && col == 6) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("3"), new Size(pixelSize, pixelSize), '3'));
//                    } else if (row == 0 && col == 7) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("2"), new Size(pixelSize, pixelSize), '2'));
//                    } else if (row == 0 && col == 8) {
//                        frame.addTile(row, col, new ImageTile(imagefiles.get("1"), new Size(pixelSize, pixelSize), '1'));
//                    }
//                }
//            }
//        } else if(mBlackBoard) {
//
//            for(int row = 1; row < 9; row++) {
//                for(int col = 1; col < 9; col++ ) {
//                    if((row + col) % 2 == 0) {
//                        frame.addTile(row, col, new SolidColorTile(Color.BLUE, 't'));
//
//                    } else if ((row + col) % 2 != 0) {
//                        frame.addTile(row, col, new SolidColorTile(Color.GRAY, 't'));
//                    }
//                }
//            }
//            for(int row = 0; row < 9; row++) {
//                for(int col = 0; col < 9; col++) {
//                    if (col > 0) {
//                        if (pieces[row][col] instanceof WhitePawnPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whitePawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackPawnPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackPawn"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteRookPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackRookPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteKnightPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackKnightPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteBishopPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackBishopPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteKingPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackKingPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteQueenPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("whiteQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackQueenPiece) {
//                            frame.addTile(row, 9 - col, new ImageTile(imagefiles.get("blackQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (row == 0 && col == 1 ) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("1"), new Size(pixelSize, pixelSize), '1'));
//                        } else if (row == 0 && col == 2) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("2"), new Size(pixelSize, pixelSize), '2'));
//                        } else if (row == 0 && col == 3) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("3"), new Size(pixelSize, pixelSize), '3'));
//                        } else if (row == 0 && col == 4) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("4"), new Size(pixelSize, pixelSize), '4'));
//                        } else if (row == 0 && col == 5) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("5"), new Size(pixelSize, pixelSize), '5'));
//                        } else if (row == 0 && col == 6) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("6"), new Size(pixelSize, pixelSize), '6'));
//                        } else if (row == 0 && col == 7) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("7"), new Size(pixelSize, pixelSize), '7'));
//                        } else if (row == 0 && col == 8) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("8"), new Size(pixelSize, pixelSize), '8'));
//                        }
//                    } else {
//                        if (pieces[row][col] instanceof WhiteRookPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("whiteRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackRookPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("blackRook"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteKnightPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackKnightPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("blackKnight"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteBishopPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("whiteBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackBishopPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("blackBishop"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteKingPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("whiteKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackKingPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("blackKing"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof WhiteQueenPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("whiteQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        } else if (pieces[row][col] instanceof BlackQueenPiece) {
//                            frame.addTile(row, col, new ImageTile(imagefiles.get("blackQueen"), new Size(pixelSize, pixelSize), pieces[row][col].getText()));
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//
//    public void updateBoard(AnimationFrame frame) throws IOException {
//        createView(frame);
//    }
//
//    public void setKeyClick(char c) throws CloneNotSupportedException, IOException {
//        //System.out.println("Got inside setKeyCLick");
//        textMove.add(c);
//
//        if(textMove.get(0) == '\n') {
//            textMove.remove(0);
//        } else if (textMove.size() == 1 && textMove.get(0).equals('q')) {
//            //stopGame = true;
//            System.out.println("we are ending game");
//        }
//
//        if(textMove.size() == 4) {
//            Integer startRow = myCharList.indexOf(textMove.get(0)) + 1;
//            Integer startCol = myNumList.indexOf(textMove.get(1)) + 1;
//            //model.setFromXY(startRow, startCol);
//            controller.clickedPiece(startRow, startCol);
//            textMove.remove(0);
//            textMove.remove(0);
//            Integer finishRow = myCharList.indexOf(textMove.get(0)) + 1;
//            Integer finishCol = myNumList.indexOf(textMove.get(1)) + 1;
//            //model.setToXY(finishRow, finishCol);
//            controller.clickedPiece(finishRow, finishCol);
//            textMove.remove(0);
//            textMove.remove(0);
//            //chessTextMode.MoveTextPiece(chessBoard, startRow, startCol, finishRow, finishCol);
//            System.out.println("\n");
//
//        }
//    }
//
//    @Override
//    public void notifyBoardUpdate(ChessModel m) {
//        System.out.println("Notifying observer of change");
//        this.model = m;
//        this.pieces = model.getPieces();
//        boardChanged = true;
//    }
//
//    public void run() {
//        Size windowSize = new Size(width, width);
//        Size tileSize = new Size(pixelSize, pixelSize);
//        SpriteWindow window = new SpriteWindow("Window", windowSize);
//
//
//        if(!isKibbitzer) {
//            window.setKeyTypedHandler((c) -> {
//                try {
//                    setKeyClick(c);
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//            window.setMouseClickedHandler((x, y) -> {
//                try {
//                    if(mWhiteBoard) {
//                        System.out.println("Before click on white board");
//                        controller.clickedPiece(x, y);
//                        System.out.println("After click on white board");
//                    } else if(mBlackBoard) {
//                        if(y == 0) {
//                            System.out.println("y == 0 before clicked");
//                            controller.clickedPiece(x, y);
//                            System.out.println("After click in y == 0");
//                        } else {
//                            System.out.println("before click on black board");
//                            controller.clickedPiece(x, 9 - y);
//                            System.out.println("After click on black board");
//                        }
//
//                    }
//
//                } catch (CloneNotSupportedException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        window.setTileSize(tileSize);
//        AnimationFrame frame = window.getInitialFrame();
//        try {
//            createView(frame);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        window.setFps(20);
//        window.start();
//
//        while (window.isRunning()) {
//            AnimationFrame newFrame = window.waitForNextFrame();
//            if(newFrame == null) {
//                window.stop();
//            }
//
//            if(boardChanged) {
//                System.out.println("Board changed so re-render board");
//                try {
//                    updateBoard(newFrame);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                window.showNextFrame();
//                boardChanged = false;
//            }
//
//        }
//
//    }
//}
