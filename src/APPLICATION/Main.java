package APPLICATION;

import CONN.*;
import POJOs.ProductSnowflakeDao;

import java.sql.*;
import net.snowflake.client.jdbc.SnowflakeDriver;

public class Main {
	private static ProductSnowflakeDao productDao = new ProductSnowflakeDao();
	
    public static void main(String[] args) throws SQLException{
    	productDao.findAll();
		System.out.println();
		productDao.findAllWithCards();
    }
}
