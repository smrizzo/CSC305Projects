import java.awt.*;
import java.awt.geom.Ellipse2D;


public class StarComponent extends Component implements CompObserver {
    public StarModel model;
    private CCController controller;
    private Marble[][] marble;
    private Color[] colors = {Color.RED, Color.pink, Color.BLUE, Color.GREEN, Color.DARK_GRAY, Color.ORANGE};
    private PlayerTriangle tri;

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


        g.setColor(Color.GRAY);
        g.fillRect(0, 0, size.width, size.height);

        if(model.getState().getText() == '1') {
            tri = new Player1Triangle(gIn, componentW, componentH);
        } else if(model.getState().getText() == '2') {
            tri = new Player2Triangle(gIn, componentW, componentH);
        }

        for(int y = 0; y < 17; y++) {
            for(int x = 0; x < 25; x++) {
                if(marble[y][x] != null) {
                    marble[y][x].transformToPixels(componentW, componentH);
                    double X = marble[y][x].getPixelsWidth() + 50; // + 5
                    double Y = marble[y][x].getPixelsHeight() + 10; //+ 10
                    double w = minDimension /18;
                    double h = minDimension/ 18;
                    Ellipse2D e = new Ellipse2D.Double((X - w/12.0), (Y - h/12.0), w, h); // X-w/12.0, Y-h/12.0

                    if(marble[y][x].getPlayer() instanceof Player1Marble) {
                        colorUpdate = colors[0];
                    } else if (marble[y][x].getPlayer() instanceof Player2Marble) {
                        colorUpdate = colors[1];
                    } else if (marble[y][x].getPlayer() instanceof Player3Marble) {
                        colorUpdate = colors[2];
                    } else if (marble[y][x].getPlayer() instanceof Player4Marble) {
                        colorUpdate = colors[3];
                    } else if (marble[y][x].getPlayer() instanceof Player5Marble) {
                        colorUpdate = colors[4];
                    } else if (marble[y][x].getPlayer() instanceof Player6Marble) {
                        colorUpdate = colors[5];
                    } else {
                        colorUpdate = Color.BLACK;
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


