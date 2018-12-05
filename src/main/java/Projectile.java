import java.awt.image.BufferedImage;

public class Projectile extends Entity {

    private int vesselID;

    public Projectile(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, BufferedImage sprite,
                      boolean active, int vesselID) {

        super(minX, minY, maxX, maxY, speed, collideDamage, sprite, active);
        this.vesselID = vesselID;

    }

    public int getVesselID() {
        return vesselID;
    }

    @Override
    protected void collide(Vessel v) {
        v.setHealth(v.getHealth() - v.getCollideDamage());
    }

    @Override
    protected void routine() {
        //@TODO Implement this
    }
}
