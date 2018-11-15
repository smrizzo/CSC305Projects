public class Convert {
    private int x;
    private int y;
    private double pixelsHeight;
    private double pixelsWidth;
    private double width;
    private double height;
    public Convert(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        transformCoords();
    }

    private void transformCoords() {
        double pixelHeightTicks = (height /18);
        double pixelWidthTicks = width /32;
        this.pixelsHeight = pixelHeightTicks * y;
        this.pixelsWidth = pixelWidthTicks * x;
    }

    public double getPixelsHeight() {
        return pixelsHeight;
    }

    public double getPixelsWidth() {
        return pixelsWidth;
    }
}
