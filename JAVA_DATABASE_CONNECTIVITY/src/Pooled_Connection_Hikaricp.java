import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class Pooled_Connection_Hikaricp {

	public static void main(String[] args) {
	String configFile = "C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\hikaricporacle.properties";
 //		String configFile = "C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\hikaridbmysql.properties";
		HikariConfig config =  new HikariConfig(configFile);
		
		
		try( HikariDataSource dataSource = new HikariDataSource(config)){
			Connection connection = dataSource.getConnection();		
			Statement statement = connection.createStatement();
			String query = "select * from scrollableapp";
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
			}
			resultSet.close();
			statement.close();
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		

	}

}
