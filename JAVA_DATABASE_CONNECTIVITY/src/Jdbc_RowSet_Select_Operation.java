import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_RowSet_Select_Operation {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rs = RowSetProvider.newFactory();
		JdbcRowSet jrs = rs.createJdbcRowSet();
		//JdbcRowSet is not serializable same as resultSet but it is updatable and scrollable
		
		jrs.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl");
		jrs.setUsername("practice");
		jrs.setPassword("1234");
		jrs.setCommand("select * from scrollableapp");
		jrs.execute();
		
		while(jrs.next()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		}
		System.out.println();
		
		while(jrs.previous()) {
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		}
		
		System.out.println();
		jrs.absolute(3);
		System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		

	}

}
