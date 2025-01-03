
public class Jdbc_Date {

	public static void main(String[] args) {
		// used to store both date and time information
		java.util.Date uDate = new java.util.Date();
		System.out.println(uDate);
		
		
		long date = uDate.getTime();
		System.out.println(date);
		
		//used to store date information
		java.sql.Date sDate = new java.sql.Date(date);
		System.out.println(sDate);
		
		java.sql.Time sTime = new java.sql.Time(date);
		System.out.println(sTime);
		 
		java.sql.Timestamp stimeStamp = new java.sql.Timestamp(date);
		System.out.println(stimeStamp);		

	}

}
