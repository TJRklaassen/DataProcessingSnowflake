package CONN;

import java.sql.*;
import net.snowflake.client.jdbc.SnowflakeDriver;

public abstract class SnowflakeBaseDao {
    private static final String DB_DRIV = "";
    private static final String DB_URL = "jdbc:snowflake://lm27557.eu-central-1.snowflakecomputing.com/?";
    private static final String DB_USER = "DataProcessingV1B";
    private static final String DB_PASS = "Lemasttifl1";
    private static final String CONNECTION_STRING = DB_URL + "&user=" + DB_USER
            + "&password=" + DB_PASS + "&warehouse=compute_wh&db=ovchipkaart&schema=public";
    private static Connection conn;

    public SnowflakeBaseDao() {

        try {
            if ( conn == null) {
                conn = DriverManager.getConnection(CONNECTION_STRING);
                conn.setAutoCommit(false);
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
