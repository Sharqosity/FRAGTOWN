import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by poopoo on 4/3/2016.
 */
public class StarsBig {


    private int x,y;
    private Color color;
    private static BufferedImage pic;
    private int picOrientation;
//    private GamePanel gamePanel;

//    private AffineTransform at = new AffineTransform();;

    public StarsBig(int x1, int y1){

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
        g2.drawLine(x-3, y, x+3, y);
        g2.drawLine(x-3, y-3, x+3, y+3);
        g2.drawLine(x-3, y, x+3, y);
        g2.drawLine(x+3, y-3, x-3, y+3);

    }

}
