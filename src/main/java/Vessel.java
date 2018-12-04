import java.awt.image.BufferedImage;

public class Vessel extends Entity {

    private int health;
    private Projectile projectile;

    public Vessel(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, int health,
                Projectile projectile, BufferedImage sprite) {

        super(minX, minY, maxX, maxY, speed, collideDamage, sprite);

        this.health = health;
        this.projectile = projectile;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    @Override
    protected void collide(Entity e) {
        this.health -= e.getCollideDamage();
    }

    protected void routine(){
    //TODO Implement this
    }

}
