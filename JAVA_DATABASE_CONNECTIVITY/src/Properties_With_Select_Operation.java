import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Properties_With_Select_Operation {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. Load properties file
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");

            Properties properties = new Properties();
            properties.load(fis);

            // 2. Establish connection using properties file
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("userName"),
                    properties.getProperty("password"));

            System.out.println("✅ Connection succeeded!");

            // 3. Create statement object
            statement = connection.createStatement();

            // 4. Write SQL query
            String query = "SELECT * FROM employee";

            // 5. Execute query
            resultSet = statement.executeQuery(query);

            // 6. Process the result
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + "\t" +
                        resultSet.getString(2) + "\t" +
                        resultSet.getString(3) + "\t" +
                        resultSet.getInt(4) + "\t" +
                        resultSet.getString(5) + "\t" +
                        resultSet.getString(6)
                );
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading properties file");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Database error occurred");
            e.printStackTrace();

        } finally {
            // 7. Close all resources safely
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("🔒 All resources closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
