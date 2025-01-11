import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_ExcelFile_Insert_Operation {

	static int id ;
	static String name;
	static String city ;
	
	private static final String EXCEL_FILE = "insert into data.sample values (?,?,?)";
	//workbook.<sheetname> represents the table name
	public static void main(String[] args) {
		
		String url = "jdbc:Excel:///C:\\Users\\mohan\\Downloads\\Documents";
		// through jdbc we have to communicate so jdbc, the database name is excel and location
		
		
		try(Scanner sc = new Scanner (System.in)){
			if(sc!=null) {
				System.out.println("enter the id of the ceo");
				 id = sc.nextInt();
				System.out.println("enter the name of the ceo");
				name = sc.next();
				System.out.println("enter the city of the ceo");
				city = sc.next();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement = connection.prepareStatement(EXCEL_FILE)) {
				if(preparedStatement!=null) {
					preparedStatement.setInt(1,id);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, city);
					int count = preparedStatement.executeUpdate();
					
					if(count == 1) {
						System.out.println("row updated");
					}
					else {
						System.out.println("row not affected");
					}
				}
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		

	}

}
