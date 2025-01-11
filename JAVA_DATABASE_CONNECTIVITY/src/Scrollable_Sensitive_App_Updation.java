import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Scrollable_Sensitive_App_Updation {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		Properties properties = null;
		ResultSet resultSet = null;
		
	
		
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		FileInputStream fis = new FileInputStream(f);
		properties = new Properties();
		properties.load(fis);
		
		
		
		 connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
	
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		String query = "select id,name,age,address from scrollableapp";
		
		// give the sql query with column names don't give * , if resultset is insensitive and updatable 
		resultSet = statement.executeQuery(query);
		
		System.out.println("before updation");
		System.out.println("id\tname\tage\taddress");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));		
		}
		System.out.println();
		
		
		
		System.out.println("records after updation");
		
	
		
		System.out.println("id\tname\tage\taddress");
		resultSet.beforeFirst();
		while(resultSet.next()) {
			int ageupdate = resultSet.getInt(3);
			if(ageupdate <=23) {
				int updatedage = ageupdate+1;
				resultSet.updateInt(3, updatedage);
				resultSet.updateRow();
			}
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));		
		}
	
	resultSet.close();
	fis.close();
	statement.close();
	connection.close();
	}
	
			
}


