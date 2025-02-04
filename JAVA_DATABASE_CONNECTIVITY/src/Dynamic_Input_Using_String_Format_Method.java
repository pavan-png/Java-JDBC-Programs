import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Dynamic_Input_Using_String_Format_Method {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		String url = "jdbc:Oracle:thin:@localhost:1521:orcl";
		String userName = "practice";
		String password = "1234";
		
		connection = DriverManager.getConnection(url,userName,password);
		statement = connection.createStatement();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter the id of the employee");
		Integer id  = sc.nextInt();
		
		System.out.println("enter the name of the employee");
		String name = sc.next();
		
		System.out.println("enter the department of the employee");
		String department = sc.next();
		
		System.out.println("enter the salary of the employee");
		Integer salary = sc.nextInt();
		
		System.out.println("enter the gender of the employee");
		String gender = sc.next();
		
		System.out.println("enter the age of the employee");
		Integer age = sc.nextInt();
		
		System.out.println("enter the city of the employee");
		String city = sc.next();
		
		String query = String.format("INSERT INTO employee VALUES (%d,'%s','%s',%d,'%s',%d,'%s') ",id,name,department,salary,gender,age,city );
		Integer rowsUpdated = statement.executeUpdate(query);
		
		System.out.println("rows affected : "+rowsUpdated);
		statement.close();
		connection.close();
		
	}

}
