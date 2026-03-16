import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement {

    // SQL query kept as constant (industry practice)
    private static final String INSERT_EMPLOYEE_SQL =
            "INSERT INTO employee (id, name, department, salary, gender, age) VALUES (?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {

        // Properties object to load DB credentials
        Properties properties = new Properties();

        // Try-with-resources for file and scanner
        try (FileInputStream fis = new FileInputStream(
"C:\\Users\\Pavan\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
             Scanner scanner = new Scanner(System.in)) {

            // Load database properties
            properties.load(fis);

            // Read DB credentials from properties file
            String url = properties.getProperty("url");
            String user = properties.getProperty("userName");
            String password = properties.getProperty("password");

            /*
             * Establish database connection
             * and create PreparedStatement
             */
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {

                // =========================
                // Taking input from user
                // =========================

                System.out.print("Enter Employee ID: ");
                int id = scanner.nextInt();

                System.out.print("Enter Employee Name: ");
                String name = scanner.next();

                System.out.print("Enter Department: ");
                String department = scanner.next();

                System.out.print("Enter Salary: ");
                int salary = scanner.nextInt();

                System.out.print("Enter Gender: ");
                String gender = scanner.next();

                System.out.print("Enter Age: ");
                int age = scanner.nextInt();

                /*
                 * Setting values into PreparedStatement
                 * Each ? placeholder corresponds to index
                 */

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, department);
                ps.setInt(4, salary);
                ps.setString(5, gender);
                ps.setInt(6, age);

                // Execute insert query
                int rowsInserted = ps.executeUpdate();

                System.out.println("Number of rows inserted: " + rowsInserted);

            } catch (SQLException e) {

                System.err.println("Database error occurred");
                e.printStackTrace();
            }

        } catch (IOException e) {

            System.err.println("Error loading properties file");
            e.printStackTrace();
        }
    }
}