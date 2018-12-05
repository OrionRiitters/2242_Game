public class Game {

    private final int FPS = 60;
    private final int MS = 1000;
    private static boolean exitGame = false;

    private long timeStamp = System.currentTimeMillis();
    private double delta = 0;
    private long lastFrameTimeMS = timeStamp;
    private double timeStep = (double) MS / (double) FPS; // 1000 ms / 60 FPS = 1 frame every 16.667 ms
    private float moveBuffer = 0; // Used to control screen-wide movement speed
    private int destroyProjectilesAccum = 0;

    ImageLoader imageLoader = new ImageLoader();
    GUI gui;
    Entities entities;
    LevelOne levelOne;



    public static void main(String[] args) {
        Game game = new Game();
        game.mainLoop(game);
    }

    private void mainLoop(Game game){ // Game loop, calculates when to call update()
        gui = new GUI(game);
        entities = new Entities(game);
        levelOne = new LevelOne(game);

        levelOne.initializeLevel();
        gui.initialize();

        while(!exitGame) {
            timeStamp = System.currentTimeMillis();
            delta += timeStamp - lastFrameTimeMS; // Delta = this timestamp - last timestamp + remainder of
            lastFrameTimeMS = timeStamp;           // delta - timeStep

            while (delta >= timeStep) {         // Runs one frame, creates new delta from remainder of delta
                update();
                delta -= timeStep;
            }
        }
    }

    private void update() { // Call functions to update game here. This is called once every frame
        entities.runRoutines();
        destroyOOBProjectiles();
        gui.moveIt();
    }

    protected void destroyOOBProjectiles() { // After 16 frames, destroy all projectiles out of bounds
        destroyProjectilesAccum++;
        if (destroyProjectilesAccum == 16) {
            entities.purgeProjectiles();
            destroyProjectilesAccum = 0;
        }
    }
}