import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement {

    public static void main(String[] args) {

        // Load credentials and database properties
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(
                "C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
             Scanner sc = new Scanner(System.in)) {

            properties.load(fis);

            // Establish connection using try-with-resources
            try (Connection connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("userName"),
                        properties.getProperty("password"));

                 PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO employee VALUES(?,?,?,?,?,?,?)")) {

                // Input section
                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();

                System.out.print("Enter Employee Name: ");
                String name = sc.next();

                System.out.print("Enter Department: ");
                String department = sc.next();

                System.out.print("Enter Salary: ");
                int salary = sc.nextInt();

                System.out.print("Enter Gender: ");
                String gender = sc.next();

                System.out.print("Enter Age: ");
                int age = sc.nextInt();

                System.out.print("Enter City: ");
                String city = sc.next();

                // Set values into PreparedStatement
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, department);
                ps.setInt(4, salary);
                ps.setString(5, gender);
                ps.setInt(6, age);
                ps.setString(7, city);

                // Execute update
                int rowsUpdated = ps.executeUpdate();
                System.out.println("No. of rows inserted: " + rowsUpdated);

            } catch (SQLException e) {
                System.err.println("Database Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }
}
