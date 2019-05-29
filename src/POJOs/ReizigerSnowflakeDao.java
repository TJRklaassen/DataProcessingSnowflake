package POJOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import CONN.SnowflakeBaseDao;

public class ReizigerSnowflakeDao extends SnowflakeBaseDao implements ReizigerDao {
	
	public ArrayList<Reiziger> findAll() throws SQLException {		
		OVChipkaartSnowflakeDao kaartDao = new OVChipkaartSnowflakeDao();
		
		Connection conn = this.getConnection();
		Statement stmt = conn.createStatement();
		ArrayList<Reiziger> list = new ArrayList<Reiziger>();
		
		String queryText = "SELECT * FROM reiziger";
		ResultSet rs = stmt.executeQuery(queryText);
		
		while(rs.next()) {
			int reizigerID = rs.getInt(1);
			String voorletters = rs.getString(2);
			String tussenvoegsel = rs.getString(3);
			String achternaam = rs.getString(4);
			Date geboortedatum = rs.getDate(5);
			
			Reiziger r = new Reiziger(reizigerID, voorletters, tussenvoegsel, achternaam, geboortedatum);
			r.setKaarten(kaartDao.findByKaarthouder(r));
			
			list.add(r);
		}
		
		rs.close();
		stmt.close();
		closeConnection(conn);
		
		return list;
	}
	
	public Reiziger findByReizigerID(int id) throws SQLException {
		OVChipkaartSnowflakeDao kaartDao = new OVChipkaartSnowflakeDao();
		
		Connection conn = getConnection();
		String queryText2 = "SELECT * FROM reiziger WHERE reizigerid = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText2);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		int reizigerID = rs.getInt("REIZIGERID");
		String voorletters = rs.getString("VOORLETTERS");
		String tussenvoegsel = rs.getString("TUSSENVOEGSEL");
		String achternaam = rs.getString("ACHTERNAAM");
		Date geboortedatum = rs.getDate("GEBORTEDATUM");
		
		Reiziger r = new Reiziger(reizigerID, voorletters, tussenvoegsel, achternaam, geboortedatum);
		r.setKaarten(kaartDao.findByKaarthouder(r));
		
		rs.close();
		pstmt.close();
		closeConnection(conn);
		
		return r;
	}
	
	public List<Reiziger> findByGbdatum(String gbdatum) throws SQLException {
		Connection conn = getConnection();
		List<Reiziger> list = new ArrayList<Reiziger>();
		
		String queryText = "SELECT * FROM reiziger WHERE gebortedatum = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setDate(1, Date.valueOf(gbdatum));
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int reizigerID = rs.getInt("REIZIGERID");
			String voorletters = rs.getString("VOORLETTERS");
			String tussenvoegsel = rs.getString("TUSSENVOEGSEL");
			String achternaam = rs.getString("ACHTERNAAM");
			Date geboortedatum = rs.getDate("GEBORTEDATUM");
			
			Reiziger r = new Reiziger(reizigerID, voorletters, tussenvoegsel, achternaam, geboortedatum);
			list.add(r);
		}
		
		rs.close();
		pstmt.close();
		closeConnection(conn);
		
		return list;
	}

	public Reiziger save(Reiziger reiziger) throws SQLException {
		Connection conn = getConnection();
		
		String queryText = "INSERT INTO reiziger VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setInt(1, reiziger.getReizigerID());
		pstmt.setString(2, reiziger.getVoorletters());
		pstmt.setString(3, reiziger.getTussenvoegsel());
		pstmt.setString(4, reiziger.getAchternaam());
		pstmt.setDate(5, reiziger.getGbdatum());
		pstmt.executeUpdate();
		
		pstmt.close();
		closeConnection(conn);
		
		return reiziger;
	}

	public Reiziger update(Reiziger reiziger) throws SQLException {
		Connection conn = getConnection();
		
		String queryText = "UPDATE reiziger SET voorletters=?, tussenvoegsel=?, achternaam=?, gebortedatum=?  WHERE reizigerid=?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setString(1, reiziger.getVoorletters());
		pstmt.setString(2, reiziger.getTussenvoegsel());
		pstmt.setString(3, reiziger.getAchternaam());
		pstmt.setDate(4, reiziger.getGbdatum());
		pstmt.setInt(5, reiziger.getReizigerID());
		pstmt.executeUpdate();
		
		pstmt.close();
		closeConnection(conn);
		
		return reiziger;
	}
	
	public boolean delete(Reiziger reiziger) {
		try {
			Connection conn = getConnection();
			
			String queryText = "DELETE FROM reiziger WHERE REIZIGERID = ?";
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setInt(1, reiziger.getReizigerID());
			pstmt.executeUpdate();
			pstmt.close();
			closeConnection(conn);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
