import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class CCController {

    private List<Ellipse2D> marbleShapes = new ArrayList<>();
    private List<MarbleViewTracker> marbleViewTracker = new ArrayList<>();
    private List<StarUI> starUIS = new ArrayList<>();

    public CCController(StarModel model, int numOfUI, String keyName) {
        for(int i = 0; i < numOfUI; i++) {
            starUIS.add(new StarUI(this, model ,keyName));
        }
        for(int i = 0; i < numOfUI; i++) {
            starUIS.get(i).setFrameVisible(true);
        }
    }

    public void addMarbleTracker(MarbleViewTracker marble) {
        marbleViewTracker.add(marble);
    }

    public void foundClick(int x, int  y) {
        for(MarbleViewTracker marble: marbleViewTracker) {
            if(marble.getMyShape().contains(x, y - marble.getMyShape().getHeight())) {

                System.out.println("found marble at click position, X: " + marble.getPoint().x + ", Y: " + marble.getPoint().y);
//                System.out.println("We found a piece that on the GUI that is a marble");
            }
        }
    }



}
