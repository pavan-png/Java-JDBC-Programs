
import java.sql.*;

public class jdbc_Execute_Update_Method {

	public static void main(String[] args) throws SQLException{
		Connection connection = null;
		Statement statement = null;
		String username = "practice";
		String password = "1234";
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		connection = DriverManager.getConnection(url,username,password);
		System.out.println("connected with database");
		statement = connection.createStatement();
		String query = "DELETE FROM Employee WHERE Department = 'IT' ";
		Integer rowsAffected = statement.executeUpdate(query);
		System.out.println("the rows affected are : "+rowsAffected);
		statement.close();
		connection.close();
		System.out.println("resources closed");
		
	}

}
