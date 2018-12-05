import java.awt.image.BufferedImage;

public class Vessel extends Entity {

    private int health;
    //private Projectile projectile;
    private int vesselID;
    private static int nextVesselID = 0;

    public Vessel(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, int health,
                 BufferedImage sprite, boolean active) {

        super(minX, minY, maxX, maxY, speed, collideDamage, sprite, active);

        this.health = health;
        //this.projectile = projectile;
        vesselID = nextVesselID; // vesselID's will be used to associated vessels with their projectiles
        nextVesselID++;
    }

    /*public Projectile getProjectile() {
        return projectile;
    }*/

    public int getVesselID() {
        return vesselID;
    }

    public void setHealth(int i) {
        health = i;
    }

    public int getHealth() {
        return health;
    }

    @Override
    protected void collide(Vessel v) {
        v.setHealth(v.getHealth() - v.getCollideDamage());
    }

    protected void routine(){
    //TODO Implement this
    }

}
