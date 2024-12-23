import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Convert_Sql_Date_To_String {
	public static void main(String[] args) throws IOException, SQLException {
	Connection connection = null;
	Scanner sc = null;
	ResultSet resultSet = null;
	FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
	Properties properties = new Properties();
	properties.load(fis);
	
	connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
	PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM datecheck WHERE name = ? ");
	sc = new Scanner(System.in);
	System.out.println("enter the name :");
	String name = sc.next();
	preparedStatement.setString(1,name);
	resultSet = preparedStatement.executeQuery();
	System.out.println("name \t dob \t dom");
	while(resultSet.next()) {
		String rName = resultSet.getString(1);
		java.sql.Date dob  = resultSet.getDate(2);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String  sDob = sdf.format(dob);
		
		java.sql.Date dom = resultSet.getDate(3);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		String sdom = sdf1.format(dom);
		System.out.println(name +"\t"+sDob +"\t"+sdom);
		}
		resultSet.close();
		preparedStatement.close();
		sc.close();
		fis.close();
		connection.close();
	
	}
	
}
