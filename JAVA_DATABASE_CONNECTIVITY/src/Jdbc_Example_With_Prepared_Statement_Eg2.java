
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement_Eg2 {

    // SQL query stored as constant (industry best practice)
    private static final String SELECT_EMPLOYEE_BY_DEPARTMENT =
            "SELECT id, name, department, salary, gender, age FROM employee WHERE department = ?";

    public static void main(String[] args) {

        // Properties object to load DB configuration
        Properties properties = new Properties();

        // Try-with-resources to automatically close resources
        try (FileInputStream fis = new FileInputStream(
                "C:\\Users\\Pavan\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
             Scanner scanner = new Scanner(System.in)) {

            // Load database properties
            properties.load(fis);

            // Read credentials from properties file
            String url = properties.getProperty("url");
            String username = properties.getProperty("userName");
            String password = properties.getProperty("password");

            /*
             * Step 1: Establish database connection
             */
            try (Connection connection = DriverManager.getConnection(url, username, password);

                 /*
                  * Step 2: Create PreparedStatement
                  * Query contains placeholder (?) for department
                  */
                 PreparedStatement preparedStatement =
                         connection.prepareStatement(SELECT_EMPLOYEE_BY_DEPARTMENT)) {

                // Take department input from user
                System.out.print("Enter the department: ");
                String department = scanner.next();

                // Set department value to query parameter
                preparedStatement.setString(1, department);

                /*
                 * Step 3: Execute query and get result set
                 */
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println("\nEmployee Details");
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("ID\tNAME\tDEPARTMENT\tSALARY\tGENDER\tAGE");
                    System.out.println("-------------------------------------------------------------");

                    /*
                     * Step 4: Iterate through result set
                     */
                    while (resultSet.next()) {

                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String dept = resultSet.getString("department");
                        int salary = resultSet.getInt("salary");
                        String gender = resultSet.getString("gender");
                        int age = resultSet.getInt("age");

                        System.out.println(
                                id + "\t" +
                                name + "\t" +
                                dept + "\t" +
                                salary + "\t" +
                                gender + "\t" +
                                age
                        );
                    }
                }

            } catch (SQLException e) {

                System.err.println("Database error occurred while fetching employee data");
                e.printStackTrace();
            }

        } catch (IOException e) {

            System.err.println("Error loading database configuration file");
            e.printStackTrace();
        }
    }
}