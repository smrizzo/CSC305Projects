import edu.calpoly.spritely.AnimationFrame;
import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.SpriteWindow;
import edu.calpoly.testy.Testy;

import java.awt.*;


public class Main {
    static int width = 8;
    static int pixelSize = 100;
    private SolidColorCircle[][] Cells = new SolidColorCircle[width][width];
    public boolean wasClicked = false;

    public void drawBoard(SpriteWindow window) {
        AnimationFrame frame = window.getInitialFrame();
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {

                if((x + y) % 2 == 0) {
                    Cells[x][y] = new SolidColorCircle(Color.WHITE, 'w', false);
                    frame.addTile(x, y, Cells[x][y]);

                } else {
                    Cells[x][y] = new SolidColorCircle(Color.BLACK, 'b', false);
                    frame.addTile(x, y, Cells[x][y]);
                }
            }
        }
    }

    public void updateBoard(AnimationFrame frame) {

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                frame.addTile(x, y, Cells[x][y]);
            }
        }
        wasClicked = false;
    }

    public void setTileClicked(int x, int y) {

        if(Cells[x][y].getText() == 'w' || Cells[x][y].getText() == 'W') {
            Cells[x][y] = new SolidColorCircle(Color.WHITE, 'W', true);
        } else {
            Cells[x][y] = new SolidColorCircle(Color.BLACK, 'B', true);
        }
        wasClicked = true;

    }


    public void runMain() {
        Size windowSize = new Size(width, width);
        Size tileSize = new Size(pixelSize, pixelSize);

        SpriteWindow window = new SpriteWindow("Window", windowSize);

        window.setTileSize(tileSize);
        drawBoard(window);


        window.setMouseClickedHandler((x, y) -> setTileClicked(x, y));


        window.start();
        while (window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            if(newFrame == null) {
                window.stop();
            }

            if(wasClicked) {
                updateBoard(newFrame);
                window.showNextFrame();
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(args[0]);
        if(args[0].equals("test")) {
            MyTest testObj = new MyTest();
            Testy.run(
                    () -> testObj.test1(),
                    () -> testObj.test2()
            );
        } else {
            Main m = new Main();
            m.runMain();
        }



//        Main m = new Main();
//        m.runMain();
    }
}
