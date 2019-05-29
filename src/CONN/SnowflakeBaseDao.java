package CONN;

import java.sql.*;
import java.util.Properties;

import net.snowflake.client.jdbc.SnowflakeDriver;

public abstract class SnowflakeBaseDao {
    private static final String DB_DRIV = "";
    private static final String DB_URL = "jdbc:snowflake://lm27557.eu-central-1.snowflakecomputing.com";
    private static final String DB_USER = "DataProcessingV1B";
    private static final String DB_PASS = "Lemasttifl1";
   
    private static Connection conn;

    public Connection getConnection() { 
    	try {
            if ( conn == null || conn.isClosed()) {
                Class.forName("com.snowflake.client.jdbc.SnowflakeDriver");

                Properties properties = new Properties();
                properties.put("user", DB_USER);
                properties.put("password", DB_PASS);
                properties.put("db", "ovchipkaart");
                properties.put("schema", "public");
                properties.put("warehouse", "compute_wh");

                conn = DriverManager.getConnection(DB_URL, properties);
                conn.setAutoCommit(false);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection Failure");
            e.printStackTrace();
        }
    	return conn;
    }

    public void closeConnection(Connection connection) throws SQLException {
//        connection.close();
    }
}
