import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Transaction_App {

    public static void main(String[] args) {

        String propertiesPath = "./LoginDetails.properties";

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(propertiesPath);
                Scanner sc = new Scanner(System.in)
        ) {

            // Load DB properties
            properties.load(fis);

            try (
                    Connection connection = DriverManager.getConnection(
                            properties.getProperty("url"),
                            properties.getProperty("userName"),
                            properties.getProperty("password"));

                    Statement statement = connection.createStatement()
            ) {

                // Display initial data
                ResultSet resultSet = statement.executeQuery("SELECT * FROM paymentapp");

                System.out.println("Name\tBalance");

                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t" + resultSet.getInt(2));
                }

                resultSet.close();

                System.out.println("Transaction Begins...");

                // Start transaction
                connection.setAutoCommit(false);

                statement.executeUpdate(
                        "UPDATE paymentapp SET amount = amount - 2000 WHERE name = 'ram'"
                );

                statement.executeUpdate(
                        "UPDATE paymentapp SET amount = amount + 2000 WHERE name = 'lakshman'"
                );

                System.out.println("Confirm transaction (y/n):");
                String option = sc.next();

                if (option.equalsIgnoreCase("y")) {
                    connection.commit();
                    System.out.println("Transaction Committed");
                } else {
                    connection.rollback();
                    System.out.println("Transaction Rolled Back");
                }

                // Display data after transaction
                ResultSet rs = statement.executeQuery("SELECT * FROM paymentapp");

                System.out.println("\nResults After Transaction");
                System.out.println("Name\tBalance");

                while (rs.next()) {
                    System.out.println(rs.getString(1) + "\t" + rs.getInt(2));
                }

                rs.close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}