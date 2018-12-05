import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class LevelOne {

    Game game;
    ImageLoader imageLoader;
    Entities entities;

    public LevelOne(Game game) {
        this.game = game;
        this.imageLoader = game.imageLoader;
        this.entities = game.entities;
    }


    public void initializeLevel() {
        pullImages();
        initializeEntities();


    }

    private void initializeEntities(){

        entities.addVesselToList(new PlayerVessel(50, 50, 100, 100, 5,
                2, 100, imageLoader.getImage("playerVesselIMG"), true, game));


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
    }


}
