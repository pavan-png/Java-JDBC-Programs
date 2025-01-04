import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Callable_Statement_Eg2 {

	public static void main(String[] args) throws SQLException {
		
		//mysql
		Connection connection = null;
		CallableStatement cstmt = null;
		String stored_Procedure = null;
		Scanner sc = null;
		String product1 = null;
		String product2 = null;
		ResultSet resultSet;
		Boolean flag = false;
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pavan-workspace","root","Ponny@017");
		
		stored_Procedure = "{CALL `P_GET_PRODUCT_DETAILS_BY_NAME` (?,?)}";
		cstmt = connection.prepareCall(stored_Procedure);
		
		sc = new Scanner(System.in);
		System.out.println("enter the name of the product 1");
		product1 = sc.next(); 
		System.out.println("enter the name of the product 2");
		product2 = sc.next();
		
		cstmt.setString(1,product1);
		cstmt.setString(2, product2);
		
		cstmt.execute();
		
		resultSet = cstmt.getResultSet();
		if(resultSet!=null) { 
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4));
				flag = true;
			}
		}
		if(flag == true) {
			System.out.println("record is available and printed");
		}
		else {
			System.out.println("record is not available");
		}
		
		resultSet.close();
		sc.close();
		cstmt.close();
		connection.close();
		

	}
		

}
