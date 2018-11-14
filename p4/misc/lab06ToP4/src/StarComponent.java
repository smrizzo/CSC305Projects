import java.awt.*;
import java.awt.geom.Ellipse2D;


public class StarComponent extends Component implements CompObserver {
    public StarModel model;
    private CCController controller;
    private Marble[][] marble;
    private Color[] colors = {Color.RED, Color.pink, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY, Color.ORANGE};

    public StarComponent(CCController controller, StarModel model) {
        this.controller = controller;
        this.model = model;
        this.marble = model.getMarbles();
        model.registerCompObserver(this);
    }

    public void paint(Graphics gIn)  {
        Graphics2D g = (Graphics2D) gIn;
        Dimension size = getSize();
        double componentH = size.getHeight();
        double componentW = size.getWidth();
        double minDimension = Math.min(componentH, componentW);
        Color colorUpdate;

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size.width, size.height);


        for(int y = 0; y < 17; y++) {
            for(int x = 0; x < 25; x++) {
                if(marble[y][x] != null) {
                    marble[y][x].transformToPixels(componentW, componentH);
                    double X = marble[y][x].getPixelsWidth() + 25; // + 5
                    double Y = marble[y][x].getPixelsHeight() + 10; //+ 10
                    double w = minDimension /16;
                    double h = minDimension/ 16;
                    Ellipse2D e = new Ellipse2D.Double((X - w/2.0), (Y - h/2.0), w, h); // X-w/12.0, Y-h/12.0

                    if(marble[y][x].getPlayer() == 1) {
                        colorUpdate = colors[0];
                    } else if (marble[y][x].getPlayer() == 2) {
                        colorUpdate = colors[1];
                    } else if (marble[y][x].getPlayer() == 3) {
                        colorUpdate = colors[2];
                    } else if (marble[y][x].getPlayer() == 4) {
                        colorUpdate = colors[3];
                    } else if (marble[y][x].getPlayer() == 5) {
                        colorUpdate = colors[4];
                    } else if (marble[y][x].getPlayer() == 6) {
                        colorUpdate = colors[5];
                    } else {
                        colorUpdate = Color.WHITE;
                    }
                    controller.addMarbleTracker(new MarbleViewTracker(e, colorUpdate, new Point(x, y)));
                    g.setColor(colorUpdate);
                    g.fill(e);
                }

            }
        }
    }

    public void setModel(StarModel model) {
        this.model = model;
    }

    @Override
    public void updateComponent(StarModel m) {
        this.model = m;
        this.marble = m.getMarbles();
    }
}


