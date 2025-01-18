import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_RowSet_Update_Operation {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rs = RowSetProvider.newFactory();
		JdbcRowSet jrs = rs.createJdbcRowSet();
		
		jrs.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl");
		jrs.setUsername("practice");
		jrs.setPassword("1234");
		jrs.setCommand("select id,name,age,address,salary from scrollableapp1");
		jrs.execute();
		
		System.out.println("before updation ");
		while(jrs.next()) {
		 
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		
		jrs.beforeFirst();
		while(jrs.next()) {
			int salary  = jrs.getInt(5);
			if (salary>=5000) {
				int updatedSalary =salary+5000;
				jrs.updateInt(5, updatedSalary);
				jrs.updateRow();
			}
		}
		System.out.println("records updated successfully");
		
		jrs.beforeFirst();
		System.out.println("after updation ");
		while(jrs.next()) {
		 
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4)+"\t"+jrs.getInt(5));
		}
		jrs.close();
	}

}

