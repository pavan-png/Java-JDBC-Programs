import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_CsvFile_Insert_Operation {
	static int id;
	static String name;
	static String address;
	static String url = "jdbc:Text:///C:\\Users\\Administrator\\Downloads\\new";
	static String CSV = "insert into data.csv values (?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);){
			if(sc !=null) {
			System.out.println("enter the id of the bmw owner");
			id = sc.nextInt();
			System.out.println("enter the name of the bmw owner");
			name = sc.next();
			System.out.println("enter the city of the bmw owner");
			address = sc.next();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement = connection.prepareStatement(CSV)){
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2,name);
				preparedStatement.setString(3,address);
				
				int rowsAffected = preparedStatement.executeUpdate();
				if(rowsAffected == 1) {
					System.out.println("row affected");
				}
				else {
					System.out.println("row not affected");
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}

	}

}
