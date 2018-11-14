
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
    private CCController controller;

    public StarUI(CCController controller, StarModel model, String name) {
        this.controller = controller;
        this.model = model;
        model.registerViewObserver(this);
        this.frame = new JFrame("Star's Hollow-" + name);
        this.frame.setMinimumSize(new Dimension(500, 500));
        this.frame.setLayout(new BorderLayout());
        contents =  new StarComponent(controller, model);
        this.frame.add(contents);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                notifyWindowClosing();
            }
        });
        this.frame.addMouseListener(new MouseListener() {
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
            controller.foundClick(x, y);
//        System.out.println("x: " + (int)((x - 25.0) / (componentW/32)) + ", " + "y: " +  (int)((y - 10.0)/(componentH/18)));

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
