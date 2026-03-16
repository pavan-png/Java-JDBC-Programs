import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Jdbc_Image_Retrival {

    public static void main(String[] args) {

        String propertiesPath = "./LoginDetails.properties";
        String outputImagePath = "./copied.jpg";

        Properties properties = new Properties();

        try (
                FileInputStream fis = new FileInputStream(propertiesPath)
        ) {

            // Load DB properties
            properties.load(fis);

            try (
                    Connection connection = DriverManager.getConnection(
                            properties.getProperty("url"),
                            properties.getProperty("userName"),
                            properties.getProperty("password"));

                    PreparedStatement preparedStatement =
                            connection.prepareStatement("SELECT * FROM imageinsert");

                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {

                int id = 0;
                String name = null;
                InputStream imageStream = null;

                if (resultSet.next()) {

                    id = resultSet.getInt(1);
                    name = resultSet.getString(2);
                    imageStream = resultSet.getBinaryStream(3);

                    try (FileOutputStream fos = new FileOutputStream(outputImagePath)) {

                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = imageStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }

                    System.out.println("ID   : " + id);
                    System.out.println("Name : " + name);
                    System.out.println("Image saved at : " + outputImagePath);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}