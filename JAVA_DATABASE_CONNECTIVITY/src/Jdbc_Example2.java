import java.sql.*;

public class Jdbc_Example2 {

    public static void main(String[] args) {

        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;

        try {
            String username = "practice";
            String password = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // corrected (oracle lowercase)

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection succeeded");

            // Create statement
            statement = connection.createStatement();

            // Execute query
            String query = "SELECT * FROM employee";
            resultSet = statement.executeQuery(query);

            // Output header
            System.out.println("Id\tName\tDepartment\tSalary\tGender\tAge");

            // Process ResultSet
            while (resultSet.next()) {
                Integer id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String dept = resultSet.getString("Department");
                Integer sal = resultSet.getInt("Salary");
                String gen = resultSet.getString("Gender");
                Integer age = resultSet.getInt("Age");

                System.out.println(id + "\t" 
                                   + name + "\t" 
                                   + dept + "\t" 
                                   + sal + "\t" 
                                   + gen + "\t" 
                                   + age);
            }

        } 
        catch (SQLException e) {
            System.out.println("SQL error occurred");
            e.printStackTrace();
        } 
        finally {

            // Close ResultSet
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet");
                e.printStackTrace();
            }

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
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Error closing Connection");
                e.printStackTrace();
            }
        }
    }
}
