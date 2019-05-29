package APPLICATION;

import CONN.*;
import POJOs.ProductSnowflakeDao;
import POJOs.Reiziger;
import POJOs.ReizigerSnowflakeDao;

import java.sql.*;
import net.snowflake.client.jdbc.SnowflakeDriver;

public class Main {
	private static ProductSnowflakeDao productDao = new ProductSnowflakeDao();
	private static ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
	
    public static void main(String[] args) throws SQLException{
    	printFindAll();
    	
    	productDao.findAll();
		System.out.println();
		productDao.findAllWithCards();
    }
    
    public static void printFindAll() throws SQLException {
		for(Reiziger r : reizigerDao.findAll()) {
			if(r.getTussenvoegsel() == null) {
				System.out.println(String.format("%s, %s %s, %s",
						r.getReizigerID(), r.getVoorletters(), r.getAchternaam(), r.getGbdatum()));
			} else {
				System.out.println(String.format("%s, %s %s %s, %s",
						r.getReizigerID(), r.getVoorletters(), r.getTussenvoegsel(), r.getAchternaam(), r.getGbdatum()));
			}
		}
		System.out.println();
	}
}
