import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Execute_Update_Method_Eg3 {

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

            // SQL UPDATE query
            String query = "UPDATE employee SET salary = 200000 WHERE name = 'pavan'";

            // Creating statement
            statement = connection.createStatement();

            // Executing update
            Integer rowsAffected = statement.executeUpdate(query);
            System.out.println("Number of rows affected: " + rowsAffected);

        } 
        catch (SQLException e) {
            System.out.println("SQL error occurred");
            e.printStackTrace();
        } 
        finally {

            // Close Statement
            try {
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.out.println("Error closing Statement");
                e.printStackTrace();
            }

            // Close Connection
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
