import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by george_jiang on 3/16/16.
 */
public class Coin extends Sprite
{

    private static BufferedImage pic;
    private int speed;


    //set up the image.
    static
    {
        try
        {
            pic = ImageIO.read(new File("res/" + "dogecoin.png"));
        } catch (IOException e)
        {
            System.out.println("Didn't find the file.");
        }
    }

    public Coin(Point Loc)
    {
        super(Loc, pic);
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



//    public void move()
//    {
//
//        setDir();
//        super.move(dx, dy);
//    }

//    public double getSlope(Point p, Point origin){
//        return ((double)(origin.y-p.y)/(origin.x-p.x));
//
//    }


    @Override
    public ArrayList<Rectangle> getHitBoxes()
    {
        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
        boxes.add(new Rectangle(getLoc().x, getLoc().y, pic.getWidth(), pic.getHeight()));
        return boxes;
    }


}
