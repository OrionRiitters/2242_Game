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
            if (!((p.getMaxX() < 0 || p.getMinX() > game.gui.FRAME_WIDTH) || (p.getMaxY() < 0 || // If out of bounds,
                    p.getMinY() > game.gui.FRAME_HEIGHT))) {

                p.setProjectileIndex(projectileListBuffer.size());     // reset projectile's index,
                projectileListBuffer.add(p);                          // add projectile to bufferList,
            }
        }
        projectileList.removeAll(projectileList);              // remove contents of projectileList,
        projectileList.addAll(projectileListBuffer);        // add contents of bufferList to projectileList.
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

                if (getMaxX() == frameWidth && direction == "right") {  // Moves right, hits boundary,
                    direction = "left";                                // moves left, hits boundary, moves right, etc.
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

    }
}