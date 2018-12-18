import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class LevelOne {

    Game game;
    ImageLoader imageLoader;
    Entities entities;
    GUI gui;
    final long timeDifference;
    long levelTime = 0;

    int nextRelease = 0; // Sets up variables to control enemy release times
    long[] enemyReleaseTimes = {2000, 20000, 40000, 80000, 100000, 0};


    public LevelOne(Game game) {
        this.game = game;
        imageLoader = game.imageLoader;
        entities = game.entities;
        gui = game.gui;
        timeDifference = System.currentTimeMillis();
    }

    public void initializeLevel() {
        pullImages();
        initializeEntities();
    }

    private void initializeEntities(){ // Need to rename this method, as it only initializes playerVessel

        entities.addVesselToList(new PlayerVessel(50, 300, 6,
                2, 1000, imageLoader.getImage("playerVesselIMG"), true, game, true, Movement.N));

    }

    private void pullImages() { // Pulls images from images file and places them in memory
        imageLoader.addImageToHashMap("playerVesselIMG", new File("images", "PlayerVessel.png"));
        imageLoader.addImageToHashMap("projectileIMG", new File("images", "Projectile.png"));
        imageLoader.addImageToHashMap("backgroundIMG", new File("images", "Background.png"));
        imageLoader.addImageToHashMap("leftPropelIMG", new File("images", "LeftPropel.png"));
        imageLoader.addImageToHashMap("propelIMG", new File("images", "Propel.png"));
        imageLoader.addImageToHashMap("reversePropelIMG", new File("images", "ReversePropel.png"));
        imageLoader.addImageToHashMap("rightPropelIMG", new File("images", "RightPropel.png"));
        imageLoader.addImageToHashMap("enemyIMG", new File("images", "Enemy.png"));
        imageLoader.addImageToHashMap("enemyProjectileIMG", new File("images", "EnemyProjectile.png"));
        imageLoader.addImageToHashMap("enemy2IMG", new File("images", "Enemy2.png"));
        imageLoader.addImageToHashMap("enemy3IMG", new File("images", "Enemy3.png"));
        imageLoader.addImageToHashMap("boss1IMG", new File("images", "Boss1.png"));
        imageLoader.addImageToHashMap("bossProjectileIMG", new File("images", "BossProjectile.png"));
    }

    protected void updateTime(long timeStamp) {
        levelTime = timeStamp - timeDifference;
    }

    protected void checkReleases() { // After x amount of milliseconds, release next wave of enemies
        if (levelTime > enemyReleaseTimes[nextRelease]) {
            switch(nextRelease) {
                case 0:
                    firstRelease();
                    break;
                case 1:
                    secondRelease();
                    break;
                case 2:
                    thirdRelease();
                    break;
                case 3:
                    fourthRelease();
                    break;
                case 4:
                    fifthRelease();
                    break;
                case 5:
                    if (entities.vesselList.size() == 1) {
                        Game.exitGame = true;
                    }
            }

            if (nextRelease < 5) nextRelease++;
        }
    }

    private void firstRelease() {  // Each of these releases are called above by checkReleases().
        entities.createEnemy1(-30, 20);
        entities.createEnemy1(-50, 50);
        entities.createEnemy1(-80, 80);
        entities.createEnemy1(-110, 20);
        entities.createEnemy1(-140, 50);
        entities.createEnemy1(-170, 80);

    }

    private void secondRelease() {

        entities.createEnemy2(-30, 20);
        entities.createEnemy2(-500, 50);

        entities.createEnemy2(gui.FRAME_WIDTH + 1200, 20);
        entities.createEnemy2(gui.FRAME_WIDTH + 1300, 50);
        entities.createEnemy2(gui.FRAME_WIDTH + 1450, 20);
        entities.createEnemy2(gui.FRAME_WIDTH + 1550, 50);

    }

    private void thirdRelease() {

        entities.createEnemy1(-30, 20);
        entities.createEnemy2(-100, 40);
        entities.createEnemy3(-500, 60);
        entities.createEnemy3(gui.FRAME_WIDTH + 500, 10);

    }

    private void fourthRelease() {

        entities.createEnemy1(-33, 24);
        entities.createEnemy1(gui.FRAME_WIDTH + 300, 50);
        entities.createEnemy1(-80, 80);
        entities.createEnemy1(gui.FRAME_WIDTH + 300, 20);
        entities.createEnemy1(-140, 50);
        entities.createEnemy1(-170, 10);

        entities.createEnemy1(-30, 20);
        entities.createEnemy2(-100, 40);
        entities.createEnemy3(-500, 60);
        entities.createEnemy3(gui.FRAME_WIDTH + 500, 10);

    }

    private void fifthRelease() {

        entities.createBoss1(gui.FRAME_WIDTH / 2, -100);

    }

}
