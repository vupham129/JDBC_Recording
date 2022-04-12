import java.sql.*;

public class NavigatingResultSet1 {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM REGIONS");

            /*
            ResultSet methods to navigate rows:
            next(), previous(), first(), last(), beforeFirst(), afterLast(), absolute(8)
             */

            rs.first() ;
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));

            rs.last() ;
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));

            rs.previous();
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));

            rs.absolute(2);
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));

            rs.beforeFirst();
            rs.next();
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));

            rs.afterLast();
            rs.previous();
            System.out.println(rs.getString("REGION_ID" +
                    "")+" "+rs.getString("REGION_NAME"));




        }catch (SQLException e){
            System.out.println("Error has occurred "+e.getMessage());
        }










    }
}
