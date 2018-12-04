import java.io.File;
import java.util.ArrayList;

public class Entities {  //TODO: This class will need to be re-worked. Right now it is a bunch of
                        // workarounds meant to temporarily solve the problem of working with entities

    File f = new File("images", "arrow.png");

    public static ArrayList<Entity> entitiesList = new ArrayList<Entity>();

    public Projectile projectile = new Projectile(20, 20, 40, 40, 2, 5,
            ImageLoader.loadImage(f));

    private PlayerVessel playerVessel = new PlayerVessel(50, 50, 100, 100,
            5, 2, 100, projectile, ImageLoader.loadImage(f));

    public PlayerVessel getPlayerVessel() {
        return playerVessel;
    }
}