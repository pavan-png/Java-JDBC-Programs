import java.sql.*;

public class jdbc_Execute_Update_Method {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            String username = "practice";
            String password = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // corrected (oracle lowercase)

            // Establishing connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database");

            // Creating statement
            statement = connection.createStatement();

            // SQL delete query
            String query = "DELETE FROM Employee WHERE Department = 'IT'";

            // Executing the query
            Integer rowsAffected = statement.executeUpdate(query);

            System.out.println("Rows affected: " + rowsAffected);
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
