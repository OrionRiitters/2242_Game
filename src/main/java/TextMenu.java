public class TextMenu {

    Game game;
    StatisticsManager statisticsManager;

    public TextMenu(Game game) {
        this.game = game;
        statisticsManager = game.statisticsManager;
    }

    public void startMenu() {

        int userInput;

        System.out.println("Hello! GUI's are difficult, so welcome to this game's text-based menu!");
        System.out.println("Would you like to:");
        System.out.println("Play the game? Enter 1");
        System.out.println("View High Scores? Enter 2");
        System.out.println("Get out of here? Enter 8");
        userInput = input.InputUtils.intInput("");

        switch(userInput) {
            case 1:
                game.mainLoop(game);
            case 2:
                highScores();
            case 8:
                System.exit(0);
            default:
                System.out.println("Think you're clever, entering an incorrect integer?");
                System.out.println("Now you'll have to start this process over entirely. Who's laughing now?");
                String moreInput = input.InputUtils.stringInput("Start process over? y/n");
                if (moreInput.equals("y")) {
                    startMenu();
                } else {
                    System.out.println("Okay, then. Exiting game!");
                    System.exit(0);
                }
        }
    }

    public void highScores() {
        DBManager newDBManager = new DBManager(game);
        newDBManager.queryStatistics();
    }

    public static void gameOver(StatisticsManager statisticsManager) {

        String userInput;
        statisticsManager.setScore();
        System.out.println("You've lost! Don't worry, whether or not you win this game is incredibly unimportant.");
        System.out.println("Here are the statistics for your play-through: (they will not be saved because you did not win)");
        System.out.println("Score: " + statisticsManager.getScore());
        System.out.println("Accuracy: " + statisticsManager.getAccuracy() + "%");
        System.out.println("You were hit " + statisticsManager.getHitsTaken() + " times! It's why you lost!");
        System.out.println("You were able to line up " + statisticsManager.getHitsGiven() + " effective shots!");
        userInput = input.InputUtils.stringInput("Return to main menu? y/n");
        if (userInput.equals("y")) {

            TextMenu newTextMenu = new TextMenu(new Game());
            newTextMenu.startMenu();
        }
        else {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }

    public static void youWon(StatisticsManager statisticsManager, Game game) {

        String userInput;
        statisticsManager.setScore();
        System.out.println("You've won! Your reward is the victory itself.");
        System.out.println("Here are the statistics for your play-through:");
        System.out.println("Score: " + statisticsManager.getScore());
        System.out.println("Accuracy: " + statisticsManager.getAccuracy() + "%");
        System.out.println("You were hit " + statisticsManager.getHitsTaken() + " times!");
        System.out.println("You were able to line up " + statisticsManager.getHitsGiven() + " effective shots!");
        userInput = input.InputUtils.stringInput("Return to main menu? y/n");

        game.dbManager.addStatsToDB(statisticsManager.getHitsTaken(), statisticsManager.getHitsGiven(),
                                    statisticsManager.getScore(), statisticsManager.getAccuracy());

        if (userInput.equals("y")) {

            TextMenu newTextMenu = new TextMenu(new Game());
            newTextMenu.startMenu();
        } else {
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }
}
