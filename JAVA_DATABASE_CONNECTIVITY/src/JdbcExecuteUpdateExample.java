import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class: JdbcExecuteUpdateExample
 * -------------------------------------------------------------
 * This program demonstrates how to execute DML operations
 * using JDBC executeUpdate() method.
 *
 * Operations supported by executeUpdate():
 *   • INSERT
 *   • UPDATE
 *   • DELETE
 *   • DDL statements (CREATE, DROP, ALTER)
 *
 * Return value:
 *   executeUpdate() returns the number of rows affected
 *   by the SQL statement.
 */

public class JdbcExecuteUpdateExample {

    // Database configuration (industry practice)
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
             * -------------------------------------
             * DriverManager selects the appropriate JDBC driver
             * and returns a Connection implementation object.
             */
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connection established successfully.");
            System.out.println("Connection implementation class: "
                    + connection.getClass().getName());


            /*
             * STEP 2: Create Statement Object
             * -------------------------------------
             * Statement is used to send SQL commands to the database.
             */
            statement = connection.createStatement();

            System.out.println("Statement implementation class: "
                    + statement.getClass().getName());


            /*
             * STEP 3: Define SQL Query
             * -------------------------------------
             * This query deletes employees from IT department.
             */
            String query = "DELETE FROM employee WHERE department = 'IT'";


            /*
             * STEP 4: Execute SQL Query
             * -------------------------------------
             * executeUpdate() is used for DML operations.
             *
             * It returns the number of rows affected.
             */
            int rowsAffected = statement.executeUpdate(query);


            /*
             * STEP 5: Display Result
             */
            System.out.println("Rows deleted from employee table: " + rowsAffected);

        }
        catch (SQLException e) {

            /*
             * Handles database related exceptions
             * Example:
             *   • Invalid SQL
             *   • Connection failure
             *   • Table not found
             */
            System.out.println("Error occurred while executing SQL statement.");
            e.printStackTrace();
        }

        finally {

            /*
             * STEP 6: Close Resources
             * -------------------------------------
             * Always close JDBC resources to avoid
             * memory leaks and connection exhaustion.
             *
             * Order of closing:
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