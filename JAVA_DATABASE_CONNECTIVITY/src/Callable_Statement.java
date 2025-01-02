/*
 * The connection URL for the mysql database is jdbc:mysql://localhost:3306/sonoo where jdbc is the API, mysql is the database, localhost is the server name on which mysql is running, we may also use IP address, 3306 is the port number and sonoo is the database name. We may use any database, in such case, we need to replace the sonoo with our database name.
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Callable_Statement {
	private static final String storedProcedureCall = "{CALL P_GET_PRODUCT_DETAILS_BY_ID(?,?,?,?)}";
	/*
	 * private since the query should be limited only to this class
	 * 
	 */

	public static void main(String[] args) throws SQLException {
	Connection connection = null;
	CallableStatement cstmt = null;
	Scanner sc =null;
	connection	= DriverManager.getConnection("jdbc:mysql://localhost:3306/pavan-workspace","root","Ponny@017");
	
	// keep the query in the curly braces
	cstmt = connection.prepareCall(storedProcedureCall);
	
	System.out.println("enter the product id : ");
	sc = new Scanner(System.in);
	Integer id = sc.nextInt();
	
	cstmt.setInt(1, id);
	
	cstmt.registerOutParameter(2,Types.VARCHAR);
	cstmt.registerOutParameter(3, Types.INTEGER);
	cstmt.registerOutParameter(4,Types.INTEGER);
	
	cstmt.execute();
	
	System.out.println("the product name is "+cstmt.getString(2));
	System.out.println("the product price is "+cstmt.getInt(3));
	System.out.println("the product price is "+cstmt.getInt(4));
	
	sc.close();
	cstmt.close();
	connection.close();
	
	
	}

}
