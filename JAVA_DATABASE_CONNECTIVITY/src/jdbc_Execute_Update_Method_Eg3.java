import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_Execute_Update_Method_Eg3 {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		String userName = "practice";
		String password = "1234";
		connection = DriverManager.getConnection(url,userName,password);
		String query = "UPDATE employee SET salary = 200000 WHERE name = 'pavan' ";
		statement = connection.createStatement();
		Integer rowsAffected = statement.executeUpdate(query);
		System.out.println("no of rows affected are : "+rowsAffected);
		statement.close();
		connection.close();
		
	}

}
