
/**
 * You shouldn't need to modify this class at all.  It just creates a
 * screen, and puts a component inside it.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StarUI implements ViewObserver{

    private JFrame frame;
    private JButton button;
    private Component contents;
    private StarModel model;
    private CCController controller;
    private Image myImage;

    public StarUI(CCController controller, StarModel model, String name) {
        this.controller = controller;
        this.model = model;
        model.registerViewObserver(this);
        this.frame = new JFrame("Star's Hollow-" + name);

        ImageIcon myIcon  = new ImageIcon("./Images/undoButton.png");
        myImage = myIcon.getImage();
        Image newimg = myImage.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        myIcon = new ImageIcon(newimg);
        this.button = new JButton(myIcon);
        this.button.setBounds(20, 20, 50, 50);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setActionCommand("undoLastMove");
        this.frame.add(button);

        this.frame.setMinimumSize(new Dimension(600, 600));
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

        this.button.addActionListener(e -> undoButtonClicked(e));

    }

    public void undoButtonClicked(ActionEvent e) {
        System.out.println("Button was clicked");
        controller.buttonClicked(e.getActionCommand());
    }



    private void notifyLocation(int x, int y) {
            controller.foundClick(x, y);

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
