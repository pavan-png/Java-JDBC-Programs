import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_CsvFile_Select_Operation {
	
	private static String url = "jdbc:Text:///C:\\Users\\Administrator\\Downloads\\new";
	
	// Csv file only will represent the table name
	public static void main(String[] args) {
		String EXCEL_CSV = "select * from data.csv";
		try(Connection connection = DriverManager.getConnection(url)){
			try(PreparedStatement preparedStatement = connection.prepareStatement(EXCEL_CSV)){
				try(ResultSet resultSet = preparedStatement.executeQuery()){
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3));
					}
				}
				
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
