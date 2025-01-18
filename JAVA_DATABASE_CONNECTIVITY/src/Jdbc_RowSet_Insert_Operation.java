import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_RowSet_Insert_Operation {

	public static void main(String[] args) throws SQLException {
		RowSetFactory rf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rf.createJdbcRowSet();
		jrs.setUrl("jdbc:Oracle:thin:@localhost:1521:orcl");
		jrs.setUsername("practice");
		jrs.setPassword("1234");
		jrs.setCommand("select id,name,age,address from scrollableapp1");
		jrs.execute();
		
		
		  while(jrs.next()) {
		 
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		}
		
		
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {

			jrs.moveToInsertRow();
			/*
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
			
			jrs.updateInt(1,id);
			jrs.updateString(2, name);
			jrs.updateInt( 3,age);
			jrs.updateString(4, address);
			
			jrs.insertRow();
			
			System.out.println("row insert sucessfully");
			
			System.out.println("do you want to insert one row y/n");
			String option = sc.next();
			
			if(option.equalsIgnoreCase("n")) {
				break;
			}
		}
		
		sc.close();
		jrs.close();
		

	}

}
