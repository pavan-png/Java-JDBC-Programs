import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement {
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection  = null;
		Scanner sc = null;
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties  = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		PreparedStatement ps = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?)");
		sc = new Scanner(System.in);

		System.out.println("enter the id of the employee");
		Integer id  = sc.nextInt();
		
		System.out.println("enter the name of the employee");
		String name = sc.next();
		
		System.out.println("enter the department of the employee");
		String department = sc.next();
		
		System.out.println("enter the salary of the employee");
		Integer salary = sc.nextInt();
		
		System.out.println("enter the gender of the employee");
		String gender = sc.next();
		
		System.out.println("enter the age of the employee");
		Integer age = sc.nextInt();
		
		System.out.println("enter the city of the employee");
		String city = sc.next();
		
		ps.setInt(1,id);
		ps.setString(2,name);
		ps.setString(3,department);
		ps.setInt(4,salary);
		ps.setString(5, gender);
		ps.setInt(6,age);
		ps.setString(7, city);
		
		Integer rowsUpdated = ps.executeUpdate();
		System.out.println("no of rows updated : "+rowsUpdated);
		sc.close();
		ps.close();
		connection.close();
		}
}
