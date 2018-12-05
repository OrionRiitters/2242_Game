import java.awt.image.BufferedImage;

public class Projectile extends Entity {

    private int vesselID;
    private int projectileIndex;
    Game game;
    Entities entities;

    public Projectile(int minX, int minY, int speed, int collideDamage, BufferedImage sprite,
                      boolean active, int vesselID, Game game) {

        super(minX, minY, speed, collideDamage, sprite, active);
        this.vesselID = vesselID;
        this.game = game;
        entities = game.entities;
        projectileIndex = entities.projectileList.size();

    }

    public int getProjectileIndex() {
        return projectileIndex;
    }

    public void setProjectileIndex(int i) {
        projectileIndex = i;
    }

    public int getVesselID() {
        return vesselID;
    }

    @Override
    protected void collide(Vessel v) {
        v.setHealth(v.getHealth() - v.getCollideDamage());
    }


    protected void routine() {
        // Override this when instantiating a Projectile
    }
}
