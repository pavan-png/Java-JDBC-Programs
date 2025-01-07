import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Scrollable_App {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		Statement statement = null;
		FileInputStream fis = null;
		ResultSet resultSet = null;
		
		File f = new File("C:\\Users\\mohan\\pavan-workspace-adv-java\\Java-JDBC-Programs\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		
		
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		resultSet = statement.executeQuery("select * from scrollableapp");
		System.out.println("moving in foreward direction");
		System.out.println("id\tname\tage\taddress");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));		
		}
		System.out.println();
		System.out.println("moving in backward direction");
		while(resultSet.previous()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));		
		}
		
		System.out.println("printing 1st record");
		resultSet.first();
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing last record");
		resultSet.last();
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing the absolute record 4");
		resultSet.absolute(4);
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing the absolute record 2 ");
		resultSet.absolute(2);
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing the relative record 2 ");
		resultSet.relative(2);
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing the absolute record -2 ");
		resultSet.absolute(-2);
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		System.out.println("printing the relative record -1");
		resultSet.relative(-1);
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		resultSet.close();
		fis.close();
		statement.close();
		connection.close();
		}

}
