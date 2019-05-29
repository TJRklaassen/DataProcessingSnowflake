package CONN;

import java.sql.*;

public class SnowflakeBaseDao {
    private static final String DB_DRIV = "";
    private static final String DB_URL = "";
    private static final String DB_USER = "DataProcessingV1B";
    private static final String DB_PASS = "Lemasttifl1";
    private static Connection conn;

    public SnowflakeBaseDao() {

        try {
            if ( conn == null) {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        }
        catch (SQLException e) {
            System.out.println("Connection Failure");
            e.printStackTrace();
        }
    }

    public Connection getConnection() { return conn; }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
