import java.sql.*;

public class NavigatingQueryResult {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement() ;// stmt object

            ResultSet rs = stmt.executeQuery("Select * from REGIONS");
            rs.next();
            System.out.println("Region ID on first row is "+ rs.getString(1));
            System.out.println("Region ID on first row is "+ rs.getString("REGION_ID"));
            System.out.println("Region Name on first row is "+ rs.getString(2));
            System.out.println("Region Name on first row is "+ rs.getString("REGION_NAME"));

            rs.next();
            System.out.println("Region ID on second row is "+ rs.getString("REGION_NAME"));
            rs.next();
            System.out.println("Region ID on third row is "+ rs.getString("REGION_NAME"));
            System.out.println("Do we have more data = " + rs.next());
            System.out.println("Region ID on fourth row is "+ rs.getString("REGION_NAME"));
            System.out.println("Do we have more data = " + rs.next());

            //System.out.println("Region Name after last row is "+rs.getString("REGION_NAME"));

            rs.previous();

        }catch (SQLException e){
            System.out.println("Error has occurred "+e.getMessage());
        }













    }
}
