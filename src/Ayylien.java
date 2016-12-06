import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 3/15/16.
 */
public class Ayylien extends Sprite {
    private static BufferedImage pic;
    private int speed;

    //set up the image.
    static {
        try {
            pic = ImageIO.read(new File("res/" + "ayy lmao scaled.png"));
        } catch (IOException e) {
            System.out.println("Didn't find the file.");
        }
    }

    public Ayylien(int x, int y) {
        super(new Point(x, y), pic);
        speed = 3;

    }


    @Override
    public void move(/*int dir, */int oX, int oY)
    {

        int angleInDegrees = (int) (Math.atan2(this.getLoc().y-360, this.getLoc().x-640) * (180 / Math.PI));

        setDir(angleInDegrees);

        int dx = (int) (Math.cos(/*Math.toRadians*/(getDir())) * speed);
        int dy = (int) (Math.sin(/*Math.toRadians*/(getDir())) * speed);

        super.move(dx, dy);
    }

    @Override
    public ArrayList<Rectangle> getHitBoxes() {
        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
        boxes.add(new Rectangle(getLoc().x, getLoc().y, pic.getWidth(), pic.getHeight()));
        return boxes;
    }

}