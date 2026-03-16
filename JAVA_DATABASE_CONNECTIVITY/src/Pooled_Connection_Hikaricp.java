import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Pooled_Connection_Hikaricp {

    public static void main(String[] args) {

        String configFile = "./hikaricporacle.properties";

        HikariConfig config = new HikariConfig(configFile);

        try (HikariDataSource dataSource = new HikariDataSource(config);
             Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM scrollableapp")) {

            System.out.println("Connected using HikariCP Connection Pool\n");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");

                System.out.println(id + "  " + name + "  " + age + "  " + address);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}