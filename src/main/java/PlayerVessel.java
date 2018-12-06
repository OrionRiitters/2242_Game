import java.awt.image.BufferedImage;

public class PlayerVessel extends Vessel {

    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private boolean keySpace;
    final int PROJECTILE_SPACING = 10;
    private int projectileAccum = 0;
    Game game;
    Entities entities;
    ImageLoader imageLoader;

    public PlayerVessel(int minX, int minY, int speed, int collideDamage, int health,
                        BufferedImage sprite, boolean active, Game game) {

        super(minX, minY, speed, collideDamage, health, sprite, active);

        this.game = game;

        imageLoader = game.imageLoader;
        entities = game.entities;
        keyLeft = false;
        keyRight = false;
        keyUp = false;
        keyDown = false;
        keySpace = false;

    }

    public void setProjectileAccum(int i) {
        projectileAccum = i;
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
            Movement.moveW(this, getSpeed());
        }
        if (isKeyRight()) {
            Movement.moveE(this, getSpeed());
        }
        if (isKeyUp()) {
            Movement.moveN(this, getSpeed());
        }
        if (isKeyDown()) {
            Movement.moveS(this, getSpeed());
        }
        if (isKeySpace()) { // Call this object's 'fire' method
            fire();
        }

    }

    private void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

        entities.addProjectileToList(new Projectile(getMinX(), getMinY() + 9, 4,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID(), game) {

            @Override
            public void routine() {
                Movement.moveN(this, getSpeed());
            }

        });
        entities.addProjectileToList(new Projectile(getMinX() + 28, getMinY() + 9,4,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID(), game) {

            @Override
            public void routine() {
                Movement.moveN(this, getSpeed());
            }
        });
    }

    protected void fire() { // Space out projectile initialization
        projectileAccum++;

        if (projectileAccum == PROJECTILE_SPACING) {
            initializeProjectile();
            projectileAccum = 0;
        }
    }

    private void propel(int x, int y) {

    }

}
