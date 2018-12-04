import java.awt.image.BufferedImage;

public class Projectile extends Entity {

    public Projectile(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, BufferedImage sprite) {

        super(minX, minY, maxX, maxY, speed, collideDamage, sprite);

    }

    @Override
    protected void collide(Entity e) {
        //@TODO Implement this
    }

    @Override
    protected void routine() {
        //@TODO Implement this
    }

    protected void fire(Entity e) {
        //@TODO Implement this
    }
}
