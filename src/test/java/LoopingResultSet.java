import java.sql.*;

public class LoopingResultSet {
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

           // next(): move the cursor to next line and return boolean according to whether we have next row

            while(rs.next()){
                System.out.println(rs.getString(1)+" | "+rs.getString(2));
            }

            rs.beforeFirst();

            while(rs.next()){
                System.out.println("row number is "+rs.getRow());
                System.out.println(rs.getString(1)+" | "+rs.getString(2));
            }

            //getting row count --> move cursor to the last row and get the row number
            rs.last();
            System.out.println("Row count is "+rs.getRow());




        }catch (SQLException e){
            System.out.println("Error has occurred "+e.getMessage());
        }








    }
}
