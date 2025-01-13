import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.PooledConnection;

import oracle.jdbc.pool.*;


public class Connection_Pool {

	public static void main(String[] args) throws SQLException {
		//oracle 
		OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
		
		dataSource.setURL("jdbc:Oracle:thin:@localhost:1521:orcl");
		dataSource.setUser("practice");
		dataSource.setPassword("1234");
		
		PooledConnection pc = dataSource.getPooledConnection();
		Connection connection = pc.getConnection();
		Statement statement = connection.createStatement();
		String query = "select * from products";
		ResultSet resultSet = statement.executeQuery(query);
		System.out.println("pid\tpname\tprice\tqty");
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4));
		}
		resultSet.close();
		statement.close();
		connection.close();
		

	}

}
