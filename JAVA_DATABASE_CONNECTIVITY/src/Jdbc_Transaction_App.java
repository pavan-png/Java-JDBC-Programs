import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_Transaction_App {

	public static void main(String[] args) throws IOException, SQLException {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	FileInputStream fis = null;
	Scanner sc = null;

	
	File f = new File("C:\\Users\\mohan\\pavan-workspace-adv-java\\Java-JDBC-Programs\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
	fis = new FileInputStream(f);
	Properties properties = new Properties();
	properties.load(fis);
	
	connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
	statement = connection.createStatement();
	resultSet = statement.executeQuery("select * from paymentapp");
	
	System.out.println("name" +"\t"+"balance");
	while(resultSet.next()) {
		System.out.println(resultSet.getString(1) +"\t"+resultSet.getInt(2));
		}
	System.out.println("transaction begins");
	connection.setAutoCommit(false);
	statement.executeUpdate("update paymentapp set amount = amount - 2000  where name = 'ram' " );
	statement.executeUpdate("update paymentapp set amount = amount + 2000 where name = 'lakshman' ");
	System.out.println("please confirm to proceed the transaction y/n");
	sc = new Scanner(System.in);
	String option = sc.next();
	if(option.equalsIgnoreCase("y")) {
		connection.commit();
	}
	else {
		connection.rollback();
	}
	
	ResultSet rs = statement.executeQuery("select * from paymentapp");
	System.out.println("results after transaction");
	System.out.println("name" +"\t"+"balance");
	while(rs.next()) {
		
		System.out.println(rs.getString(1) +"\t" +rs.getInt(2));
	}
	
	resultSet.close();
	rs.close();
	sc.close();
	statement.close();
	fis.close();
	connection.close();
	}

}
