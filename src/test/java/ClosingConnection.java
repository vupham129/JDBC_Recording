import java.sql.*;

public class ClosingConnection {

    public static void main(String[] args) throws SQLException{
        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";

//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;

        // try with resources -- only work with auto closable resources
        try( Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery("SELECT * FROM REGIONS") )
        {
            rs.next() ;
            System.out.println(rs.getString(2));

        }catch (SQLException e){
            System.out.println("ERROR HAS OCCURRED "+ e.getMessage());
        }



        /*
        try{
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT * FROM REGIONS");

            System.out.println("ALL SUCCESSFUL");


        }catch (SQLException e){
            System.out.println("ERROR HAS OCCURRED "+ e.getMessage());
        }finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                System.out.println("ERROR HAS OCCURRED "+ e.getMessage());
            }
        }

         */










    }







}
