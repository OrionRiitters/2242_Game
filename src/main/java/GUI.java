import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

import javax.swing.*;


final public class GUI extends JFrame implements KeyListener {

    final public int FRAME_WIDTH = 640;
    final public int FRAME_HEIGHT = 480;

    JFrame frame;
    DrawPanel drawPanel;


    Game game;
    Entities entities;
    ImageLoader imageLoader;

    GUI(Game game) {
        this.game = game;
    }

    public void initialize() {

        frame = this; // Methods interfacing with KeyListener interface must use 'this' instead of 'frame'.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        imageLoader = game.imageLoader;
        entities = game.entities;

        drawPanel = new DrawPanel();

        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.getContentPane().add(drawPanel, BorderLayout.PAGE_START);

        drawPanel.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        addKeyListener(this);  // Set up key listener

    }

    class DrawPanel extends JPanel {

        BufferedImage background = imageLoader.getImage("backgroundIMG");

        public void paintComponent(Graphics g) { // Paints background, then vessels, then projectiles
            try {
                g.drawImage(background, 0, 0, null);

                for (Vessel v : entities.vesselList) {
                    g.drawImage(v.getSprite(), v.getMinX(), v.getMinY(), null);
                }

                for (Projectile p : entities.projectileList) {
                    g.drawImage(p.getSprite(), p.getMinX(), p.getMinY(), null);
                }
            } catch (ConcurrentModificationException cme) {}
         // Swing is not thread-safe, and being that the errors being thrown do not affect gameplay in a noticeable
        // way, I am disregarding the exceptions for the time being. I am not showing the stacktrace because
       // the console is being used as a menu in order to finish the game in time for presentation.
        }
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