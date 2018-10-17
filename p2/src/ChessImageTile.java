import edu.calpoly.spritely.Size;
import edu.calpoly.spritely.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessImageTile implements Tile {

    public BufferedImage image;
    private final char text;
    private Color color;
    private Size size;
    public boolean hasPiece;
    private boolean letterSet;
    private boolean highLight;
    private String letter;

    public ChessImageTile(File imageFile, Size size, char text, Color color, Boolean hasPiece)
            throws IOException
    {
        BufferedImage im;
        if (GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless()) {
            im = null;
        } else {
            if (!imageFile.exists()) {
                throw new IOException("File not found:  " + imageFile);
            }
            try {
                im = ImageIO.read(imageFile);
            } catch (IOException ex) {
                System.err.println("***  Error reading " + imageFile);
                throw ex;
            }
            im = scaleImage(im, size);

        }
        this.image = im;
        this.text = text;
        this.color = color;
        this.size = size;
        this.hasPiece = hasPiece;
    }
    //different constructor for setting variables after initial set up
    public ChessImageTile(BufferedImage im, Size size, char text, Color color, Boolean hasPiece, Boolean highLight) {

        this.image = im;
        this.text = text;
        this.color = color;
        this.size = size;
        this.hasPiece = hasPiece;
        this.highLight = highLight;
    }

    public void setTileImage(BufferedImage image) {
        this.image = image;
    }

    public void setBorder(String letter) {
        this.letter = letter;
        letterSet = true;
        //System.out.println("We set letter:" + letter);
    }

    public void setTileColor(Color color) {
        //System.out.println("Color before setting:" + this.color);
        this.color = color;
        //System.out.println("Got inside set Color:" + this.color);
    }

    private static BufferedImage scaleImage(BufferedImage im, Size size) {
        if (im.getWidth() != size.width || im.getHeight() != size.height) {
            double scaleX = ((double) size.width) / im.getWidth();
            double scaleY = ((double) size.height) / im.getHeight();
            BufferedImage after =
                    new BufferedImage(size.width, size.height,im.getType());
            AffineTransform scale =
                    AffineTransform.getScaleInstance(scaleX, scaleY);
            AffineTransformOp scaleOp =
                    new AffineTransformOp(scale,
                            AffineTransformOp.TYPE_BILINEAR);
            scaleOp.filter(im, after);
            im = after;
        }
        return im;
    }



    @Override
    public void paint(Graphics2D g, Size size) {
        if(highLight) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(this.color);
            g.fillRect(5, 5, 90, 90);
        } else {
            g.setColor(this.color);
            g.fillRect(0, 0, size.width, size.height);
        }

        if(hasPiece) {
            g.drawImage(this.image, 0, 0, null);
        } else if (letterSet) {
            //System.out.println("Got inside letterSet painter");
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.setColor(Color.white);
            g.drawString(letter,  40, 60);
        }

    }

    @Override
    public char getText() {
        return text;
    }

    public Color getTileColor() {
        return color;
    }

    public Size getTileSize() {
        return size;
    }

    public BufferedImage getTileImage() {
        return image;
    }


}
