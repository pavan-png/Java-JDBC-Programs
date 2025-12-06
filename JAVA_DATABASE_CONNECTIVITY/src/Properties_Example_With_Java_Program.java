import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Properties_Example_With_Java_Program {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            // 1. Load the properties file
            FileInputStream fis = new FileInputStream(
                "C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties"
            );

            // 2. Create Properties object and load data
            Properties properties = new Properties();
            properties.load(fis);

            // 3. Fetch key values from properties file
            String url = properties.getProperty("url");
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            // 4. Establish database connection
            connection = DriverManager.getConnection(url, userName, password);

            // 5. Print success message
            if (connection != null) {
                System.out.println("✅ Connection Successful!");
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading the properties file");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();

        } finally {
            // 6. Close the connection
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("🔒 Connection Closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
