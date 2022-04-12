import java.sql.*;

public class LoopingBackWard {
    public static void main(String[] args) throws SQLException {

        // print all Employees Employee_ID and First_Name backward
        // SELECT * FROM EMPLOYEES (do not sort this)
        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");

        // use afterLast() method to go to after last location
        // use previous() method to move backward
        // and check whether we have valid previous row or not
        // stop if there is no more valid row
        rs.afterLast();
        while(rs.previous()){
            System.out.println(rs.getString("EMPLOYEE_ID") + " | " + rs.getString("FIRST_NAME"));
        }









    }
}
