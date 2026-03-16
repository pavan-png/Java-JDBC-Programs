import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Properties_Example_With_Java_Program {

    public static void main(String[] args) {

        try {

            // Step 1: Load database properties
            Properties properties = loadProperties("LoginDetails.properties");

            // Step 2: Create database connection
            Connection connection = createConnection(properties);

            // Step 3: Verify connection
            if (connection != null) {
                System.out.println("Connection established successfully.");
            }

            // Step 4: Close connection
            connection.close();
            System.out.println("Connection closed successfully.");

        } catch (Exception e) {
            System.out.println("Error occurred while connecting to database.");
            e.printStackTrace();
        }
    }

    /**
     * This method loads database configuration from properties file
     */
    private static Properties loadProperties(String filePath) throws IOException {

        Properties properties = new Properties();

        // try-with-resources automatically closes FileInputStream
        try (FileInputStream fis = new FileInputStream(filePath)) {

            properties.load(fis);
        }

        return properties;
    }

    /**
     * This method creates database connection using properties
     */
    private static Connection createConnection(Properties properties) throws SQLException {

        // Fetch values from properties file
        String url = properties.getProperty("url");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");

        // Establish connection
        return DriverManager.getConnection(url, userName, password);
    }
}