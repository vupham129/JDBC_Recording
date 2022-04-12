import java.sql.*;

public class LoopingThruResultSet {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM REGIONS");

            // keep looping to next row until there is no more rows when rs.next() return false
            // rs.next() move the pointer to next row and return true if there is valid next row,
            // and false if there is no valid next row
            // this will print all 4 rows REGION_NAME
            while(rs.next()){
                System.out.println("rs.getString(\"REGION_NAME\") = " + rs.getString("REGION_NAME"));
            }

            // where is your pointer right now? --> after last location
            // below line will throw exception because we are at afterlast location
            System.out.println("rs.getString(\"REGION_NAME\") = " + rs.getString("REGION_NAME"));


        } catch (SQLException e) {
            System.out.println("Exception occurred "+e.getMessage());
        }


    }
}
