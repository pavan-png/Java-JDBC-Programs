import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic_Input_Using_String_Format_Method {

    public static void main(String[] args) {

        // Connection object represents connection between Java program and database
        Connection connection = null;

        // Statement object is used to execute SQL queries
        Statement statement = null;

        // Database connection details
        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String userName = "practice";
        String password = "1234";

        // Scanner used to read input from keyboard
        Scanner sc = new Scanner(System.in);

        try {

            // Step 1: Establish connection with database
            connection = DriverManager.getConnection(url, userName, password);

            // Step 2: Create statement object
            statement = connection.createStatement();

            // Step 3: Reading employee details from user

            System.out.println("Enter the id of the employee");
            Integer id = sc.nextInt();

            System.out.println("Enter the name of the employee");
            String name = sc.next();

            System.out.println("Enter the department of the employee");
            String department = sc.next();

            System.out.println("Enter the salary of the employee");
            Integer salary = sc.nextInt();

            System.out.println("Enter the gender of the employee");
            String gender = sc.next();

            System.out.println("Enter the age of the employee");
            Integer age = sc.nextInt();

            /*
             Step 4: Creating SQL query dynamically using String.format()

             %d  -> used for integer values
             %s  -> used for string values

             String values must be inside single quotes in SQL.
            */

            String query = String.format(
                    "INSERT INTO employee VALUES (%d, '%s', '%s', %d, '%s', %d)",
                    id, name, department, salary, gender, age
            );

            // Step 5: Execute the SQL query
            int rowsUpdated = statement.executeUpdate(query);

            // Step 6: Display how many rows are inserted
            System.out.println("Rows affected : " + rowsUpdated);

        }

        // Handling SQL exceptions
        catch (SQLException e) {

            System.out.println("Error occurred while inserting data: " + e.getMessage());
        }

        finally {

            // Step 7: Closing Statement object
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }

            // Step 8: Closing Connection object
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }

            // Step 9: Closing Scanner
            sc.close();
        }
    }
}