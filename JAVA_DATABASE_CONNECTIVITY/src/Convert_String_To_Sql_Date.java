import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class Convert_String_To_Sql_Date {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the date in format :  dd-mm-yyyy");
		String scDate = sc.next();
		
		// dd-MM-yyyy   Month should be represented with capital letters, date and year in small letters 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = 	sdf.parse(scDate);
		long date = uDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(date);
		System.out.println("scanner date is " +scDate);
		System.out.println("util date is "+ uDate);
		System.out.println("sql date is "+ sqlDate);
		sc.close();
		}

}
