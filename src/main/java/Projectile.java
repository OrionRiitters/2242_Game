import java.awt.image.BufferedImage;

public class Projectile extends Entity {

    private int vesselID;
    private int projectileIndex;
    private boolean alwaysActive;
    Game game;
    Entities entities;

    public Projectile(int minX, int minY, int speed, int collideDamage, BufferedImage sprite,
                      boolean active, int vesselID, Game game, boolean friendly, String direction,
                      boolean alwaysActive) {

        super(minX, minY, speed, collideDamage, sprite, active, friendly, direction);
        this.vesselID = vesselID;
        this.game = game;
        this.alwaysActive = alwaysActive;
        entities = game.entities;
        projectileIndex = entities.projectileList.size();

    }

    public boolean isAlwaysActive() { return alwaysActive; } // If always active, projectiles do not disappear
                                                            // upon collision
    public void setProjectileIndex(int i) {
        projectileIndex = i;
    }


    @Override
    protected void collide(Vessel v) {

        v.setHealth(v.getHealth() - getCollideDamage());
        if (v.getHealth() <= 0 && v.getVesselID() == 0) {
            Game.exitGame = true;
        } else if (v.getHealth() <= 0 && v.getVesselID() != 0){v.setActive(false);}
    }


    protected void routine() {
        // Override this when instantiating a Projectile
    }
}
