public class Testing {

    public static void main(String[] args) {

        Testing test = new Testing();
        test.relativeDirectionTest();

    }

    private void relativeDirectionTest() {

        Game game = new Game();
        GUI gui = new GUI(game);
        Entities entities = new Entities(game);
        LevelOne levelOne = new LevelOne(game);
        Collisions collisions = new Collisions(game);

        levelOne.initializeLevel();
        gui.initialize();

        System.out.println(collisions.getRelativeDirection(entities.vesselList.get(1), entities.vesselList.get(2)));

    }

}
