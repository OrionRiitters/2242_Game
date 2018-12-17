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

    int nextRelease = 0;
    long[] enemyReleaseTimes = {10000, 20000, 40000, 80000, 100000};


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

    private void initializeEntities(){

        entities.addVesselToList(new PlayerVessel(50, 300, 6,
                2, 100, imageLoader.getImage("playerVesselIMG"), true, game, true, Movement.N));

    }

    private void pullImages() {
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
    }

    protected void updateTime(long timeStamp) {
        levelTime = timeStamp - timeDifference;
    }

    protected void checkReleases() {
        if (levelTime > enemyReleaseTimes[nextRelease]) {
            switch(nextRelease) {
                case 0:
                    firstRelease();
                    break;
                case 1:
                    secondRelease();
                    break;
            }

            nextRelease++;
        }
    }

    private void firstRelease() {
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

}
