public class Game {

    private final int FPS = 60;
    private final int MS = 1000;
    private static boolean exitGame = false;

    private long timeStamp = System.currentTimeMillis();
    private double delta = 0;
    private long lastFrameTimeMS = timeStamp;
    private double timeStep = (double) MS / (double) FPS; // 1000 ms / 60 FPS = 1 frame every 16.667 ms
    private float moveBuffer = 0; // Used to control screen-wide movement speed

    Entities entities = new Entities();
    GUI gui;


    public static void main(String[] args) {
        Game game = new Game();
        game.mainLoop(game);
    }

    private void mainLoop(Game game){ // Game loop, calculates when to call update()
        gui = new GUI(game);
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

    private void update() {
                                // Call functions to update game here. This is called once every frame
        gui.moveIt();

        entities.getPlayerVessel().routine();      // @TODO Maybe put playerShip into entitiesList?
        for (Entity e : entities.entitiesList) {
            e.routine();                         // This just runs routines to update entity attributes
        }
    }
}