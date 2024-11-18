import java.sql.*;

public class Jdbc_Example2 {

	public static void main(String[] args)throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		String username = "practice";
		String password = "1234";
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		connection = DriverManager.getConnection(url, username, password);
		System.out.println("connection succeded");
	    statement = connection.createStatement();
	    String query = "SELECT * FROM employee";
		resultSet = statement.executeQuery(query);
		 System.out.println("Id\tName\tDepartment\tSalary\tGender\tAge");
		while(resultSet.next()) {
			 Integer id = resultSet.getInt("Id");  // we can give column name instead of column number as parameter. give the column number as string parameter
             String name = resultSet.getString("Name");
             String dept = resultSet.getString("Department");
             Integer sal = resultSet.getInt("Salary");
             String gen = resultSet.getString("Gender");
             Integer age = resultSet.getInt("Age");
             System.out.println(id +"\t"+name +"\t" +dept+"\t"+sal+"\t"+gen+"\t"+age);
			
		}
		
		resultSet.close();
		statement.close();
		connection.close();
		

	}

}
