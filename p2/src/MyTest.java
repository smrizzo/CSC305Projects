import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;
import edu.calpoly.testy.Assert;
import edu.calpoly.testy.Testy;

import java.io.File;
import java.util.HashMap;

public class MyTest {
    ChessPieces chessPieces = new ChessPieces();
    HashMap<String, File> pieces = chessPieces.getPieces();


    public void testTileSize() {
        Size windowSize = new Size(8, 8);
        SpriteWindow window = new SpriteWindow("Window", windowSize);
        Size tileSize = new Size(100, 100);
        window.setTileSize(tileSize);
        Size sizeReturned = window.getTileSize();
        Assert.assertEquals(sizeReturned.height, 100);
        Assert.assertEquals(sizeReturned.width, 100);
    }



    public void runTests() {
        Testy.run(
                () -> {testTileSize();}
        );
    }
}
