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
    GUI gui;

    public PlayerVessel(int minX, int minY, int speed, int collideDamage, int health,
                        BufferedImage sprite, boolean active, Game game, boolean friendly, String direction) {

        super(minX, minY, speed, collideDamage, health, sprite, active, friendly, direction);

        this.game = game;
        gui = game.gui;


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
    protected void routine() { // Move direction based on keys pressed and player's position relative to the
                              // frame

        if (isKeyLeft()) Movement.moveW(this,
                getMinX() > 0 ? getSpeed() : 0);
        if (isKeyRight()) Movement.moveE(this,
                getMaxX() < gui.FRAME_WIDTH ? getSpeed() : 0);
        if (isKeyUp()) Movement.moveN(this,
                getMinY() > 0 ? getSpeed() : 0);
        if (isKeyDown()) Movement.moveS(this,
                getMaxY() < gui.FRAME_HEIGHT ? getSpeed() : 0);
        if (isKeySpace()) fire();


    }

    private void initializeProjectile() {  // This creates two new projectiles and adds them to entities.projectilesList

        entities.addProjectileToList(new Projectile(getMinX(), getMinY() + 9, 4,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID(), game, true,
                            Movement.N) {

            @Override
            public void routine() {
                Movement.moveN(this, getSpeed());
            }

        });
        entities.addProjectileToList(new Projectile(getMinX() + 28, getMinY() + 9,4,
                5, game.imageLoader.getImage("projectileIMG"), true, getVesselID(), game, true,
                            Movement.N) {

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

    @Override
    protected void collide(Vessel v) {
        v.setHealth(v.getHealth() - getCollideDamage());
        if (getHealth() <= 0) {setActive(false);}

    }

}
