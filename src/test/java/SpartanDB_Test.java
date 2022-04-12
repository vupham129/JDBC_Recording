import java.sql.*;

public class SpartanDB_Test {
    /**
     * your EC2 server already contains a practice app called Spartan
     * it has oracle database with single table
     * you can also check out the UI
     * by navigating to URL yourEC2SERverIP:8000
     * IP is the same as HR database
     * username: SP
     * password: SP
     */
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "SP";
        String password ="SP";

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM SPARTANS");

        // This table has SPARTAN_ID, NAME, GENDER CREATED_AT UPDATED_AD
        while(rs.next()) {
            System.out.println(rs.getString(1) + " | " + rs.getString(2) +
                    " | " + rs.getString(3) + " | " + rs.getString(4) + " | " + rs.getString(5));

        }


    }
}
