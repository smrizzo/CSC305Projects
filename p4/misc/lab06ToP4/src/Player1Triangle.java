import java.awt.*;

public class Player1Triangle extends PlayerTriangle{
    private Graphics g;
    int x1, x2, x3;
    int y1, y2, y3;
    public Player1Triangle(Graphics g, double width, double height) {
        super(7, 12, 17, 12, 12, 16, width, height);
        this.x1 = 7;
        this.x2 = 17;
        this.x3 = 12;
        this.y1 = 12;
        this.y2 = 12;
        this.y3 = 16;
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
