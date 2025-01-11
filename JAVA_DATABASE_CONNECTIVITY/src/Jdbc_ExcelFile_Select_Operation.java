import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_ExcelFile_Select_Operation {

	public static void main(String[] args) {
		
		
		String url = "jdbc:Excel:///C:\\Users\\Administrator\\Downloads";
		// through jdbc we have to communicate so jdbc, the database name is excel and location
		
		final String EXCEL_FILE = "insert into data.sample values (?,?,?)";
		//workbook.<sheetname> represents the table name
		
		try(Scanner sc = new Scanner (System.in)){
			if(sc!=null) {
				System.out.println("enter the id of the ceo");
				int id = sc.nextInt();
				System.out.println("enter the name of the ceo");
				String name = sc.next();
				
			}
		}
		try(Connection connection = DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement = connection.prepareStatement(EXCEL_FILE)) {
				
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
