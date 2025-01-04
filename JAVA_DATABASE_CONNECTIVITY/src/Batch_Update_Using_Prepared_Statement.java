import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Batch_Update_Using_Prepared_Statement {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		File f = null;
		FileInputStream fis = null;
		Properties properties = null;
		String query = null;
		Scanner sc = null;
		
		f = new File("C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		fis = new FileInputStream(f);
		properties = new Properties();
		properties.load(fis);
		
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		
		query = "insert into emp values (?,?,?)";
		preparedStatement = connection.prepareStatement(query);
		
		
		if(preparedStatement!=null) {
			sc = new Scanner(System.in);
			while (true) {
				System.out.println("enter the name of the employee");
				String name = sc.next();
				System.out.println("enter the age of the employee");
				Integer age = sc.nextInt();
				System.out.println("enther the address of the employee");
				String address = sc.next();
				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, age);
				preparedStatement.setString(3, address);
				preparedStatement.addBatch();
				
				System.out.println("press y to insert another record (or) n to ");
				String option = sc.next();
				if(option.equalsIgnoreCase("n")) {
					break;
				}
				
			}
		preparedStatement.executeBatch();
		System.out.println("records inserted successfully");
		}
		
		
	sc.close();
	fis.close();
	preparedStatement.close();
	connection.close();

	}

}
