package APPLICATION;

import java.sql.Date;
import java.sql.SQLException;

import POJOs.Reiziger;
import POJOs.ReizigerSnowflakeDao;

/**
 * Deze applicatie zet de gegevens terug naar hoe het eerst was.
 * Dit zodat we opnieuw kunnen testen.
 */
public class DataRollback {
	private static ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
	
	public static void main(String[] args) throws SQLException{	
		Reiziger r1 = new Reiziger(1, "G", "van", "Rijn", Date.valueOf("2002-09-17"));
		Reiziger r2 = new Reiziger(6, "H", null, "Jansen", Date.valueOf("1999-03-16"));
		Reiziger r3 = new Reiziger(2, "B", "van", "Rijn", Date.valueOf("2002-10-22"));
		
		reizigerDao.update(r1);
		reizigerDao.delete(r2);
		reizigerDao.save(r3);
	}
}
