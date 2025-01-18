import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_RowSet_Delete_Operation {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rf.createJdbcRowSet();
		jrs.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl");
		jrs.setUsername("practice");
		jrs.setPassword("1234");
		jrs.setCommand("select id , name , age , address , salary from scrollableapp1");
		jrs.execute();
		System.out.println("before deletion");
		while(jrs.next()) {
		 
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		jrs.beforeFirst();
		while(jrs.next()) {
			int salary = jrs.getInt(5);
			if(salary<=5000) {
				jrs.deleteRow();
			}
		}
		System.out.println("record deleted successfully");
		jrs.beforeFirst();
		System.out.println("after deletion");
		while(jrs.next()) {
		 
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
	}

}
