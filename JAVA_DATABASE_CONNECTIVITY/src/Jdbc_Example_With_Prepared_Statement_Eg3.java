import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement_Eg3 {
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection  = null;
		Scanner sc = null;
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties  = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		PreparedStatement ps = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
		sc = new Scanner(System.in);
		System.out.println("enter the employee id to remove");
		String id = sc.next();
		ps.setString(1,id);
		int rowsUpdated = ps.executeUpdate();
		System.out.println("no of rows updated : "+rowsUpdated);
		sc.close();
		ps.close();
		connection.close();
		}
}
