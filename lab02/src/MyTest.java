import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;
import edu.calpoly.testy.Testy;
import edu.calpoly.testy.Assert;

public class MyTest {

    public void test1() {
        Size windowSize = new Size(8, 8);
        SpriteWindow window = new SpriteWindow("Window", windowSize);
        Size tileSize = new Size(100, 100);
        window.setTileSize(tileSize);
        Size sizeReturned = window.getTileSize();
        Assert.assertEquals(sizeReturned.height, 100);
        Assert.assertEquals(sizeReturned.width, 100);
    }

    public void test2() {
        Size windowSize = new Size(8, 8);
        Size tileSize2 = new Size(80, 80);
        SpriteWindow window2 = new SpriteWindow("Window", windowSize);
        window2.setTileSize(tileSize2);
        Size sizeReturned2 = window2.getTileSize();
        Assert.assertEquals(sizeReturned2.height, 80);
        Assert.assertEquals(sizeReturned2.width, 80);
    }

    public void runMain() {

        final MyTest testObj = new MyTest();
        Testy.run(
                () -> testObj.test1(),
                () -> testObj.test2()
        );
    }

    public static void main(String[] args) {
        MyTest m = new MyTest();
        m.runMain();
    }

}
