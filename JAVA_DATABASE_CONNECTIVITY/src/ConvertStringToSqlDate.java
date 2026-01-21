import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ConvertStringToSqlDate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the date in format (dd-MM-yyyy): ");
            String inputDate = scanner.next();

            // Define expected date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false); // Ensures strict date validation

            // Convert String → java.util.Date
            java.util.Date utilDate = sdf.parse(inputDate);

            // Convert util.Date → milliseconds
            long millis = utilDate.getTime();

            // Convert milliseconds → java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(millis);

            // Output results
            System.out.println("Input String Date : " + inputDate);
            System.out.println("java.util.Date    : " + utilDate);
            System.out.println("java.sql.Date     : " + sqlDate);

        } catch (ParseException e) {
            System.out.println("Invalid date format! Please enter in dd-MM-yyyy format.");
        } finally {
            scanner.close();
        }
    }
}
