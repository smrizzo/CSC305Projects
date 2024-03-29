
/**
 * You shouldn't need to modify this class at all.  It just creates a
 * screen, and puts a component inside it.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class StarUI implements ViewObserver{

    private JFrame frame;
    private Component contents;
    private StarModel model;

    public StarUI(StarModel model, String name) {
        this.model = model;
        model.registerViewObserver(this);
        this.frame = new JFrame("Star's Hollow-" + name);
        this.frame.setMinimumSize(new Dimension(400, 400));
        System.out.println("Height: " + frame.getHeight());
        System.out.println("Width: " + frame.getWidth());
        this.frame.setLayout(new BorderLayout());
        contents =  new StarComponent(model);
        this.frame.add(contents);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                notifyWindowClosing();
            }
        });
        contents.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                notifyLocation(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

    }

    private void notifyLocation(int x, int y) {

        System.out.println("clicked x:" + x);
        System.out.println("clicked y:" + y);
        Dimension size = contents.getSize();
        double componentH = size.getHeight();
        double componentW = size.getWidth();
        double minDimension = Math.min(componentH, componentW);
        System.out.println("x: " + (int)((x - 25) / (componentW/32)) + ", " + "y: " + (int)(y/(componentH/18)));

    }

    private void notifyWindowClosing() {

        frame.dispose();
        model.removeCompObserver((StarComponent) contents);
        model.removeViewObserver(this);
    }

    public void setFrameVisible(boolean visible) {
        frame.pack();
        frame.setVisible(visible);
    }

    @Override
    public void updateStar(StarModel m) {
        this.model = m;
        contents.repaint();
    }
}
