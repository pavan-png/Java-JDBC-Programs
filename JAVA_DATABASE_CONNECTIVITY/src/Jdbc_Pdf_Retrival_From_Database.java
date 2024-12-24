import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class Jdbc_Pdf_Retrival_From_Database {

	public static void main(String[] args) throws IOException, SQLException{
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		ResultSet resultSet = null;
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		preparedStatment = connection.prepareStatement("select * from pdfinsertion where name = ?");
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the name");
		String name = sc.next();
		preparedStatment.setString(1,name);
		String name_1 = null;
		Reader reader = null;
		
		resultSet = preparedStatment.executeQuery();
		while(resultSet.next()) {
			name_1 = resultSet.getString(1);
			reader = resultSet.getCharacterStream(2);
		}
		File f1 = new File("C:\\Users\\Administrator\\Downloads\\new\\resume.pdf");
		FileWriter fw = new FileWriter(f1);
		IOUtils.copy(reader, fw);
		System.out.println(name + "\t"+f1.getAbsolutePath());
		fw.close();
		reader.close();
		resultSet.close();
		sc.close();
		preparedStatment.close();
		connection.close();
		fis.close();
		}

}
