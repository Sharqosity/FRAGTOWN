import com.sun.org.apache.xpath.internal.SourceTree;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements KeyListener, MouseListener {

    private ArrayList<Sprite> enemies;
    private ArrayList<Sprite> bullets;
    private ArrayList<Sprite> coins;
    private Player player;
    private Planet planet;
    private ArrayList<StarsSmall> starsSmall;
    private ArrayList<StarsBig> starsBig;

    private int score = 0;

    private Timer timer;

    private double angle;

    private int tick = 0;

    private int radius = 80;

    public Point origin = new Point(getWidth() / 2, getHeight() / 2);

    private boolean[] keys;

    private int randomX, randomY;


    public static synchronized void playSound(final String url) {
        //TODO get the music working
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream("/res/" + url));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A


    //MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE AN A
    //MR. HOPPS GIVE mE //MR. HOPPS GIVE mE AN AAN A//MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A//MR. HOPPS GIVE mE AN A
//MR. HOPPS GIVE mE AN A

    public void ayylienGen(int amount) {

        for (int i = 0; i < amount; i++) {
            randomX = (int) (Math.random() * 2);
            randomY = (int) (Math.random() * 2);
//            randomX=java.util.Random.nextBoolean();

//            System.out.println("Randomx: "+randomX);
//            System.out.println("Randomy: "+randomY);
            if (randomX == 0 && randomY == 0) {
                enemies.add(new Ayylien(
                                (int) (0 - (Math.random() * 640 - 360)),
                                (int) (0 - (Math.random() * 360 - 180))
                        )
                );
            } else if (randomX == 0 && randomY == 1) {
                enemies.add(new Ayylien(
                                (int) (0 - (Math.random() * 640 - 360)),
                                (int) (720 + (Math.random() * 360 - 180))
                        )
                );
            } else if (randomX == 1 && randomY == 0) {
                enemies.add(new Ayylien(
                                (int) (1280 + (Math.random() * 640 - 360)),
                                (int) (0 - (Math.random() * 360 - 180))
                        )
                );
            } else if (randomX == 1 && randomY == 1) {
                enemies.add(new Ayylien(
                                (int) (1280 + (Math.random() * 640 - 360)),
                                (int) (720 + (Math.random() * 360 - 180))
                        )
                );
            }
        }
    }


    public GamePanel() {
        enemies = new ArrayList<Sprite>();
        bullets = new ArrayList<Sprite>();
        coins = new ArrayList<Sprite>();

        player = new Player();
        planet = new Planet();

        starsSmall = new ArrayList<StarsSmall>();
        starsBig = new ArrayList<StarsBig>();

        for (int i = 0; i < 200; i++) {
            starsSmall.add(new StarsSmall((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight())));
        }
        for (int i = 0; i < 20; i++) {
            starsBig.add(new StarsBig((int) (Math.random() * getWidth()), (int) (Math.random() * getHeight())));
        }

        //TODO background


        keys = new boolean[1000];
        addKeyListener(this);
        addMouseListener(this);

        //<<<ACTIONPERFORMED IS HERE>>>
        //The actionPerformed is here.  Code all time driven events here.
        timer = new Timer(1000 / 24, new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                tick++;
                if (tick % 48 == 0) {
                    ayylienGen(tick / 96);
                }


                //sample player movement

                planet.rotateBy(2);

                if (isKeyPressed(KeyEvent.VK_D)) {
                    angle += Math.toRadians(4); //+ 2 degrees.
                    player.rotate(angle, 2 * radius);
                    player.setDir(-(int) (angle * (180 / Math.PI)));
                }

                if (isKeyPressed(KeyEvent.VK_A)) {
                    angle -= Math.toRadians(4);
                    player.rotate(angle, 2 * radius);
                    player.setDir(-(int) (angle * (180 / Math.PI)));
                }


                if (isKeyPressed(KeyEvent.VK_SPACE)) {
                    Projectile projectile = new Projectile(player.getLoc().x + player.getWidth() / 2, player.getLoc().y + player.getHeight() / 2, Math.toDegrees(angle));
                    bullets.add(projectile);
                }


//sample enemy movement
                for (Sprite r : enemies)
                    if (r instanceof Ayylien)
                        ((Ayylien) r).move(1, 1); //Rock move method.


                //sample bullet movement
                if (bullets.size() > 0) {

                    Iterator<Sprite> iter = bullets.iterator();

                    while (iter.hasNext()) {
                        Sprite b = iter.next();

                        b.move(0, 0);   //projectile move(x,y) was written with dummy parameters.  Go rearited it .

                        if ((b.getLoc().x > getWidth() || b.getLoc().x < 0) || (b.getLoc().y > getHeight() || b.getLoc().y < 0)) {
                            iter.remove();
//                            System.out.println(" i remove it :) ");
                        }
                    }
//                    for (Sprite b : bullets) {
//                        b.move(0, 0);   //projectile move(x,y) was written with dummy parameters.  Go rearited it .
//                        if ((b.getLoc().x > getWidth() || b.getLoc().x < 0) || (b.getLoc().y > getHeight() || b.getLoc().y < 0)) {
//                            bullets.remove(b);
//                        }
//                    }
                }

                for (Sprite c : coins) {

                    int x = c.getLoc().x;
                    int y = c.getLoc().y;

                    if (x == 0)
                        x = 1;

                    c.move(0, 0);
//
                }


                //sample collision event
                for (int i = 0; i < enemies.size(); i++) {
                    if (player.intersects(enemies.get(i))) {
                        //TODO make the player like actually die instead of moving him offscreen
                        player.kill();

                    }
                    if (planet.intersects(enemies.get(i))) {
                        player.kill();
                    }
                }
                for (int i = 0; i < enemies.size(); i++) {

                    for (int j = 0; j < bullets.size(); j++) {
                        Sprite bull = bullets.get(j);
                        Sprite ene = enemies.get(i);
                        if (bull.intersects(ene)) {
                            coins.add(new Coin(bull.getLoc()));
                            enemies.remove(i);
                            bullets.remove(j);
                            i--; //don't skip any enemies.
                            j = bullets.size(); //stop checking the bullets against this removed enemy
                        }
                        for (int n = 0; n < coins.size(); n++) {
                            Sprite coin = coins.get(n);
                            if (player.intersects(coin)) {
                                score++;
                                coins.remove(n);
                            }
                            if (planet.intersects(coin)) {
                                coins.remove(n);
                            }
                        }
                        //TODO PLANET COLLISION


                        repaint();

                    }
                }
            }


        });
        timer.start();
    }


    //Draw all the things! Don't move anything or change any values here!  Just draw!
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        timer.start();


        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.white);
        g2.drawString("Score: " + score, 200, 200);

        for (Sprite s : enemies)
            s.draw(g2);
        for (Sprite b : bullets)
            b.draw(g2);
        for (Sprite c : coins) {
            c.draw(g2);
        }


        if (player.isAlive()) {
//            playSound("ayylmao.wav");
            player.draw(g2);
            planet.draw(g2);
        } else {
            timer.stop();
            g2.clearRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.BLACK);
            g2.drawString("you suck", getWidth() / 2 - 100, getHeight() / 2 - 50);
            g2.drawString((String.valueOf(score)), getWidth()/2 - 100, getHeight()/2);

        }
//        if(planet != null)

//        g2.drawImage(player.getPic(),player.getA(),null );


    }


    /*
    The keys array will hold a true value for a key that is held down,
    and a false if the key is not held down.  This structure allows
    for multiple keys to be pressed and recognized simultaneously.

    You should use the isKeyPressed method to determine what keys the
    user is pressing.

    Sample Usage:
     GameListener listener = new GameListener();
     ...
     if(listener.isKeyPressed(KeyEvent.VK_SPACE))
        player.shoot();
     if(listener.isKeyPressed(KeyEvent.VK_LEFT || KeyEvent.VK_A)
        player.move(-4, 0); //move left.
     */
    public boolean isKeyPressed(int code) {
        return keys[code];
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    //MOUSE LISTENERS


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}