import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Image_Insertion {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Scanner sc;
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		
	    preparedStatement = connection.prepareStatement("INSERT INTO imageinsert VALUES (?,?,?)");
	    sc = new Scanner(System.in);
	    System.out.println("enter the id :");
	    Integer id = sc.nextInt();
	    System.out.println("enter the name :");
	    String name = sc.next();
	    File f = new File("C:\\Users\\Administrator\\Downloads\\cozy-home-interior-anime-style.jpg");
	    FileInputStream fisImage = new FileInputStream(f);
	    preparedStatement.setInt(1,id);
	    preparedStatement.setString(2,name);
	    preparedStatement.setBinaryStream(3, fisImage);
	    
	    Integer rowsUpdated = preparedStatement.executeUpdate();
	    System.out.println("no of rows upadted : "+rowsUpdated);
	    preparedStatement.close();
	    fisImage.close();
	    fis.close();
	    sc.close();
	    connection.close();;
	}

}
