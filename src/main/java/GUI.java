import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;


final public class GUI extends JFrame implements KeyListener {

    JFrame frame;
    DrawPanel drawPanel;

    ImageLoader imageLoader = new ImageLoader();
    File file = new File("images", "arrow.png");

    private int oneX = 7;
    private int oneY = 7;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;

    Game game;
    Entities entities;

    GUI(Game game) {
        this.game = game;
    }

    public void initialize()
    {
        frame = this; // Methods interfacing with KeyListener interface must use 'this' instead of 'frame'.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entities = game.entities;
        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(1000, 1000);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        addKeyListener(this);  // Set up key listener


    }

    class DrawPanel extends JPanel {
        BufferedImage image = entities.getPlayerVessel().getSprite(); // Grab playerShip image

        public void paintComponent(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(6, 6, this.getWidth() - 12, this.getHeight() - 12);
            g.setColor(Color.BLACK);
            g.drawImage(image, oneX, oneY, null);
        }
    }

    protected void moveIt() {
        oneX = entities.getPlayerVessel().getMinX();
        oneY = entities.getPlayerVessel().getMinY();
        frame.repaint();
    }

    public void keyTyped(KeyEvent e) {} // Must have this method because this class implements KeyListener

    public void keyPressed(KeyEvent e) {
        setKeys(e, true, true, true, true, true);
    }

    public void keyReleased(KeyEvent e) {
        setKeys(e, false, false, false, false, false);
    }

    // Sets player ship key values based on corresponding key pressed/released
    void setKeys(KeyEvent e, boolean a, boolean d, boolean w, boolean s, boolean space) {
        if (e.getKeyChar() == 'a') { game.entities.getPlayerVessel().setKeyLeft(a); }
        else if (e.getKeyChar() == 'd') { game.entities.getPlayerVessel().setKeyRight(d); }
        else if (e.getKeyChar() == 'w') { game.entities.getPlayerVessel().setKeyUp(w); }
        else if (e.getKeyChar() == 's') { game.entities.getPlayerVessel().setKeyDown(s); }
        else if (e.getKeyChar() == ' ') { game.entities.getPlayerVessel().setKeySpace(space); }
    }
}