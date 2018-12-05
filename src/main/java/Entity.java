import java.awt.image.BufferedImage;

public abstract class Entity { // This abstract class will contain Vessel and Projectile.

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int speed;
    private int collideDamage;
    private BufferedImage sprite;
    private boolean active;

    public Entity(int minX, int minY, int speed, int collideDamage, BufferedImage sprite, boolean active) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = minX + sprite.getWidth();
        this.maxY = minY + sprite.getWidth();
        this.collideDamage = collideDamage;
        this.sprite = sprite;
        this.active = active;
        this.speed = speed;
    }
    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getSpeed() {
        return speed;
    }

    public BufferedImage getSprite() {
        return sprite;
    }
    public void setMinX(int minX) {
        this.minX = minX;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCollideDamage(int collideDamage) {
        this.collideDamage = collideDamage;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getCollideDamage() {
        return collideDamage;
    }

    protected abstract void collide(Vessel v); // Implement this in child classes

    protected abstract void routine(); // Implement this in child classes


}

