import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Image_Insertion {

    // Relative Path
    private static final String PROPERTIES_PATH =".\\LoginDetails.properties";

    private static final String IMAGE_PATH =
            "C:\\Users\\Pavan\\Downloads\\Compressed\\banner.png";

    // SQL query
    private static final String INSERT_QUERY =
            "INSERT INTO imageinsert (id, name, image) VALUES (?, ?, ?)";

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
                Scanner scanner = new Scanner(System.in)
        ) {

            // Load database configuration
            properties.load(fis);

            String url = properties.getProperty("url");
            String username = properties.getProperty("userName");
            String password = properties.getProperty("password");

            try (
                    Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
                    FileInputStream imageStream = new FileInputStream(new File(IMAGE_PATH))
            ) {

                System.out.print("Enter ID: ");
                int id = scanner.nextInt();

                System.out.print("Enter name: ");
                String name = scanner.next();

                // Set parameters
                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setBinaryStream(3, imageStream);

                int rowsInserted = ps.executeUpdate();

                System.out.println("Rows inserted: " + rowsInserted);
            }

        } catch (IOException e) {
            System.out.println("Error reading properties or image file.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database operation failed.");
            e.printStackTrace();
        }
    }
}