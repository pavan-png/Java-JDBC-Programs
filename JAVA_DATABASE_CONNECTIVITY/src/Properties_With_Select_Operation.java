import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Properties_With_Select_Operation {

	public static void main(String[] args) throws SQLException, IOException {
		
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet =null;
	FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
	Properties properties = new Properties();
	properties.load(fis);
	connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
	System.out.println("connection succeded");
	statement = connection.createStatement();
	String query = "SELECT * FROM employee";
	resultSet = statement.executeQuery(query);
	while(resultSet.next()) {
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6));
	}
	
	resultSet.close();
	statement.close(); 
	connection.close();
	
	}

}
