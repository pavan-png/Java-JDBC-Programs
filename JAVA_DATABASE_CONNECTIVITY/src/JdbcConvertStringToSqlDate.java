import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class JdbcConvertStringToSqlDate {

    public static void main(String[] args) {

        String propertiesPath =
                "C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties";

        try (
            FileInputStream fis = new FileInputStream(propertiesPath);
            Scanner sc = new Scanner(System.in)
        ) {

            Properties properties = new Properties();
            properties.load(fis);

            try (
                Connection connection = DriverManager.getConnection(
                        properties.getProperty("url"),
                        properties.getProperty("userName"),
                        properties.getProperty("password"));

                PreparedStatement preparedStatement =
                        connection.prepareStatement("INSERT INTO datecheck VALUES (?,?,?)")
            ) {

                System.out.println("Enter the name:");
                String name = sc.next();

                System.out.println("Enter the date of birth (dd-MM-yyyy):");
                String sDob = sc.next();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date uDate = sdf.parse(sDob);
                long date = uDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(date);

                System.out.println("Enter the date of marriage (yyyy-MM-dd):");
                String sDom = sc.next();

                java.sql.Date sqlDom = java.sql.Date.valueOf(sDom);

                preparedStatement.setString(1, name);
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setDate(3, sqlDom);

                int rowsUpdated = preparedStatement.executeUpdate();
                System.out.println("No of rows updated: " + rowsUpdated);
            }

        } catch (IOException | SQLException | ParseException e) {
            // Centralized exception handling
            e.printStackTrace();
        }
    }
}
