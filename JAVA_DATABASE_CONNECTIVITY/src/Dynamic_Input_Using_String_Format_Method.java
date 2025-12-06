import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic_Input_Using_String_Format_Method {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String userName = "practice";
        String password = "1234";

        Scanner sc = new Scanner(System.in);

        try {
            // Establishing connection
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();

            // Reading input
            System.out.println("enter the id of the employee");
            Integer id = sc.nextInt();

            System.out.println("enter the name of the employee");
            String name = sc.next();

            System.out.println("enter the department of the employee");
            String department = sc.next();

            System.out.println("enter the salary of the employee");
            Integer salary = sc.nextInt();

            System.out.println("enter the gender of the employee");
            String gender = sc.next();

            System.out.println("enter the age of the employee");
            Integer age = sc.nextInt();

            System.out.println("enter the city of the employee");
            String city = sc.next();

            // Creating SQL Query using format()
            String query = String.format(
                "INSERT INTO employee VALUES (%d, '%s', '%s', %d, '%s', %d, '%s')",
                id, name, department, salary, gender, age, city
            );

            // Executing query
            int rowsUpdated = statement.executeUpdate(query);
            System.out.println("Rows affected : " + rowsUpdated);

        } 
        catch (SQLException e) {
            System.out.println("Error occurred while inserting data: " + e.getMessage());
        } 
        finally {
            // Closing resources
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement: " + e.getMessage());
            }

            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }

            sc.close();
        }
    }
}
