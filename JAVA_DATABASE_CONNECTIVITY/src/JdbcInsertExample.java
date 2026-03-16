import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class: JdbcInsertExample
 * ----------------------------------------------------------
 * This program demonstrates how to insert data into a table
 * using JDBC executeUpdate() method.
 *
 * executeUpdate() is used for:
 *   • INSERT
 *   • UPDATE
 *   • DELETE
 *   • DDL operations
 *
 * It returns the number of rows affected.
 */

public class JdbcInsertExample {

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
             * -----------------------------------------
             * DriverManager locates the appropriate JDBC driver
             * and returns a Connection implementation object.
             */
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connection established successfully.");
            System.out.println("Connection implementation class: "
                    + connection.getClass().getName());


            /*
             * STEP 2: Define SQL Query
             * -----------------------------------------
             * This query inserts a new employee record.
             */
            String query =
                    "INSERT INTO employee (id, name, department, salary, gender, age ) VALUES (1019, 'Pavan', 'IT', 99000, 'Male', 24)";


            /*
             * STEP 3: Create Statement Object
             * -----------------------------------------
             * Statement is used to send SQL commands to the database.
             */
            statement = connection.createStatement();

            System.out.println("Statement implementation class: "
                    + statement.getClass().getName());


            /*
             * STEP 4: Execute SQL Query
             * -----------------------------------------
             * executeUpdate() returns the number of rows affected.
             */
            int rowsAffected = statement.executeUpdate(query);


            /*
             * STEP 5: Display Result
             */
            System.out.println("Number of rows inserted: " + rowsAffected);

        }
        catch (SQLException e) {

            /*
             * Handles database related exceptions
             * such as:
             *   • Connection issues
             *   • SQL syntax errors
             *   • Table not found
             */
            System.out.println("Error occurred while executing SQL INSERT operation.");
            e.printStackTrace();
        }

        finally {

            /*
             * STEP 6: Close Resources
             * -----------------------------------------
             * Always close JDBC resources to avoid
             * memory leaks and connection exhaustion.
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