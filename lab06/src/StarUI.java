
/**
 * You shouldn't need to modify this class at all.  It just creates a
 * screen, and puts a component inside it.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class StarUI implements myObserver{

    private JFrame frame;
    private Component contents;
    private StarModel model;

    public StarUI(StarModel model) {
        this.model = model;
        model.registerObserver(this);
        this.frame = new JFrame("Star's Hollow");
        this.frame.setMinimumSize(new Dimension(400, 400));
        this.frame.setLayout(new BorderLayout());
        contents =  new StarComponent(model);
        this.frame.add(contents);
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                notifyWindowClosing();
            }
        });
    }

    private void notifyWindowClosing() {

        frame.dispose();
        model.shutDown();
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
