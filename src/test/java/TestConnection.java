import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    /**
     * host: 54.197.9.248
     * port: 1521
     * SID: XE
     * user: hr
     * pass: hr
     * jdbc url AKA connection String
     * syntax: jdbc : vendorname : driverType @Yourhost:Port:SID
     *
     * jdbc: oracle:thin:@54.197.9.248:1521:XE
     *
     * @param args
     */
    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection was successful");
        } catch (SQLException e) {
            System.out.println("Connection has failed "+e.getMessage());
        }


    }



}
