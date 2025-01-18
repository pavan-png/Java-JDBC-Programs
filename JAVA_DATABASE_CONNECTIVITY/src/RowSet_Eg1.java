import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class RowSet_Eg1 {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory rs = RowSetProvider.newFactory();
		JdbcRowSet jrs = rs.createJdbcRowSet();
		CachedRowSet crs = rs.createCachedRowSet();
		WebRowSet wrs = rs.createWebRowSet();
		JoinRowSet jnrs = rs.createJoinRowSet();
		FilteredRowSet frs = rs.createFilteredRowSet();
		
		System.out.println(jrs.getClass().getName());
		System.out.println(crs.getClass().getName());
		System.out.println(wrs.getClass().getName());
		System.out.println(jnrs.getClass().getName());
		System.out.println(frs.getClass().getName());
		
		

	}

}
