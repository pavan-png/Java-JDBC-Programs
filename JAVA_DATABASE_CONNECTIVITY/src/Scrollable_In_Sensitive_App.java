

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Scrollable_In_Sensitive_App {

    public static void main(String[] args) {

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("./LoginDetails.properties")) {

            props.load(fis);

            try (Connection connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("userName"),
                    props.getProperty("password"));

                 Statement statement = connection.createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE,
                         ResultSet.CONCUR_UPDATABLE);

                 ResultSet rs = statement.executeQuery(
                         "SELECT id, name, age, address FROM scrollableapp")) {

                System.out.println("Records before refresh");
                System.out.println("ID\tNAME\tAGE\tADDRESS");

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getInt("age") + "\t" +
                            rs.getString("address"));
                }

                System.out.println("\nApplication paused. Update database manually...");
                System.in.read();

                rs.beforeFirst();

                System.out.println("\nRecords after refresh");
                System.out.println("ID\tNAME\tAGE\tADDRESS");

                while (rs.next()) {

                    rs.refreshRow();

                    System.out.println(
                            rs.getInt("id") + "\t" +
                            rs.getString("name") + "\t" +
                            rs.getInt("age") + "\t" +
                            rs.getString("address"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}