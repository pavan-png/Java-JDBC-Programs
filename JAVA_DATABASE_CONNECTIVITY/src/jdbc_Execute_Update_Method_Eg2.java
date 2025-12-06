import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Execute_Update_Method_Eg2 {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // corrected (oracle lowercase)
            String userName = "practice";
            String password = "1234";

            // Establishing connection
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected to database");

            // SQL INSERT query
            String query = 
                "INSERT INTO employee VALUES (1019, 'pavan', 'IT', 99000, 'MALE', 24, 'VIJAYAWADA')";

            // Creating statement
            statement = connection.createStatement();

            // Executing query
            Integer rows = statement.executeUpdate(query);

            System.out.println("Number of rows affected: " + rows);
        }
        catch (SQLException e) {
            System.out.println("SQL error occurred");
            e.printStackTrace();
        }
        finally {

            // Closing Statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing Statement");
                e.printStackTrace();
            }

            // Closing Connection
            try {
                if (connection != null) connection.close();
                System.out.println("Resources closed");
            } catch (SQLException e) {
                System.out.println("Error closing Connection");
                e.printStackTrace();
            }
        }
    }
}
