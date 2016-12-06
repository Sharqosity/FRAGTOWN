import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by poopoo on 4/3/2016.
 */
public class StarsSmall {


    private int x,y;
    private Color color;
    private static BufferedImage pic;
    private int picOrientation;
//    private GamePanel gamePanel;

//    private AffineTransform at = new AffineTransform();;

    public StarsSmall(int x1, int y1){

        x = x1;
        y = y1;
        color = Color.WHITE;
    }
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

    public void draw(Graphics2D g2){

        g2.setColor(color);
        g2.drawLine(x-1, y, x+1, y);
        g2.drawLine(x, y-1, x, y+1);

    }

}
