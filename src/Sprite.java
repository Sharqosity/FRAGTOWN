import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 3/8/16.
 */
public abstract class Sprite implements Collider
{

    private Point loc;
    private BufferedImage pic; //put the file in the res folder.
    private int dir;

    public Sprite(Point myLoc, BufferedImage myPic)
    {
        loc = myLoc;
        pic = myPic;
    }

    public void draw(Graphics2D g2)
    {
        g2.drawImage(pic, loc.x, loc.y, null);
//        g2.drawImage(pic, getHeight()/2, getWidth()/2, null);

//        double rotationRequired = Math.toRadians(picOrientation - dir);
//        double locationX = pic.getWidth() / 2;
//        double locationY = pic.getHeight() / 2;
//        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
//        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
//        g2.drawImage(op.filter(pic, null), loc.x, loc.y, null);

    }

    public void move(int dx, int dy)
    {

        loc.translate(dx, dy);

    }

    public abstract ArrayList<Rectangle> getHitBoxes();

    public boolean intersects(Collider other)
    {
        for (Rectangle otherBox : other.getHitBoxes())
            for (Rectangle myBox : getHitBoxes())
                if (otherBox.intersects(myBox))
                    return true;

        return false;
    }

    public Point getLoc()
    {
        return loc;
    }

    public int getWidth()
    {
        return pic.getWidth();
    }

    public int getHeight()
    {
        return pic.getHeight();
    }

    public void setLoc(int x, int y)
    {
        loc.setLocation(x, y);
    }

    public int getDirection(Point from, Point to)
    {
        double dx = to.x - from.x;
        double dy = from.y - to.y;
        int deg = (int) Math.toDegrees(Math.atan(dy / dx));
        if (to.x < from.x)
            deg += 180;
        return deg;
    }

    public void setDir(int newDir)
    {
        dir = newDir;
    }

    public BufferedImage getPic()
    {
        return pic;
    }

    public void setPic(BufferedImage pic)
    {
        this.pic = pic;
    }

    public int getDir()
    {
        return dir;
    }

    public void setLoc(Point loc)
    {

        this.loc = loc;
    }
}
