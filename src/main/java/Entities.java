import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Entities {

    Game game;
    ImageLoader imageLoader;

    public ArrayList<Vessel> vesselList = new ArrayList<Vessel>(); // Lists to contain all entities
    public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();

    private int destroyProjectilesAccum = 0;


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
        ArrayList<Projectile> projectileListCopy = new ArrayList<Projectile>(projectileList); // Used for projectileList.removeAll() function later.

        for (Projectile p : projectileList) {
            if (!((p.getMaxX() < 0 || p.getMinX() > game.gui.FRAME_WIDTH) || (p.getMaxY() < 0 || // If out of bounds,
                    p.getMinY() > game.gui.FRAME_HEIGHT)) && p.getActive()) { // or if inactive,

                p.setProjectileIndex(projectileListBuffer.size());     // reset projectile's index,
                projectileListBuffer.add(p);                          // add projectile to bufferList,
            }
        }
        projectileList.removeAll(projectileListCopy);              // remove contents of projectileList,
        projectileList.addAll(projectileListBuffer);        // add contents of bufferList to projectileList.
    }

    protected void purgeVessels() {  // This does the same thing as purgeProjectiles, but purges vessels and
                                    // only checks each vessels 'active' attribute, and not if it is OOB
        ArrayList<Vessel> vesselListBuffer = new ArrayList<Vessel>();
        ArrayList<Vessel> vesselListCopy = new ArrayList<Vessel>(vesselList);

        for (Vessel v : vesselList) {
            if (v.getActive()) {

                v.setVesselID(vesselListBuffer.size());
                vesselListBuffer.add(v);
            }
        }
        vesselList.removeAll(vesselListCopy);
        vesselList.addAll(vesselListBuffer);
    }

    protected void runRoutines() {
        for (Vessel v : vesselList) {
            v.routine();
        }

        for (Projectile p : projectileList) {
            p.routine();
        }
    }

    protected void createEnemy1(int minX, int minY) {

        addVesselToList(new Vessel(minX, minY,2, 2,
                50, imageLoader.getImage("enemyIMG"), true, false, Movement.E) {

            //String direction = "right";
            int frameWidth = game.gui.FRAME_WIDTH;
            int frameHeight = game.gui.FRAME_HEIGHT;
            int frame = 0;


            @Override
            protected void routine() { // If ship is out of bounds, send it back in bounds

                boolean OOB = false;
                frame = frame <= 32 ? frame + 1 : 0;

                if (getMaxX() > frameWidth) {
                    Movement.moveW(this, getSpeed());
                    OOB = true;
                }
                else if (getMinX() < 0) {
                    Movement.moveE(this, getSpeed());
                    OOB = true;
                }
                if (getMaxY() > frameHeight) {
                    Movement.moveN(this, getSpeed());
                    OOB = true;
                }
                else if (getMinY() < 0) {
                    Movement.moveS(this, getSpeed());
                    OOB = true;
                }

                if (!OOB) {
                    Movement.move(this, this.getDirection());
                }

                if (frame == 0) {
                    initializeProjectile();
                }


            }

            @Override
            protected void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

                addProjectileToList(new Projectile(getMinX() + 14, getMaxY() - 5, 4,
                        5, game.imageLoader.getImage("enemyProjectileIMG"), true, getVesselID(), game, false,
                        Movement.N) {

                    @Override
                    public void routine() {
                        Movement.moveS(this, getSpeed());
                    }

                });
            }

        });

    }

}