import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.Tile;

import java.awt.*;
import java.util.Random;

public class SolidColorCircle implements Tile {

    private final Color color;
    private final char text;
    private boolean hasCircle;
    Random rand = new Random();

    SolidColorCircle(Color color, char text, boolean hasCircle) {
        this.color = color;
        this.text = text;
        this.hasCircle = hasCircle;
    }


    @Override
    public void paint(Graphics2D g, Size size) {
        g.setColor(color);
        g.fillRect(0, 0, size.width, size.height);
        if(hasCircle) {
            float r = rand.nextFloat();
            float G = rand.nextFloat();
            float b = rand.nextFloat();
            g.setColor(new Color(r, G, b));
            g.fillOval(0, 0, size.width, size.height);
        }

    }

    @Override
    public char getText() {
        return text;
    }
}
