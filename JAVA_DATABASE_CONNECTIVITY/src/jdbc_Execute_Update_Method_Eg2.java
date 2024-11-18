import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Execute_Update_Method_Eg2 {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		String userName = "practice";
		String password = "1234";
		connection = DriverManager.getConnection(url,userName,password);
		String query = "INSERT INTO employee VALUES ( 1019 ,'pavan','IT',99000,'MALE',24,'VIJAYAWADA')";
		statement = connection.createStatement();
		Integer i = statement.executeUpdate(query);
		System.out.println("no of rows affected are "+i);
		statement.close();
		connection.close();
		
	}

}
