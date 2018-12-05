import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Entities {  //TODO: This class will need to be re-worked. Right now it is a bunch of
                        // workarounds meant to temporarily solve the problem of working with entities


    public ArrayList<Vessel> vesselList = new ArrayList<Vessel>();
    public ArrayList<Projectile> projectileList = new ArrayList<Projectile>();


    public PlayerVessel getPlayerVessel() {
        return (PlayerVessel) vesselList.get(0);
    }

    public Projectile getProjectile(Integer i){
        return projectileList.get(i);
    }

    protected void addVesselToList(Vessel v) {
        vesselList.add(v);
    }

    protected void addProjectileToList(Projectile p) {
        projectileList.add(p);
    }
}