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
public class Player extends Sprite
{

    private static BufferedImage pic;
    private int picOrientation;
    private boolean alive;
//    private GamePanel gamePanel;
//    private AffineTransform at = new AffineTransform();;

    static
    {
        try
        {
            pic = ImageIO.read(new File("res/" + "plane.png"));
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

    public Player()
    {
        super(new Point(632-pic.getWidth()/2, 341-pic.getHeight()/2), pic);
        picOrientation = 0;
        alive = true;
//        super(new Point(400, 300), pic);

    }


    public void rotate(double ang, int rad)
    {
//        AffineTransform at = new AffineTransform();
//        at.rotate(ang);


//super.draw(Graphics2D g2);
        //X = r * cosine(angle)
        //Y = r * sine(angle)
        setLoc((int) (rad * Math.cos(ang)) + 632-pic.getWidth()/2, (int) (rad * Math.sin(ang)) + 341-pic.getHeight()/2);
//        int drawLocationX = 300;
//        int drawLocationY = 300;


    }

    @Override
    public void draw(Graphics2D g2)
    {

        double rotationRequired = Math.toRadians(picOrientation - getDir());
        //TODO fix this so it rotates around the center
        double locationX = pic.getWidth() / 2;
        double locationY = pic.getHeight() / 2;
//        g2.translate(locationX, locationY);
//        g2.rotate(rotationRequired);
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        g2.drawImage(op.filter(pic, null), getLoc().x, getLoc().y, null);
//        g2.rotate(-rotationRequired);
//        g2.translate(-locationX, -locationY);

    }


    public void rotateBy(int delta)
    {
        setDir(getDir() + delta);
    }


    @Override
    public ArrayList<Rectangle> getHitBoxes()
    {
        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
        boxes.add(new Rectangle(getLoc().x, getLoc().y, pic.getWidth(), pic.getHeight()));
        return boxes;
    }

    public boolean isAlive(){ return alive;}
    public void kill(){alive = false;}

//    public int getDirection(Point from, Point to){
//        double dx = to.x - from.x;
//        double dy = from.y - to.y;
//        int deg =  (int)Math.toDegrees(Math.atan(dy/dx));
//        if(to.x < from.x)
//            deg += 180;
//        return deg;
//    }

//    public AffineTransform getA()
//    {
//        return at;
//    }
}
