import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Convert_String_To_Sql_Date {

	public static void main(String[] args) throws IOException, SQLException, ParseException {
		Connection connection = null;
		Scanner sc = null;
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties = new Properties();
		properties.load(fis);
		connection	= DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO datecheck VALUES (?,?,?)");
		sc = new Scanner(System.in);
		System.out.println("Enter the name");
		String name = sc.next();
		System.out.println("enter the date of bith in format : dd-mm-yyyy ");
		String sDob = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = sdf.parse(sDob);
		long date = uDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);
		System.out.println("enter the dom in the format : yyyy-mm-dd ");
		String sdom = sc.next();
		java.sql.Date sqlDom = java.sql.Date.valueOf(sdom);
		preparedStatement.setString(1,name);
		preparedStatement.setDate(2,sqlDate);
		preparedStatement.setDate(3, sqlDom);
		int rowsUpdated = preparedStatement.executeUpdate();
		System.out.println("no of rows updated : "+rowsUpdated);
		preparedStatement.close();
		sc.close();
		fis.close();
		connection.close();
		}

}
