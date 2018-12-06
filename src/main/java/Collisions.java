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

        for (Vessel v : entities.vesselList) {
            for (int i = v.getVesselID() +1 ; i < entities.vesselList.size() ; i++) {
                v.collide(entities.vesselList.get(i));
                if (checkCollision(v, entities.vesselList.get(i))) {
                    entities.vesselList.get(i).collide(v);
                    v.collide(entities.vesselList.get(i));
                    System.out.println("VV-Collide!");
                }
            }

            for (Projectile p : entities.projectileList) {
                if (checkCollision(v, p)) {
                    p.collide(v);
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
