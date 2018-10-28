package others;

import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class WatchdogUI {

    private JFrame frame;
    private Component contents;

    public WatchdogUI(Watchdog model) {
        this.frame = new JFrame("Dine, baby, dine!");
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(400, 400));
        contents =  new WatchdogComponent(model);
        this.frame.add(contents);
    }

    public void setFrameVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void repaint(int ms) {
        contents.repaint(ms);
    }
}
