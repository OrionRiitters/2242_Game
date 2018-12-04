import java.awt.image.BufferedImage;

public class PlayerVessel extends Vessel {

    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private boolean keySpace;

    public PlayerVessel(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, int health,
                      Projectile projectile, BufferedImage sprite) {

        super(minX, minY, maxX, maxY, speed, collideDamage, health, projectile, sprite);

        keyLeft = false;
        keyRight = false;
        keyUp = false;
        keyDown = false;
        keySpace = false;
    }

    public void setKeyLeft(boolean keyLeft) {
        this.keyLeft = keyLeft;
    }

    public void setKeyRight(boolean keyRight) {
        this.keyRight = keyRight;
    }

    public void setKeyUp(boolean keyUp) {
        this.keyUp = keyUp;
    }

    public void setKeyDown(boolean keyDown) {
        this.keyDown = keyDown;
    }

    public void setKeySpace(boolean keySpace) {
        this.keySpace = keySpace;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }  // Might not need getters

    public boolean isKeyRight() {
        return keyRight;
    }

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeySpace() {
        return keySpace;
    }

    @Override
    protected void routine() {

        if (isKeyLeft()) {
            setMinX(getMinX() - 1); // Move left
            setMaxX(getMaxX() - 1);
        }
        if (isKeyRight()) {
            setMinX(getMinX() + 1); // Move right
            setMaxX(getMaxX() + 1);
        }
        if (isKeyUp()) {
            setMinY(getMinY() - 1); // Move up
            setMaxY(getMaxY() - 1);
        }
        if (isKeyDown()) {
            setMinY(getMinY() + 1); // Move down
            setMaxY(getMaxY() + 1);
        }
        if (isKeySpace()) { // Call this object's 'fire' method on its projectile object
            fire(getProjectile());
        }

    }

    protected void fire(Projectile p) {
        //@TODO Implement this
    }

}
