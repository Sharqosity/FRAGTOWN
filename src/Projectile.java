import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 3/14/16.
 */
public class Projectile extends Sprite {


    private static BufferedImage pic;
    static{
        try{
            pic = ImageIO.read(new File("res/" + "proj.png"));
        }catch(IOException e){
            System.out.println("Didn't find the file.");
        }
    }


    private Player player;
    private int speed;

    public Projectile(int x, int y, double angle){
        //TODO implement angle
        super(new Point(x-5, y), pic);
        speed = 20;
        setDir((int)(angle));
//        System.out.println("init dir" + getDir());
    }


    @Override
    public int getDirection(Point from, Point to){
        double dx = to.x - from.x;
        double dy = from.y - to.y;
        int deg =  (int)Math.toDegrees(Math.atan(dy/dx));
        if(to.x < from.x)
            deg += 180;
        deg+=180;
        return deg;
    }

    @Override
    public ArrayList<Rectangle> getHitBoxes() {
        ArrayList<Rectangle> boxes = new ArrayList<Rectangle>();
        boxes.add(new Rectangle(getLoc().x, getLoc().y, pic.getWidth(), pic.getHeight()));
        return boxes;
    }

    @Override
    public void move(int dx, int dy) {
        dx = (int)(Math.cos(Math.toRadians(getDir()))*speed);
        dy = (int)(Math.sin(Math.toRadians(getDir()))*speed);
        super.move(dx, dy);

    }
}
