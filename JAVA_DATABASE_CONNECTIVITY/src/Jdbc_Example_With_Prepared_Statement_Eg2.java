import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Example_With_Prepared_Statement_Eg2 {
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection  = null;
		Scanner sc = null;
		ResultSet resultSet = null;
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties  = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE department = ?");
		sc = new Scanner(System.in);
		System.out.println("enter the department");
		String department = sc.next();
		ps.setString(1,department);
		resultSet = ps.executeQuery();
		 System.out.println("Id\tName\tDepartment\tSalary\tGender\tAge");
		while(resultSet.next()) {
			 System.out.println(resultSet.getInt(1) +"\t"+resultSet.getString(2) +"\t" +resultSet.getString(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getInt(6));	
		}
		
		sc.close();
		resultSet.close();
		ps.close();
		connection.close();
		}
}
