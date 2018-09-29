import edu.calpoly.spritely.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Size windowSize = new Size(8, 8);
        Size tileSize = new Size(100, 100);
        SolidColorTile blackTile = new SolidColorTile(Color.BLACK, 'b');
        SolidColorTile whiteTile = new SolidColorTile(Color.WHITE, 'w');


        SpriteWindow window = new SpriteWindow("Window", windowSize);
        window.setTileSize(tileSize);
        AnimationFrame frame = window.getInitialFrame();



        System.out.println("Hello World");
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if((x + y) % 2 == 0) {
                    frame.addTile(x, y, whiteTile);
                } else {
                    frame.addTile(x, y, blackTile);
                }
                //window.showNextFrame();

            }
        }
        MouseClickedHandler handler = (x, y) -> System.out.println("cliked on tile at: " + x + " and " + y);
        window.setMouseClickedHandler(handler);

        window.start();
        while(window.isRunning()) {
            AnimationFrame newFrame = window.waitForNextFrame();
            

        }


    }
}
