import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("andrew is a pilot");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 1280, 720); //(x, y, w, h)


        GamePanel panel = new GamePanel();
        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);

        window.setVisible(true);

    }
}
