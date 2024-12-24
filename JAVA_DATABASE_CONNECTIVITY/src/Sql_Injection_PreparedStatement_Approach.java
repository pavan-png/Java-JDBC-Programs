import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Sql_Injection_PreparedStatement_Approach {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		Scanner sc = new Scanner(System.in);
		preparedStatement = connection.prepareStatement("select count(*) from logindetails where username = ? and password = ? ");
		System.out.println("enter the username");
		String userName = sc.next();
		System.out.println("enter the password");
		String password = sc.next();
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, password);
		resultSet = preparedStatement.executeQuery();
		int row =0;
		if(resultSet.next()) {
			row = resultSet.getInt(1);
		}
		
		if (row==1) {
			System.out.println("valid crendentials ");
		}
		else {
			System.out.println("invalid credentials");
		}
		
		resultSet.close();
		preparedStatement.close();
		fis.close();
		connection.close();
	
	}

}
