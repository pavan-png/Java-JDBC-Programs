import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Scrollable_App {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		FileInputStream fis = null;
		
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\src\\Scrollable_App.java");
		fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		
		
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		connection.createStatement();
		}

}
