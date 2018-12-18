public class StatisticsManager {

    private int hitsTaken = 0;
    private int hitsGiven = 0;
    private int shotsFired = 0;
    private int score = 0;
    private float accuracy = 0;

    Game game;



    StatisticsManager(Game game) {
        this.game = game;

    }


    public int getHitsTaken() { return hitsTaken; }

    public int getHitsGiven() { return hitsGiven; }

    public int getShotsFired() { return shotsFired; }

    public int getScore() { return score; }

    public float getAccuracy() { return accuracy; }

    public void incrementHitsTaken() { hitsTaken++; }

    public void incrementHitsGiven() {
        hitsGiven++;
        accuracy = ((float)hitsGiven / (float) shotsFired) * 100f;
    }

    public void incrementShotsFired() { shotsFired++; }

    public void setScore() {
        score = Math.round((accuracy * (float)hitsGiven) +
                (accuracy * (float)hitsGiven * game.entities.vesselList.get(0).getHealth()) / 1000);
    }

    public void setAccuracy(float accuracy) { this.accuracy = accuracy; }



}
