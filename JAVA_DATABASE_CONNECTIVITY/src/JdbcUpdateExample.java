import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class: JdbcUpdateExample
 * ------------------------------------------------------------
 * This program demonstrates how to update records in a database
 * using JDBC executeUpdate() method.
 *
 * executeUpdate() is used for:
 *   • INSERT
 *   • UPDATE
 *   • DELETE
 *   • DDL statements
 *
 * Return value:
 *   Number of rows affected by the SQL statement.
 */

public class JdbcUpdateExample {

    // Database configuration constants (industry practice)
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521:orcl";

    private static final String USERNAME = "practice";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        // JDBC resources
        Connection connection = null;
        Statement statement = null;

        try {

            /*
             * STEP 1: Establish Database Connection
             * ------------------------------------------------
             * DriverManager identifies the appropriate JDBC
             * driver and returns a Connection implementation.
             */
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connection established successfully.");
            System.out.println("Connection implementation class: "
                    + connection.getClass().getName());


            /*
             * STEP 2: Create Statement Object
             * ------------------------------------------------
             * Statement is used to send SQL commands to the database.
             */
            statement = connection.createStatement();

            System.out.println("Statement implementation class: "
                    + statement.getClass().getName());


            /*
             * STEP 3: Define SQL UPDATE Query
             * ------------------------------------------------
             * This query updates salary of employee whose name is 'Pavan'.
             */
            String query =
                    "UPDATE employee SET salary = 200000 WHERE name = 'Pavan'";


            /*
             * STEP 4: Execute SQL Statement
             * ------------------------------------------------
             * executeUpdate() returns number of rows affected.
             */
            int rowsAffected = statement.executeUpdate(query);


            /*
             * STEP 5: Display Result
             */
            System.out.println("Number of rows updated: " + rowsAffected);

        }
        catch (SQLException e) {

            /*
             * Handles database related exceptions
             * Examples:
             *   • Invalid SQL syntax
             *   • Connection issues
             *   • Table not found
             */
            System.out.println("Error occurred while executing SQL UPDATE operation.");
            e.printStackTrace();
        }

        finally {

            /*
             * STEP 6: Close JDBC Resources
             * ------------------------------------------------
             * Always close resources to prevent memory leaks.
             *
             * Closing order:
             * Statement → Connection
             */

            // Close Statement
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed successfully.");
                }
            }
            catch (SQLException e) {
                System.out.println("Error closing Statement.");
                e.printStackTrace();
            }

            // Close Connection
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                }
            }
            catch (SQLException e) {
                System.out.println("Error closing Connection.");
                e.printStackTrace();
            }
        }
    }
}