import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_ExcelFile_Select_Operation {

	private static final String query = "select * from data.sample";
	public static void main(String[] args) {
		String url = "jdbc:Excel:///C:\\Users\\mohan\\Downloads\\Documents";
			try(Connection connection = DriverManager.getConnection(url)){
				try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
					try(ResultSet resultSet = preparedStatement.executeQuery()){
						while(resultSet.next()) {
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
