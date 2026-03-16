import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Class: JdbcExample
 * ------------------------------------------------------------
 * This program demonstrates the basic JDBC workflow:
 *
 * 1. Establish a database connection
 * 2. Create a SQL statement
 * 3. Execute the query
 * 4. Process the results
 * 5. Close all resources properly
 *
 * Database Used : Oracle
 * Table Used    : employee
 */

public class Jdbc_Example {

    // Database configuration (Industry practice: keep constants separate)
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USERNAME = "practice";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        // JDBC resources
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            /*
             * STEP 1: Establish Database Connection
             * ---------------------------------------
             * DriverManager chooses the appropriate JDBC driver
             * and returns a Connection implementation object.
             */
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database connection established successfully.");

            // Printing implementation class (for learning purposes)
            System.out.println("Connection Implementation Class: "
                    + connection.getClass().getName());


            /*
             * STEP 2: Create SQL Statement
             * ---------------------------------------
             * Statement is used to send SQL queries to the database.
             */
            statement = connection.createStatement();

            System.out.println("Statement Implementation Class: "
                    + statement.getClass().getName());


            /*
             * STEP 3: Define SQL Query
             */
            String query = "SELECT * FROM employee";


            /*
             * STEP 4: Execute Query
             * ---------------------------------------
             * executeQuery() is used for SELECT operations.
             * It returns a ResultSet object containing table data.
             */
            resultSet = statement.executeQuery(query);

            System.out.println("ResultSet Implementation Class: "
                    + resultSet.getClass().getName());


            /*
             * STEP 5: Process ResultSet
             * ---------------------------------------
             * ResultSet cursor initially points before the first row.
             * next() moves the cursor row by row.
             */
            System.out.println("----------------------------------------------------");
            System.out.println("ID\tName\tDepartment\tSalary\tGender\tAge");
            System.out.println("----------------------------------------------------");

            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String department = resultSet.getString(3);
                int salary = resultSet.getInt(4);
                String gender = resultSet.getString(5);
                int age = resultSet.getInt(6);

                System.out.println(id + "\t" + name + "\t" + department +
                        "\t" + salary + "\t" + gender + "\t" + age);
            }

        }
        catch (SQLException e) {

            /*
             * SQLException handles any database related issues
             * such as connection failure, query error, etc.
             */
            System.out.println("Error occurred during JDBC operations.");
            e.printStackTrace();
        }

        finally {

            /*
             * STEP 6: Close Resources
             * ---------------------------------------
             * Always close JDBC resources in reverse order
             * to prevent resource leaks.
             *
             * Order:
             * ResultSet -> Statement -> Connection
             */

            // Close ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                    System.out.println("ResultSet closed successfully.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet.");
                e.printStackTrace();
            }

            // Close Statement
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed successfully.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing Statement.");
                e.printStackTrace();
            }

            // Close Connection
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed successfully.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing Connection.");
                e.printStackTrace();
            }
        }
    }
}