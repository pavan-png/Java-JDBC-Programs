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

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(
                    "C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
             Scanner sc = new Scanner(System.in)) {

            // Load DB properties
            properties.load(fis);

            // Step 1: Connection + PreparedStatement
            try (Connection connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("userName"),
                        properties.getProperty("password"));

                 PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM employee WHERE id = ?")) {

                System.out.print("Enter Employee ID to delete: ");
                int id = sc.nextInt();  // correct data type for ID

                ps.setInt(1, id);

                int rowsUpdated = ps.executeUpdate();
                System.out.println("Number of rows deleted: " + rowsUpdated);

            } catch (SQLException e) {
                System.err.println("Database Error: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }
}
