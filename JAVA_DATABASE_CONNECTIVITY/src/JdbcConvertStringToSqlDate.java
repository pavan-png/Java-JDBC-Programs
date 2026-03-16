import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class JdbcConvertStringToSqlDate {

    // Relative path for properties file
    private static final String PROPERTIES_PATH = "./LoginDetails.properties";

    // SQL query constant
    private static final String INSERT_QUERY =
            "INSERT INTO datecheck (name, dob, dom) VALUES (?, ?, ?)";

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
                Scanner scanner = new Scanner(System.in)
        ) {

            // Load DB configuration
            properties.load(fis);

            String url = properties.getProperty("url");
            String username = properties.getProperty("userName");
            String password = properties.getProperty("password");

            try (
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement ps = connection.prepareStatement(INSERT_QUERY)
            ) {

                System.out.print("Enter name: ");
                String name = scanner.next();

                System.out.print("Enter Date of Birth (dd-MM-yyyy): ");
                String dobInput = scanner.next();

                // Parse DOB
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);

                java.util.Date utilDob = sdf.parse(dobInput);
                Date sqlDob = new Date(utilDob.getTime());

                System.out.print("Enter Date of Marriage (yyyy-MM-dd): ");
                String domInput = scanner.next();

                Date sqlDom = Date.valueOf(domInput);

                // Set PreparedStatement parameters
                ps.setString(1, name);
                ps.setDate(2, sqlDob);
                ps.setDate(3, sqlDom);

                int rowsInserted = ps.executeUpdate();

                System.out.println("Rows inserted: " + rowsInserted);
            }

        } catch (IOException e) {
            System.out.println("Unable to load database configuration file.");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd-MM-yyyy for DOB.");
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }
    }
}