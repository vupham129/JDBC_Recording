import java.sql.*;

public class ResultSetMetaData_ForColumnInfo {
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM REGIONS");

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // String firstColName = rsmd.getColumnName(1);
            // System.out.println("columnCount = " + columnCount);
            // System.out.println("firstColName = " + firstColName);

            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                //System.out.println("rsmd.getColumnName("+colIndex+") = " + rsmd.getColumnName(colIndex));
            }

            // printing all data in one row
//            rs.next();
//            for (int colIndex = 1; colIndex <= columnCount ; colIndex++) {
//                System.out.println("rs.getString("+colIndex+") = " + rs.getString(colIndex));
//            }
//            System.out.println("----------------------------");
//            rs.next();
//            for (int colIndex = 1; colIndex <= columnCount ; colIndex++) {
//                System.out.println("rs.getString("+colIndex+") = " + rs.getString(colIndex));
//            }
            System.out.println("----------------------------");
            for (int colIndex = 1; colIndex <= columnCount ; colIndex++) {
                System.out.print(rsmd.getColumnName(colIndex)+" \t");
            }
            System.out.println();
            while (rs.next()){
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    System.out.print(rs.getString(colIndex) + " \t");
                }
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println("Error has occurred "+e.getMessage());
        }

















    }
}
