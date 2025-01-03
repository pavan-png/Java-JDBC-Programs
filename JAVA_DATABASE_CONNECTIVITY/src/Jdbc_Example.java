import java.sql.*;

public class Jdbc_Example{
    public static void main(String[] args) {
       /*
       these are local variables they should be intialized before their use since they are of reference type
       null is assigned to it. this step will help full during the closing the resources
        */
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
           /* Class.forName("oracle.jdbc.driver.OracleDriver");
            * for jdbc 4.x the loading and registering the driver is automatic, so we can skip this step. 
            */
        	
            System.out.println("driver loaded successfully");
            String user_Name = "practice";
            String password = "1234";

            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            // jdbc:oracle:thin:@<hostname>:<port>:<sid>

            connection =  DriverManager.getConnection(url,user_Name,password);

            System.out.println("the implementation class for Connection is "+connection.getClass().getName());

            String qeury = "SELECT * FROM employee";
            statement =  connection.createStatement();
            System.out.println("the implementation class for Statement is "+statement.getClass().getName());
            resultSet = statement.executeQuery(qeury);
            System.out.println(" the implementation class name for ResultSet is "+resultSet.getClass().getName());
            System.out.println("Id\tName\tDepartment\tSalary\tGender\tAge");

            while (resultSet.next()){
                Integer id = resultSet.getInt(1);  // we can give column name instead of column number as parameter. give the column number as string parameter
                String name = resultSet.getString(2);
                String dept = resultSet.getString(3);
                Integer sal = resultSet.getInt(4);
                String gen = resultSet.getString(5);
                Integer age = resultSet.getInt(6);
                System.out.println(id +"\t"+name +"\t" +dept+"\t"+sal+"\t"+gen+"\t"+age);
            }


        }
        /* catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        */
        catch (SQLException s){
            s.printStackTrace();
            System.out.println("login error");
        }
       
           finally {
        	   try {
        		   resultSet.close();
        		   statement.close();
        		   connection.close();
        		   System.out.println("connection closed");
        	   }
        	  catch(Exception e) {
        		  e.printStackTrace();
        	  }
        	   
           }
            
            
            
     }
}
