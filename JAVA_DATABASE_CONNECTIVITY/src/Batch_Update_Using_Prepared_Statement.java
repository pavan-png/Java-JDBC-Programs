import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

public class Batch_Update_Using_Prepared_Statement {

    public static void main(String[] args) {

        String propertiesPath = "./LoginDetails.properties";
        String query = "INSERT INTO emp VALUES (?,?,?)";

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(propertiesPath);
                Scanner sc = new Scanner(System.in)
        ) {

            // Load properties
            properties.load(fis);

            try (
                    Connection connection = DriverManager.getConnection(
                            properties.getProperty("url"),
                            properties.getProperty("userName"),
                            properties.getProperty("password"));

                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {

                while (true) {

                    System.out.println("Enter employee name:");
                    String name = sc.next();

                    System.out.println("Enter employee age:");
                    int age = sc.nextInt();

                    System.out.println("Enter employee address:");
                    String address = sc.next();

                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, address);

                    preparedStatement.addBatch();

                    System.out.println("Press y to insert another record (or) n to stop");
                    String option = sc.next();

                    if (option.equalsIgnoreCase("n")) {
                        break;
                    }
                }

                // Execute batch
                preparedStatement.executeBatch();

                System.out.println("Records inserted successfully");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}