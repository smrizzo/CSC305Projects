//public class MyTest {
//    ChessPieces chessPieces = new ChessPieces();
//    HashMap<String, File> pieces = chessPieces.getImageFiles();
//    static ChessImageTile[][] Cells = new ChessImageTile[9][9];
//
//
//    public void testTileSize() {
//        Size windowSize = new Size(8, 8);
//        SpriteWindow window = new SpriteWindow("Window", windowSize);
//        Size tileSize = new Size(100, 100);
//        window.setTileSize(tileSize);
//        Size sizeReturned = window.getTileSize();
//        Assert.assertEquals(sizeReturned.height, 100);
//        Assert.assertEquals(sizeReturned.width, 100);
//    }
//
//    public void testHasPiece() throws IOException {
//        Size windowSize = new Size(8, 8);
//        SpriteWindow window = new SpriteWindow("Window", windowSize);
//        Size tileSize = new Size(100, 100);
//        window.setTileSize(tileSize);
//        Cells[1][1] = new ChessImageTile(pieces.get("whitePawn"), tileSize, 'P', Color.GRAY, true);
//        Assert.assertEquals(Cells[1][1].hasPiece, true);
//
//    }
//
//    public void testCharText() throws IOException {
//        Size windowSize = new Size(8, 8);
//        SpriteWindow window = new SpriteWindow("Window", windowSize);
//        Size tileSize = new Size(100, 100);
//        window.setTileSize(tileSize);
//        Cells[1][1] = new ChessImageTile(pieces.get("whitePawn"), tileSize, 'P', Color.GRAY, true);
//        Assert.assertEquals(Cells[1][1].getText(), 'P');
//    }
//
//
//
//
//    public void runTests() {
//        Testy.run(
//                () -> {testHasPiece();},
//                () -> {testTileSize();},
//                () -> {testCharText();}
//        );
//    }
//}
