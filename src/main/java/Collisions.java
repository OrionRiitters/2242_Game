import java.util.ArrayList;

public class Collisions {

    Game game;
    Entities entities;

    ArrayList<Vessel> checkedVessels = new ArrayList<Vessel>();

    public Collisions(Game game) {
        this.game = game;
        entities = game.entities;
    }
    public void runAllCollisions() {
        Vessel playerVessel = entities.vesselList.get(0);

        for (int i = 1 ; i < entities.vesselList.size() ; i++) {
            if (checkCollision(playerVessel, entities.vesselList.get(i))) {
                bounce(playerVessel, entities.vesselList.get(i));
                entities.vesselList.get(i).collide(playerVessel);
                playerVessel.collide(entities.vesselList.get(i));
            }


            for (Projectile p : entities.projectileList) {
                if (checkCollision(entities.vesselList.get(i), p)) {
                    p.collide(entities.vesselList.get(i));
                    p.setActive(false);
                }
            }
        }

    }


    public void runPlayerToProjectileCollisions(Vessel vessel, ArrayList<Projectile> projectileList ) {

        for (int i = 0 ; i < entities.projectileList.size() ; i++) {
            Projectile projectile = projectileList.get(i);
            if (checkCollision(vessel, projectile) && !projectile.isFriendly()) {
                projectile.collide(vessel);
                projectile.setActive(false);
            }
        }

    }

    public void runEnemyToProjectileCollisions(Vessel vessel, ArrayList<Projectile> projectileList) {

        for (int i = 0 ; i < entities.projectileList.size() ; i++) {
            Projectile projectile = projectileList.get(i);
            if (checkCollision(vessel, projectile) && projectile.isFriendly()) {
                projectile.collide(vessel);
                projectile.setActive(false);
            }
        }

    }


    public boolean checkCollision(Entity e1, Entity e2) {
        return ((e1.getMaxX() >= e2.getMinX() && e1.getMaxY() >= e2.getMinY()) &&
        (e1.getMinX() <= e2.getMaxX() && e1.getMinY() <= e2.getMaxY()));
    }

    public void bounce(Entity e1, Entity e2) {
        String relativeDirection = getRelativeDirection(e1, e2);
        Movement.move(e1, relativeDirection);
        Movement.move(e2, Movement.getOppositeDirection(relativeDirection));
    }

    public String getRelativeDirection(Entity e1, Entity e2) {
                                                            // Returns e1's relative direction to e2 using
        String direction = "";                               // the cardinal directions
        float e1MidX = getEntityMidX(e1);
        float e1MidY = getEntityMidY(e1);
        float e2MidX = getEntityMidX(e2);
        float e2MidY = getEntityMidY(e2);

        direction += (e1MidY < e2MidY) ? Movement.N : Movement.S;
        direction += (e1MidX < e2MidX) ? Movement.W : Movement.E;

        return direction;
    }

    private float getEntityMidX(Entity entity) {                // These two functions could maybe be attributes
        return (((float)entity.getMaxX()) - ((float)entity.getMinX()) / 2f) +
                (float) entity.getMinX(); // of the actual entity objects
    }

    private float getEntityMidY(Entity entity) {
        return (((float)entity.getMaxY()) - ((float)entity.getMinY()) / 2f) +
                (float) entity.getMinY();
    }

}
