package others;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class WatchdogComponent extends Component {

    private static Color[] chopstickColors = {      
        // Color by how many times chopstick has been claimed
            Color.green, Color.yellow, Color.red.brighter()
    };
    private static Color[] philosopherColors;
    private static String[] philosopherText;
    static {
	philosopherColors 
	    = new Color[Watchdog.PhilosopherState.values().length];
	philosopherText
	    = new String[Watchdog.PhilosopherState.values().length];

	philosopherColors[Watchdog.PhilosopherState.THINKING.ordinal()] 
	    = Color.blue.brighter();
	philosopherText[Watchdog.PhilosopherState.THINKING.ordinal()] = "T";

	philosopherColors[Watchdog.PhilosopherState.HUNGRY.ordinal()] 
	    = Color.magenta;
	philosopherText[Watchdog.PhilosopherState.HUNGRY.ordinal()] = "H";

	philosopherColors[Watchdog.PhilosopherState.EATING.ordinal()] 
	    = Color.cyan.darker();
	philosopherText[Watchdog.PhilosopherState.EATING.ordinal()] = "E";
    }
    private static Font philosopherFont = 
	new Font(Font.SANS_SERIF, Font.BOLD, 12);

    private Watchdog model;

    public WatchdogComponent(Watchdog model) {
        this.model = model;
    }

    public void paint(Graphics gIn)  {
        Graphics2D g = (Graphics2D) gIn;
        Dimension size = getSize();
        double h = size.getHeight();
        double w = size.getWidth();
        double minDim = Math.min(h, w);

        g.setColor(Color.black);
        g.fillRect(0, 0, size.width, size.height);

        int num = model.numberOfChopsticks;
	Font f = philosopherFont.deriveFont((float) (w / 16));
	FontMetrics fm = g.getFontMetrics(f);
        for (int i = 0; i < num; i++) {
	    Watchdog.PhilosopherState state = model.getPhilosopherState(i);
	    if (state == null) {
		continue;
	    }
            double theta = 2.0 * Math.PI * i / num;
            double x = 0.5 + 0.4 * Math.sin(theta);
            double y = 0.5 - 0.4 * Math.cos(theta);
            Ellipse2D e = new Ellipse2D.Double(
                                w * (x - 0.06), h * (y - 0.06), 
                                w * 0.12, h * 0.12);
            g.setColor(philosopherColors[state.ordinal()]);
            g.fill(e);
	    g.setColor(Color.white);
	    g.setFont(f);
	    String text = philosopherText[state.ordinal()];
	    Rectangle2D bounds = fm.getStringBounds(text, g);
	    g.drawString(text, (float) (x*w - bounds.getWidth() / 2),
		        (float) (y*h - bounds.getHeight() / 2 - bounds.getY()));
        }
        for (int i = 0; i < num; i++) {
            double theta = 2.0 * Math.PI * (i - 0.5) / num;
            g.setStroke(new BasicStroke((float) (0.03*minDim), 
                                        BasicStroke.CAP_ROUND, 
                                        BasicStroke.JOIN_ROUND));
            g.setColor(chopstickColors[model.getRequestCount(i)]);
            double x = 0.5 + 0.35 * Math.sin(theta);
            double y = 0.5 - 0.35 * Math.cos(theta);
            double x2 = 0.5 + 0.45 * Math.sin(theta);
            double y2 = 0.5 - 0.45 * Math.cos(theta);
            Line2D line = new Line2D.Double(x * w, y * h, x2 * w, y2 * h);
            g.draw(line);
        }
    }

}


