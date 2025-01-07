import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc_Transaction_Savepoint_Rollback {

	public static void main(String[] args) throws IOException, SQLException {
		Connection connection = null;
		FileInputStream fis = null;
		Statement statement = null;
		
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\Java-JDBC-Programs\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"), properties.getProperty("password"));
		statement = connection.createStatement();
		
		System.out.println("transaction begins");
		connection.setAutoCommit(false);
		statement.executeUpdate("insert into transactionsaveroll values ('pavan','jsp')");
		statement.executeUpdate("insert into transactionsaveroll values ('cbn','tdp')");
		Savepoint sp = connection.setSavepoint();
		statement.executeUpdate("insert into transactionsaveroll values ('modi' , 'bjp')");
		System.out.println("something went wrong, last operation is not successfull");
		connection.rollback(sp);
		System.out.println("operations are rolledback to savepoint");
		connection.commit();
		System.out.println("transaction done");
		
		statement.close();
		fis.close();
		connection.close();
	}

}
