import java.awt.image.BufferedImage;

public class PlayerVessel extends Vessel {

    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private boolean keySpace;
    final int PROJECTILE_SPACING = 10;
    int projectileAccum = 0;
    Game game;
    Entities entities;
    ImageLoader imageLoader;

    public PlayerVessel(int minX, int minY, int maxX, int maxY, int speed, int collideDamage, int health,
                        BufferedImage sprite, boolean active, Game game) {

        super(minX, minY, maxX, maxY, speed, collideDamage, health, sprite, active);

        this.game = game;

        imageLoader = game.imageLoader;
        entities = game.entities;
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
            setMinX(getMinX() - 2); // Move left
            setMaxX(getMaxX() - 2);
        }
        if (isKeyRight()) {
            setMinX(getMinX() + 2); // Move right
            setMaxX(getMaxX() + 2);
        }
        if (isKeyUp()) {
            setMinY(getMinY() - 2); // Move up
            setMaxY(getMaxY() - 2);
        }
        if (isKeyDown()) {
            setMinY(getMinY() + 2); // Move down
            setMaxY(getMaxY() + 2);
        }
        if (isKeySpace()) { // Call this object's 'fire' method on its projectile object
            fire();
        }

    }

    private void initializeProjectile() {


        entities.addProjectileToList(new Projectile(getMinX(), getMinY() + 9, 40, 40, 2,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID()) {

            @Override
            public void routine() {
                setMinY(getMinY() - 4);
            }


        });
        entities.addProjectileToList(new Projectile(getMinX() + 28, getMinY() + 9, 40, 40, 2,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID()) {

            @Override
            public void routine() {
                setMinY(getMinY() - 4);
            }


        });
    }

    protected void fire() {
        projectileAccum++;

        if (projectileAccum == PROJECTILE_SPACING) {
            initializeProjectile();
            projectileAccum = 0;
        }
    }

}
