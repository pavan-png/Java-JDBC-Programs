import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class Jdbc_Image_Retrival_Apache_Lib {
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		File f = new File("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		FileInputStream fis = new FileInputStream(f);
		Properties properties = new Properties();
		properties.load(fis);
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("userName"),properties.getProperty("password"));
		preparedStatement = connection.prepareStatement("select * from imageinsert");
		resultSet = preparedStatement.executeQuery();
		Integer id = null;
		String name = null;
		InputStream inputStream = null;
		while(resultSet.next()) {
			id = resultSet.getInt(1);
			name = resultSet.getString(2);
			inputStream = resultSet.getBinaryStream(3);
		}
		File f1 = new File("C:\\Users\\Administrator\\Downloads\\new\\copied.jpg");
		FileOutputStream fos = new FileOutputStream(f1);
	
		IOUtils.copy(inputStream, fos);
		System.out.println(id+"\t"+name+"\t"+f1.getAbsolutePath());
		
		fos.close();
		inputStream.close();
		resultSet.close();
		preparedStatement.close();
		fis.close();
		connection.close();
		

	}

}
