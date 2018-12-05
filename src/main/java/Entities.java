import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Entities {

    Game game;
    ImageLoader imageLoader;

    public ArrayList<Vessel> vesselList = new ArrayList<Vessel>(); // Lists to contain all entities
    public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();


    public Entities(Game game) {
        this.game = game;
        imageLoader = game.imageLoader;
    }

    public PlayerVessel getPlayerVessel() {
        return (PlayerVessel) vesselList.get(0);
    }

    public Projectile getProjectile(Integer i){
        return projectileList.get(i);
    }

    protected void addVesselToList(Vessel v) {
        vesselList.add(v);
    }

    protected void addProjectileToList(Projectile p) {
        projectileList.add(p);
    }

    protected void purgeProjectiles() {
        ArrayList<Projectile> projectileListBuffer = new ArrayList<Projectile>();
        for (Projectile p : projectileList) {
            if ((p.getMinX() < -100 || p.getMinX() > 1000) || (p.getMinY() < -100 || p.getMinY() > 1000)) {

            } else {                                                    // Create a new list of in-bounds projectiles,
                p.setProjectileIndex(projectileListBuffer.size());     // Purge projectileList, then repopulate it
                projectileListBuffer.add(p);                          // with in-bounds projectiles list.
            }
        }
        projectileList.removeAll(projectileList);
        projectileList.addAll(projectileListBuffer);
    }

    protected void runRoutines() {
        for (Vessel v : vesselList) {
            v.routine();                         // This just runs routines to update entity attributes
        }

        for (Projectile p : projectileList) {
            p.routine();
        }
    }

    protected void createEnemy1(int minX, int minY) {
        addVesselToList(new Vessel(minX, minY,5, 2,
                50, imageLoader.getImage("enemyIMG"), true) {
            String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            @Override
            protected void routine() {

                if (getMaxX() == frameWidth && direction == "right") {
                    direction = "left";
                } else if (getMaxX() < frameWidth && direction == "right") {
                    Movement.moveE(this, 2);
                } else if (getMinX() == 0 && direction == "left"){
                    direction = "right";
                    Movement.moveE(this, 2);
                } else if (getMaxX() < frameWidth && direction == "left") {
                    Movement.moveW(this, 2);
                } else if (getMaxX() == frameWidth && direction == "left") {
                    Movement.moveW(this, 2);
                }
            }
        });

        System.out.println(vesselList.get(1).getSprite().getWidth());
        System.out.println(vesselList.get(0).getSprite().getWidth());

    }
}