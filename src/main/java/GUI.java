import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;


final public class GUI extends JFrame implements KeyListener {

    final public int FRAME_WIDTH = 640;
    final public int FRAME_HEIGHT = 480;

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 7;
    private int oneY = 7;

    Game game;
    Entities entities;
    ImageLoader imageLoader;

    GUI(Game game) {
        this.game = game;
    }

    public void initialize() {

        frame = this; // Methods interfacing with KeyListener interface must use 'this' instead of 'frame'.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imageLoader = game.imageLoader;
        entities = game.entities;
        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        addKeyListener(this);  // Set up key listener

    }

    class DrawPanel extends JPanel {
        BufferedImage player = entities.getPlayerVessel().getSprite(); // Grab playerShip image
        File f = new File("images", "Background.png");
        BufferedImage background = imageLoader.getImage("backgroundIMG");

        public void paintComponent(Graphics g) { // Paints background, then vessels, then projectiles
            g.drawImage(background, 0, 0, null);
            for (Vessel v : entities.vesselList) {
                g.drawImage(v.getSprite(), v.getMinX(), v.getMinY(), null);
            }

            for (Projectile p : entities.projectileList) {
                g.drawImage(p.getSprite(), p.getMinX(), p.getMinY(), null);
            }
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
        else if (e.getKeyChar() == ' ') {
            game.entities.getPlayerVessel().setKeySpace(space); // This block allows playerVessel's 'fire' method to
            if (space == false) {                              // instantiate projectiles immediately. See PlayerVessel
                PlayerVessel playerVessel = (PlayerVessel) entities.vesselList.get(0);
                playerVessel.setProjectileAccum(playerVessel.PROJECTILE_SPACING - 1);
            }
        }
    }
}