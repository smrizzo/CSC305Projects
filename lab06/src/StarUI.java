
/**
 * You shouldn't need to modify this class at all.  It just creates a
 * screen, and puts a component inside it.
 */

import javax.swing.*;
import java.awt.*;
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
        model.removeCompObserver((StarComponent)contents);
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
