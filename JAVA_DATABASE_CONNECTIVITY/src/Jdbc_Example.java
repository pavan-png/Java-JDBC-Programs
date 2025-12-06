import java.sql.*;

public class Jdbc_Example {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("Driver loaded successfully");

            String userName = "practice";
            String password = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";

            // Establish connection
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("The implementation class for Connection is: " 
                                + connection.getClass().getName());

            // Prepare query
            String query = "SELECT * FROM employee";

            // Create statement
            statement = connection.createStatement();
            System.out.println("The implementation class for Statement is: " 
                                + statement.getClass().getName());

            // Execute query
            resultSet = statement.executeQuery(query);
            System.out.println("The implementation class for ResultSet is: " 
                                + resultSet.getClass().getName());

            System.out.println("Id\tName\tDepartment\tSalary\tGender\tAge");

            // Process result
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String dept = resultSet.getString(3);
                Integer sal = resultSet.getInt(4);
                String gen = resultSet.getString(5);
                Integer age = resultSet.getInt(6);

                System.out.println(id + "\t" + name + "\t" + dept + "\t" + sal + "\t" + gen + "\t" + age);
            }

        } 
        catch (SQLException e) {
            System.out.println("Error while executing JDBC operations.");
            e.printStackTrace();
        } 
        finally {

            // Closing ResultSet
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet");
                e.printStackTrace();
            }

            // Closing Statement
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing Statement");
                e.printStackTrace();
            }

            // Closing Connection
            try {
                if (connection != null) {
                    connection.close();
                }
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Error closing Connection");
                e.printStackTrace();
            }
        }
    }
}
