import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 3/11/16.
 */
public class Planet extends Sprite
{

    private static BufferedImage pic;
    private int picOrientation;
//    private GamePanel gamePanel;

//    private AffineTransform at = new AffineTransform();;

    static
    {
        try
        {
            pic = ImageIO.read(new File("res/" + "planet.png"));
        } catch (IOException e)
        {
            System.out.println("Didn't find the file.");
        }
    }

    public BufferedImage getPic()
    {
        return pic;
    }
    //Nothing to see here...

    public Planet()
    {
        super(new Point(632-pic.getWidth()/2, 341-pic.getHeight()/2), pic);
    }

    public void rotateBy(int delta)
    {
        setDir(getDir() + delta);
    }

    @Override
    public void draw(Graphics2D g2)
    {

        // The required drawing location
        int drawLocationX = 632-pic.getWidth()/2;
        int drawLocationY = 341-pic.getHeight()/2;

// Rotation information

        double rotationRequired = Math.toRadians (getDir());
        double locationX = pic.getWidth() / 2;
        double locationY = pic.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

// Drawing the rotated image at the required drawing locations
        g2.drawImage(op.filter(pic, null), drawLocationX, drawLocationY, null);

    }





    @Override
    public ArrayList<Rectangle> getHitBoxes()
    {
        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
        boxes.add(new Rectangle(getLoc().x, getLoc().y, pic.getWidth(), pic.getHeight()));
        return boxes;
    }


}
