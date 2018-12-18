import java.sql.*;

public class DBManager {

    String url = "jdbc:sqlite:statistics.sqlite";

    public DBManager(Game game) {  // This constructor creates a table if there is not one already

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            String createTableSql = "CREATE TABLE IF NOT EXISTS player_statistics (hits_taken INTEGER, hits_given INTEGER," +
                    " score INTEGER, accuracy DOUBLE)";

            statement.execute(createTableSql);

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }
                // This method takes statistics and saves them to the database
    public void addStatsToDB(int hitsTaken, int hitsGiven, int score, double accuracy) {

        String insertPrepStatement = "INSERT INTO player_statistics VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement psInsert = connection.prepareStatement(insertPrepStatement)) {

            psInsert.setInt(1, hitsTaken);
            psInsert.setInt(2, hitsGiven);
            psInsert.setInt(3, score);
            psInsert.setDouble(4, accuracy);
            psInsert.executeUpdate();

        } catch (SQLException exc) {
            exc.printStackTrace();
        }

    }

    public void queryStatistics() { // This method takes data from DB, alphabetizes it and displays it
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {           // Set up connection/statement

            String selectAllFromDB = "SELECT hits_taken, hits_given, score, accuracy FROM player_statistics ORDER BY score DESC";
            ResultSet selectAllResults = statement.executeQuery(selectAllFromDB);   // Set up object from query

            int topTenAccum = 0;

            System.out.format("%20s%20s%20s%20s\n", "Score:", "Accuracy:", "Hits Taken:", "Hits Given:");

            while (selectAllResults.next() && topTenAccum < 11) {
                topTenAccum++;

                int hitsTaken = selectAllResults.getInt("hits_taken");
                int hitsGiven = selectAllResults.getInt("hits_given");
                int score = selectAllResults.getInt("score");
                double accuracy = selectAllResults.getInt("accuracy");
                System.out.format("%20s%20s%20s%20s\n", score, accuracy, hitsTaken, hitsGiven);


            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void clearDBEntries() {    // Clears all database entries
        final String deleteSql = "DELETE FROM player_statistics";

        try (Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()) {

            statement.execute(deleteSql);

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

}
