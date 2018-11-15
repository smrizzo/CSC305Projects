import java.awt.*;

public class Player2Triangle extends PlayerTriangle{

    private Graphics g;

    public Player2Triangle(Graphics g, double width, double height) {
        super(20, 8, 17, 12, 25, 12, width, height);
        this.g = g;
        drawPlayerTriangle();

    }

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    @Override
    public void drawPlayerTriangle() {
        g.setColor(Color.WHITE);
        Polygon poly = new Polygon(getxPoints(), getyPoints(), 3);
        g.fillPolygon(poly);

    }
}
