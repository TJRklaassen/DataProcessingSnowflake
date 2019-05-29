package APPLICATION;

import CONN.*;
import POJOs.OVChipkaart;
import POJOs.OVChipkaartSnowflakeDao;
import POJOs.ProductSnowflakeDao;
import POJOs.Reiziger;
import POJOs.ReizigerSnowflakeDao;

import java.sql.*;
import net.snowflake.client.jdbc.SnowflakeDriver;

public class Main {
	private static ProductSnowflakeDao productDao = new ProductSnowflakeDao();
	private static ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
	private static OVChipkaartSnowflakeDao kaartDao = new OVChipkaartSnowflakeDao();
	
    public static void main(String[] args) throws SQLException{
    	System.out.println("Alle reizigers:");
    	printFindAllReizigers();
    	System.out.println("Alle OV-chipkaarten:");
    	printFindAllKaarten();
    	
    	System.out.println("Alle OV-chipkaarten met bijbehorende producten die erop staan:");
    	productDao.findAll();
		System.out.println();
		System.out.println();
		
		System.out.println("Alle producten met OV-chipkaarten die het product hebben:");
		productDao.findAllWithCards();
    }
    
    public static void printFindAllReizigers() throws SQLException {
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
		System.out.println();
	}
    
    private static void printFindAllKaarten() throws SQLException {
		for(OVChipkaart ov : kaartDao.findAll()) {
			Reiziger r = ov.getKaarthouder();
			if(r.getTussenvoegsel() != null) {
				System.out.println(String.format("Kaartnummer: %s, Einddatum: %s, Klasse: %s, Saldo: %s, Naam: %s %s %s",
						ov.getKaartnummer(), ov.getGeldigTot(), ov.getKlasse(), ov.getSaldo(), r.getVoorletters(), r.getTussenvoegsel(), r.getAchternaam()));
			} else {
				System.out.println(String.format("Kaartnummer: %s, Einddatum: %s, Klasse: %s, Saldo: %s, Naam: %s %s",
						ov.getKaartnummer(), ov.getGeldigTot(), ov.getKlasse(), ov.getSaldo(), r.getVoorletters(), r.getAchternaam()));
			}
		}
		System.out.println();
		System.out.println();
	}
}
