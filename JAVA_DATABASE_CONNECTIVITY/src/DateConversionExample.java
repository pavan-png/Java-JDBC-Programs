public class DateConversionExample {

    public static void main(String[] args) {

        // java.util.Date represents both date and time
        java.util.Date utilDate = new java.util.Date();
        System.out.println("util.Date    : " + utilDate);

        // Convert util.Date to milliseconds
        long millis = utilDate.getTime();
        System.out.println("Milliseconds : " + millis);

        // java.sql.Date represents only the date (no time)
        java.sql.Date sqlDate = new java.sql.Date(millis);
        System.out.println("sql.Date     : " + sqlDate);

        // java.sql.Time represents only the time
        java.sql.Time sqlTime = new java.sql.Time(millis);
        System.out.println("sql.Time     : " + sqlTime);

        // java.sql.Timestamp represents date + time + nanoseconds
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(millis);
        System.out.println("Timestamp    : " + sqlTimestamp);
    }
}
