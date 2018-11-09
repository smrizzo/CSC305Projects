import java.awt.*;
import java.awt.geom.Ellipse2D;


public class StarComponent extends Component implements CompObserver {
    public StarModel model;
    private Circle [][]circle;

    public StarComponent(StarModel model) {
        this.model = model;
        this.circle = model.getCircles();
        model.registerCompObserver(this);
    }

    public void paint(Graphics gIn)  {
        Graphics2D g = (Graphics2D) gIn;
        Dimension size = getSize();
        double componentH = size.getHeight();
        double componentW = size.getWidth();
        double minDimension = Math.min(componentH, componentW);

        g.setColor(Color.black);
        g.fillRect(0, 0, size.width, size.height);


        for(int y = 0; y < 17; y++) {
            for(int x = 0; x < 25; x++) {
                if(circle[y][x] != null) {
                    circle[y][x].transformToPixels(componentW, componentH);
                    double X = circle[y][x].getPixelsWidth() + 5;
                    double Y = circle[y][x].getPixelsHeight() + 10;
                    double w = minDimension / 16.0;
                    double h = minDimension / 16.0;
                    Ellipse2D e = new Ellipse2D.Double(X-w/12.0, Y-h/12.0, w, h);
                    g.setColor(circle[y][x].getColor());
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
        this.circle = m.getCircles();
    }
}


