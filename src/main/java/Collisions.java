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
                entities.vesselList.get(i).collide(playerVessel);
                playerVessel.collide(entities.vesselList.get(i));
                System.out.println("VV-Collide!");
            }


            for (Projectile p : entities.projectileList) {
                if (checkCollision(entities.vesselList.get(i), p)) {
                    p.collide(entities.vesselList.get(i));
                    p.setActive(false);
                    System.out.println("PV-Collide");
                }
            }
        }

    }

    public boolean checkCollision(Entity e1, Entity e2) {
        return ((e1.getMaxX() >= e2.getMinX() && e1.getMaxY() >= e2.getMinY()) &&
        (e1.getMinX() <= e2.getMaxX() && e1.getMinY() <= e2.getMaxY()));
    }

}
