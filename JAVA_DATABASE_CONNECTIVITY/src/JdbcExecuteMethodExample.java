import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class: JdbcExecuteMethodExample
 * ----------------------------------------------
 * Demonstrates usage of execute() method in JDBC.
 *
 * execute() can run ANY SQL statement:
 *   SELECT
 *   INSERT
 *   UPDATE
 *   DELETE
 *   DDL
 */

public class JdbcExecuteMethodExample {

    // Database configuration
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:orcl";

    private static final String USERNAME = "practice";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {

            /*
             * STEP 1: Establish Connection
             */
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection established successfully.");

            /*
             * STEP 2: Create Statement
             */
            statement = connection.createStatement();

            /*
             * STEP 3: SQL INSERT Query
             */
            String query =
            "INSERT INTO employee (id, name, department, salary, gender, age) " +
            "VALUES (1020, 'Devadatta', 'IT', 175000, 'Feale', 26)";

            /*
             * STEP 4: Execute SQL using execute()
             */
            boolean result = statement.execute(query);

            /*
             * STEP 5: Process Result
             */
            if(result) {
                System.out.println("Query returned a ResultSet.");
            }
            else {
                int rowsAffected = statement.getUpdateCount();
                System.out.println("Rows inserted: " + rowsAffected);
            }

        }
        catch (SQLException e) {
            System.out.println("Error occurred while executing SQL operation.");
            e.printStackTrace();
        }

        finally {

            // Close Statement
            try {
                if(statement != null) {
                    statement.close();
                    System.out.println("Statement closed successfully.");
                }
            }
            catch(SQLException e) {
                System.out.println("Error closing Statement.");
                e.printStackTrace();
            }

            // Close Connection
            try {
                if(connection != null) {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                }
            }
            catch(SQLException e) {
                System.out.println("Error closing Connection.");
                e.printStackTrace();
            }
        }
    }
}