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

        try {

            // Step 1: Load database properties
            Properties properties = loadProperties("LoginDetails.properties");

            // Step 2: Create database connection
            try (Connection connection = createConnection(properties);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM employee")) {

                System.out.println("Connection established successfully.\n");

                // Step 3: Process the result set
                while (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String department = resultSet.getString(3);
                    int salary = resultSet.getInt(4);
                    String gender = resultSet.getString(5);
                    int age = resultSet.getInt(6);

                    // Printing employee details
                    System.out.println(
                            id + "\t" +
                            name + "\t" +
                            department + "\t" +
                            salary + "\t" +
                            gender + "\t" +
                            age
                    );
                }

            } // resources automatically close here

        } catch (Exception e) {
            System.out.println("Error occurred while fetching data from database.");
            e.printStackTrace();
        }
    }

    /**
     * This method loads database properties from properties file
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

        String url = properties.getProperty("url");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, userName, password);
    }
}