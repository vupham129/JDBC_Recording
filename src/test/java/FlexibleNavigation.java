import java.sql.*;

public class FlexibleNavigation {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM REGIONS");

        /*
            ResultSet methods to navigate rows:
            next(), previous(), first(), last(), beforeFirst(), afterLast(), absolute(8)
             */

        /**
         * In order to access data in ResultSet object , we need to use cursor navigation methods :
         *
         * next() -move to next row and return true false according to if we have valid row
         * previous() -move to previous row and return true false according to if we have valid row
         * first() - move to first row from anywhere
         * last() - move to last row from anywhere
         * beforeFirst() - move to before first location from anywhere
         * afterLast() - move to after last location from anywhere
         * absolute(8) - move to any row by using row number , for example 8 in this case
         */
        System.out.println("next method:");
        rs.next() ; // row1
        System.out.println( rs.getString(1) + " | " + rs.getString(2));
        System.out.println("last method:");
        rs.last() ; // row 4
        System.out.println( rs.getString(1) + " | " + rs.getString(2));
        System.out.println("first method:");
        rs.first() ; // row 1
        System.out.println( rs.getString(1) + " | " + rs.getString(2));

        System.out.println("absolute(row number) method");
        rs.absolute(3);
        System.out.println( rs.getString(1) + " | " + rs.getString(2));


    }
}
