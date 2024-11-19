import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Properties_Example_With_Java_Program {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\JAVA_DATABASE_CONNECTIVITY\\LoginDetails.properties");
		Properties properties = new Properties();
		properties.load(fis);
		System.out.println(properties.getProperty("url"));
		System.out.println(properties.getProperty("userName"));
		System.out.println(properties.getProperty("password"));

	}

}
