import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Cached_RowSet_Select_Operation {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:orcl","practice","1234");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select id , name , age , address , salary from scrollableapp1");
		
		RowSetFactory rf = RowSetProvider.newFactory();
		CachedRowSet crs = rf.createCachedRowSet();
		crs.populate(resultSet);
		
		connection.close();
		
		while(crs.next()) {
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
	
	}

}
