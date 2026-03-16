import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Convert_Sql_Date_To_String {

    private static final String PROPERTIES_PATH = "./LoginDetails.properties";
    private static final String SELECT_QUERY =
            "SELECT name, dob, dom FROM datecheck WHERE name = ?";

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
                Scanner scanner = new Scanner(System.in)
        ) {

            // Load database properties
            properties.load(fis);

            String url = properties.getProperty("url");
            String username = properties.getProperty("userName");
            String password = properties.getProperty("password");

            try (
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)
            ) {

                System.out.print("Enter the name: ");
                String name = scanner.next();

                ps.setString(1, name);

                try (ResultSet resultSet = ps.executeQuery()) {

                    System.out.println("NAME\tDOB\t\tDOM");

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                    while (resultSet.next()) {

                        String rName = resultSet.getString("name");

                        java.sql.Date dob = resultSet.getDate("dob");
                        String formattedDob = sdf.format(dob);

                        java.sql.Date dom = resultSet.getDate("dom");
                        String formattedDom = sdf.format(dom);

                        System.out.println(rName + "\t" + formattedDob + "\t" + formattedDom);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading properties file.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }
    }
}