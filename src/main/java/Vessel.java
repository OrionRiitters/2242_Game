import java.awt.image.BufferedImage;

public class Vessel extends Entity {

    private int health;
    private int vesselID;
    private static int nextVesselID = 0;

    public Vessel(int minX, int minY, int speed, int collideDamage, int health,
                 BufferedImage sprite, boolean active) {

        super(minX, minY, speed, collideDamage, sprite, active);

        this.health = health;
        vesselID = nextVesselID; // vesselID's will be used to associate vessels with their projectiles
        nextVesselID++;
    }

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
        v.setHealth(v.getHealth() - getCollideDamage());
    }

    protected void routine(){
        // Override this when instantiating a Vessel
    }

}
