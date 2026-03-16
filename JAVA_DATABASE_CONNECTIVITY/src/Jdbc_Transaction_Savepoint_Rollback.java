
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc_Transaction_Savepoint_Rollback {

    public static void main(String[] args) {

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream("./LoginDetails.properties")) {

            props.load(fis);

            try (Connection connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("userName"),
                    props.getProperty("password"));

                 Statement statement = connection.createStatement()) {

                System.out.println("Transaction begins");

                connection.setAutoCommit(false);

                statement.executeUpdate(
                        "insert into transactionsaveroll values ('pavan','jsp')");

                statement.executeUpdate(
                        "insert into transactionsaveroll values ('cbn','tdp')");
                
                statement.executeUpdate(
                        "insert into transactionsaveroll values ('modi','bjp')");

                Savepoint savepoint = connection.setSavepoint();

                statement.executeUpdate(
                        "insert into transactionsaveroll values ('Rahul','congress')");


                System.out.println("Something went wrong. Rolling back to savepoint");

                connection.rollback(savepoint);

                System.out.println("Rollback to savepoint completed");

                connection.commit();

                System.out.println("Transaction committed successfully");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}