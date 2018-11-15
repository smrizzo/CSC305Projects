abstract public class PlayerTriangle {
    protected int[] xPoints = new int[3];
    protected int[] yPoints = new int[3];
    protected double width;
    protected double height;
    private Convert point1;
    private Convert point2;
    private Convert point3;


    public PlayerTriangle(int x1, int y1, int x2, int y2, int x3, int y3, double width, double height) {
        this.width = width;
        this.height = height;
        double minDimension = Math.min(height, width);
        double w = minDimension /16;
        double h = minDimension/ 16;
        point1 = new Convert(x1, y1, width, height);
        point2 = new Convert(x2, y2, width, height);
        point3 = new Convert(x3, y3, width, height);
        xPoints[0] = (int)((point1.getPixelsWidth() + 37) + w);
        xPoints[1] = (int)((point2.getPixelsWidth() + 16) + w);
        xPoints[2] = (int)((point3.getPixelsWidth() + 28) + w);

        yPoints[0] = (int)((point1.getPixelsHeight() + 5) + h);
        yPoints[1] = (int)((point2.getPixelsHeight() + 5) + h);
        yPoints[2] = (int)((point3.getPixelsHeight() + 20) + h);

    }

    abstract public void drawPlayerTriangle();


}
