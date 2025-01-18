import java.sql.SQLException;
import java.util.Scanner;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Cached_RowSet_Insert_Operation {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rf = RowSetProvider.newFactory();
	    CachedRowSet crs = rf.createCachedRowSet();
		crs.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl?relaxAutoCommit=false");
		crs.setUsername("practice");
		crs.setPassword("1234");
		crs.setCommand("select id,name,age,address,salary from scrollableapp1");
		crs.execute();
		
		
		
		  while(crs.next()) {
		 
			System.out.println(crs.getInt(1)+"\t"+crs.getString(2)+"\t"+crs.getInt(3)+"\t"+crs.getString(4)+"\t"+crs.getInt(5));
		}
		
		
		Scanner sc = new Scanner(System.in);
		
			/*
			 * in cached rowset we cannot insert multiple rows at a time
			 * in oracle auto increment feature is not supported, it should be done manually with the help of triggers or
			 * sequence, to resolve this problem every time we insert a new row we should use jrs.moveToInsertRow();
			 * keep the primary key as well. 
			 *   but auto increment is  supported in mysql. we can use jrs.moveToInsertRow(); once to insert multiple rows 
			 *   at a time. 
			 */
		
			System.out.println("enter the id of the student");
			int id  = sc.nextInt();
			System.out.println("enter the name of the student");
			String name = sc.next();
			System.out.println("enter the age of the student");
			int age = sc.nextInt();
			System.out.println("enter the city of the student");
			String address = sc.next();
			System.out.println("enter the salary of the student");
			int salary = sc.nextInt();
			crs.moveToInsertRow();
			
			crs.updateInt(1,id);
			crs.updateString(2, name);
			crs.updateInt( 3,age);
			crs.updateString(4, address);
			crs.updateInt(5, salary);
			
			crs.insertRow();
			
			System.out.println("row insert sucessfully");
			
			crs.moveToCurrentRow();
			crs.acceptChanges();
			
			sc.close();
			crs.close();
			
		
		

	}

}
