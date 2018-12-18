import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.ConcurrentModificationException;

public class Game {

    private final int FPS = 60;
    private final int MS = 1000;
    public static boolean exitGame = false;

    private long timeStamp = System.currentTimeMillis();
    private double delta = 0;
    private long lastFrameTimeMS = timeStamp;
    private double timeStep = (double) MS / (double) FPS; // 1000 ms / 60 FPS = 1 frame every 16.667 ms

    ImageLoader imageLoader = new ImageLoader();
    GUI gui;
    Entities entities;
    LevelOne levelOne;
    Collisions collisions;
    StatisticsManager statisticsManager;
    DBManager dbManager;
    TextMenu textMenu;


    public static void main(String[] args) {
        Game game = new Game();
        TextMenu textMenu = new TextMenu(game);
        textMenu.startMenu();
    }

    protected void mainLoop(Game game){ // Game loop, calculates when to call update()
        gui = new GUI(game);
        entities = new Entities(game);
        levelOne = new LevelOne(game);
        statisticsManager = new StatisticsManager(game);
        collisions = new Collisions(game);
        dbManager = new DBManager(game);

        exitGame = false;
        Vessel.nextVesselID = 0;

        levelOne.initializeLevel();
        gui.initialize();



        while(!exitGame) {
            timeStamp = System.currentTimeMillis();
            delta += timeStamp - lastFrameTimeMS; // Delta = this timestamp - last timestamp + remainder of
            lastFrameTimeMS = timeStamp;           // delta - timeStep

            while (delta >= timeStep && !exitGame) {         // Runs one frame, creates new delta from remainder of old delta
                update();
                delta -= timeStep;
            }
        }
        gui.frame.dispatchEvent(new WindowEvent(gui.frame, WindowEvent.WINDOW_CLOSING));

        if (entities.vesselList.get(0).getHealth() <= 0) {
            TextMenu.gameOver(statisticsManager);
        } else {
            TextMenu.youWon(statisticsManager, game);
        }

    }

    private void update() { // Call functions to update game here. This is called once every frame
        entities.runRoutines();
        collisions.runAllCollisions();
        collisions.runPlayerToProjectileCollisions(entities.getPlayerVessel(), entities.projectileList);
        entities.purgeProjectiles();
        entities.purgeVessels();
        levelOne.updateTime(timeStamp);
        levelOne.checkReleases();
        try { gui.frame.repaint(); }
        catch (ConcurrentModificationException cme) {}
    } // Swing is not thread-safe, and being that the errors being thrown do not affect gameplay in a noticeable
     // way, I am disregarding the exceptions for the time being. I am not showing the stacktrace because
}   // the console is being used as a menu in order to finish the game in time for presentation.