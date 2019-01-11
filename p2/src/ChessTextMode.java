import java.awt.image.BufferedImage;

public class ChessTextMode extends ChessBoard {

    public void MoveTextPiece(ChessBoard chessBoard, Integer startRow, Integer startCol, Integer finishRow, Integer finishCol) {
        if(Cells[startRow][startCol].hasPiece && !Cells[finishRow][finishCol].hasPiece && finishRow != 0 && finishCol != 0) {
            ChessImageTile finish = Cells[finishRow][finishCol];
            ChessImageTile start = Cells[startRow][startCol];

            Cells[finishRow][finishCol] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                    finish.getTileColor(), true, false);
            Cells[startRow][startCol] = new ChessImageTile((BufferedImage) null, start.getTileSize(),
                    '.', start.getTileColor(), false, false);

            chessBoard.madeMove = true;

        } else if(Cells[startRow][startCol].hasPiece && Cells[finishRow][finishCol].hasPiece) {
            if(Character.isLowerCase(Cells[startRow][startCol].getText()) && Character.isUpperCase(Cells[finishRow][finishCol].getText()) ||
                    (Character.isUpperCase(Cells[startRow][startCol].getText()) && Character.isLowerCase(Cells[finishRow][finishCol].getText()))) {

                ChessImageTile finish = Cells[finishRow][finishCol];
                ChessImageTile start = Cells[startRow][startCol];
                Cells[finishRow][finishCol] = new ChessImageTile(start.getTileImage(), start.getTileSize(), start.getText(),
                        finish.getTileColor(), true, false);
                Cells[startRow][startCol] = new ChessImageTile((BufferedImage) null,
                        start.getTileSize(), '.', start.getTileColor(), false, false);
                chessBoard.madeMove = true;
            }

        }

    }

    public String printBoard() {
        String ss = "";
        System.out.print("\n");
        for(int col = 0; col < 9; col++) {
            for(int row = 0; row < 9; row++) {
                ss = ss.concat(Cells[row][col].getText() + " ");
            }
            ss = ss.concat("\n");
        }
        ss = ss.concat("\n");
        return ss;


    }



}
