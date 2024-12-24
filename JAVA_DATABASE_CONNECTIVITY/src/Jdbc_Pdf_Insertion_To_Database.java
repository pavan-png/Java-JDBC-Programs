import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Pdf_Insertion_To_Database {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		preparedStatement = connection.prepareStatement("INSERT INTO pdfinsertion VALUES(?,?)");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the name");
		String name = sc.next();
		File f1 = new File("C:\\Users\\Administrator\\Downloads\\new\\resume.pdf");
		FileReader fr = new FileReader(f1);
		preparedStatement.setString(1,name);
		preparedStatement.setCharacterStream(2,fr);
		Integer rowsAffected = preparedStatement.executeUpdate();
		System.out.println("no of rows affected : "+rowsAffected);
		fr.close();
		sc.close();
		preparedStatement.close();
		connection.close();
		fis.close();
		
	}

}
