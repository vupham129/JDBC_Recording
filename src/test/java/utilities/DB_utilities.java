package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_utilities {

    private static Connection conn;
    private static Statement stmt;
   private static ResultSet rs;
   private static ResultSetMetaData rsmd;
    /**
     * a static method to create connection
     * with valid username and password
     */
    public static void createConnection(){
        String connectionStr = "jdbc:oracle:thin:@54.197.9.248:1521:XE";
        String username = "hr";
        String password ="hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("Connection successful");

        } catch (SQLException e) {

            System.out.println("Connection has failed "+e.getMessage());
        }
    }

    /**
     * a static method to get the ResultSet object
     * with valid connection by executing query
     * @param query
     * @return ResultSet object that contain the result just in cases needed outside the class
     */
    public static void runQuery(String query){
        createConnection();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error while getting result set "+e.getMessage());
        }

    }

    /**
     * cleaning up the methods
     */
    public static void destroy(){

            try {
                if(rs != null){
                    rs.close();
                }

                if(stmt != null){
                    stmt.close();
                }

                if(conn != null){
                    conn.close();
                }



            } catch (SQLException e) {

                e.printStackTrace();
           }


    }

    /**
     * get the row count of the resultSet
     * @return the row number of the resulSet given
     */
    public static int getRowCount(){
        int rowCount = 0;

        try{
            rs.last();
            rowCount = rs.getRow() ;
            rs.beforeFirst();
        }catch (SQLException e){
            System.out.println("Error while getting row count "+e.getMessage());
        }
        return rowCount;
    }

    /**
     * get the column count of the resultSet
     * @return the column number of the resulSet given
     */
    public static int getColumnCount(){
        int colCount = 0;

        try {
            rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("Error while getting column count "+e.getMessage());
        }
        return colCount;

    }

    /**
     * Create a method that return all column names as list
     * @return List<String>
     */
    public static List<String> getColumnNames(){
        List<String> colNamesList = new ArrayList<>();

        try {
            rsmd = rs.getMetaData() ;
            for (int colNum = 1; colNum <= rsmd.getColumnCount(); colNum++) {
                String colName = rsmd.getColumnName(colNum);
                colNamesList.add(colName);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting column names "+e.getMessage());
        }

        return colNamesList ;

    }

    // getting the entire row as List<String>

    /**
     *
     * @param rowNum the row number you want the list from
     * @return List of String that contains the row data
     */

    public static List<String> getRowDataList(int rowNum){

        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum);

            for (int colNum = 1; colNum <= getColumnCount() ; colNum++) {
                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue);
            }

        } catch (SQLException e) {
            System.out.println("Error while getting row data list");
        }
        return rowDataList;
    }

    /**
     * Getting single column cell value at certain row
     * @param rowNum row number we want to get data from
     * @param columnIndex column index we want to get the data from
     * @return the data in String
     */
    public static String getColumnDataAtRow(int rowNum, int columnIndex){
        String result ="";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnIndex);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting column data at row "+ e.getMessage());
        }


        return result;

    }

    /**
     * Getting single column cell value at certain row
     * @param rowNum row number we want to get data from
     * @param columnName column name we want to get the data from
     * @return the data at that row with that column name
     */

    public static String getColumnDataAtRow(int rowNum, String columnName){
        String result ="";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnName);

            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error while getting column data at row "+ e.getMessage());
        }


        return result;

    }

    /**
     *
     * @param columnIndex index the column you want to get a list out of
     * @throws List of string that contains entire column data from 1st row to last wor
     */

    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataList = new ArrayList<>();

        try {
            rs.beforeFirst();
            while(rs.next()){
                String cellValue = rs.getString(columnIndex);
                columnDataList.add(cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("Error while getting column data as list "+e.getMessage());
        }

        return columnDataList;
    }

    /**
     *
     * @param columnName  the column name you want to get a list out of
     * @throws List of string that contains entire column data from 1st row to last wor
     */

    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataList = new ArrayList<>();

        try {
            rs.beforeFirst();
            while(rs.next()){
                String cellValue = rs.getString(columnName);
                columnDataList.add(cellValue);
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("Error while getting column data as list "+e.getMessage());
        }

        return columnDataList;
    }


    /**
     * a method to display all the data in the result set
     */
    public static void displayAllData(){
        try {
            rs.beforeFirst();
            while (rs.next()){
                for (int colNum = 1; colNum <= getColumnCount() ; colNum++) {
                    System.out.print(  rs.getString(colNum) +"\t" );
                }
                System.out.println();
            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("Error while displaying all data "+e.getMessage());
        }
    }

    /**
     * We want to store certain row data as s Map<String, String>
     * for example:
     * give me number 3rd row --> Map<String, String> {region_id= 3, region_name= Asia}
     * @param rowNum
     * @return Map object that contains column names as a key and cell as value
     */
    public static Map<String, String> getRowMap(int rowNum){

        Map<String, String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);
            rsmd = rs.getMetaData() ;
            for (int colNum = 1; colNum <= getColumnCount() ; colNum++) {
                String colName = rsmd.getColumnLabel(colNum);
                String cellValue = rs.getString(colNum);
                rowMap.put(colName, cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("Error at row map function "+e.getMessage());
        }

        return rowMap;

    }

    /**
     * We want to store entire data as a List< Map<String, String> >
     * @param
     * @return List<Map<String, String>>  List of Map object that contains column names as a key and cell as value
     */
    public static List<Map<String, String>> getAllDataAsListOfMap(){

        List<Map<String, String>> rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount() ; rowNum++) {
            rowMapList.add(getRowMap(rowNum));
        }
        return rowMapList;

    }

    /**
     * getting the first row column data
     * @return
     */
    public static String getFirstData(){
        return getColumnDataAtRow(1, 1);


    }


    public static void main(String[] args) throws SQLException {

        //createConnection();
        runQuery("SELECT * FROM EMPLOYEES");

        rs.next();
        System.out.println(rs.getString(1));


        // System.out.println( getRowCount() );
       // System.out.println(getColumnNames());
       // System.out.println(getRowDataList(3));
       // System.out.println( "3rd row 2nd column: "+getColumnDataAtRow(3, 2));
       // System.out.println("3rd row REGION_NAME column: " + getColumnDataAtRow(3, "REGION_NAME"));
       // System.out.println("1st column as list " + getColumnDataAsList(1));
       // System.out.println("Column name REGION_ID list "+getColumnDataAsList("REGION_ID"));
        //displayAllData();
        System.out.println(getRowMap(2)); //{REGION_ID=2, REGION_NAME=Americas}

        Map<String, String> thirdRowMap = getRowMap(3);

        System.out.println("get the last name: " + thirdRowMap.get("LAST_NAME") );

        //System.out.println(getAllDataAsListOfMap());
        System.out.println(getFirstData());

        destroy();
    }
}
