import java.awt.*;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 3/8/16.
 */
public interface  Collider {

    ArrayList<Rectangle> getHitBoxes();
    boolean intersects(Collider other);

}




