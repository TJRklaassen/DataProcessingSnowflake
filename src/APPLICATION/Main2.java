package APPLICATION;

import java.sql.Date;
import java.sql.SQLException;

import POJOs.Reiziger;
import POJOs.ReizigerSnowflakeDao;

public class Main2 {
	private static ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
	
	public static void main(String[] args) throws SQLException{
		System.out.println("Alle reizigers voor insert, update en delete statements:");
		printFindAllReizigers();
		
		Reiziger r1 = new Reiziger(1, "G", "van der", "Kooij", Date.valueOf("2002-07-13"));
		Reiziger r2 = new Reiziger(6, "H", null, "Jansen", Date.valueOf("1999-03-16"));
		Reiziger r3 = new Reiziger(2, "B", "van", "Rijn", Date.valueOf("2002-10-22"));
		
		reizigerDao.update(r1);
		reizigerDao.save(r2);
		reizigerDao.delete(r3);
		
		System.out.println("Alle reizigers na insert, update en delete statements:");
		printFindAllReizigers();
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
}
