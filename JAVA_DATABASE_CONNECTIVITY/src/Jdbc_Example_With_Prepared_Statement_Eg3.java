import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement_Eg3 {

    public static void main(String[] args) {

        // Object to store database configuration
        Properties properties = new Properties();

        try (
            // Using relative path instead of absolute path
            FileInputStream fis = new FileInputStream("./LoginDetails.properties");
            Scanner sc = new Scanner(System.in)
        ) {

            // Load database properties from file
            properties.load(fis);

            // Step 1: Create database connection
            try (Connection connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("userName"),
                        properties.getProperty("password"));

                 // Step 2: Prepare SQL DELETE statement
                 PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?")) {

                // Take employee ID from user
                System.out.print("Enter Employee ID to delete: ");
                int id = sc.nextInt();

                // Set parameter value in prepared statement
                ps.setInt(1, id);

                // Step 3: Execute delete query
                int rowsDeleted = ps.executeUpdate();

                // Display result
                if (rowsDeleted > 0) {
                    System.out.println("Employee deleted successfully.");
                } else {
                    System.out.println("No employee found with the given ID.");
                }

            } catch (SQLException e) {
                System.err.println("Database Error: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }
}