import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;
import edu.calpoly.testy.Assert;
import edu.calpoly.testy.Testy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MyTest {


    ChessPieces chessPieces = new ChessPieces();
    HashMap<String, File> pieces = chessPieces.getImageFiles();



    public void testTileSize() {
        Size windowSize = new Size(8, 8);
        SpriteWindow window = new SpriteWindow("Window", windowSize);
        Size tileSize = new Size(100, 100);
        window.setTileSize(tileSize);
        Size sizeReturned = window.getTileSize();
        Assert.assertEquals(sizeReturned.height, 100);
        Assert.assertEquals(sizeReturned.width, 100);
    }

    public void testHasPiece() throws IOException {
        ChessModel model = new ChessModel();
        Piece[][] boardPieces = model.getPieces();
        Assert.assertEquals(boardPieces[1][1].getText(), 'r');


    }

    public void testCharText() throws IOException {
        ChessModel model = new ChessModel();
        Piece[][] boardPieces = model.getPieces();
        Assert.assertEquals(boardPieces[2][1].getText(), 'p');

    }


    public void runTests() {
        Testy.run(
                () -> {testHasPiece();},
                () -> {testTileSize();},
                () -> {testCharText();}
        );
    }

}
