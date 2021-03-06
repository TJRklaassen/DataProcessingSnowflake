package POJOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CONN.SnowflakeBaseDao;

public class OVChipkaartSnowflakeDao extends SnowflakeBaseDao implements OVChipkaartDao{
	
	public ArrayList<OVChipkaart> findAll() throws SQLException {
		ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
		ProductSnowflakeDao productDao = new ProductSnowflakeDao();
		
		ArrayList<OVChipkaart> kaartList = new ArrayList<OVChipkaart>();
		ArrayList<Reiziger> reizigerList = reizigerDao.findAll();
		
		for(Reiziger r : reizigerList) {
			for(OVChipkaart k : r.getKaarten()) {
				k.setProductenOpKaart(productDao.findByKaart(k));
				kaartList.add(k);
			}
		}
		return kaartList;
	}
	
	public ArrayList<OVChipkaart> findByProduct(Product product) throws SQLException {
		ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
		
		Connection conn = getConnection();
		ArrayList<OVChipkaart> list = new ArrayList<OVChipkaart>();
		
		String queryText = "SELECT ov.kaartnummer, ov.geldigtot, ov.klasse, ov.saldo, ov.reizigerid\r\n" + 
				"FROM product p, ov_chipkaart ov, ov_chipkaart_product ovp\r\n" + 
				"WHERE ov.kaartnummer = ovp.kaartnummer\r\n" + 
				"AND p.productnummer = ovp.productnummer\r\n" + 
				"AND p.productnummer = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setInt(1, product.getProductNummer());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int kaartnummer = rs.getInt("KAARTNUMMER");
			Date geldigtot = rs.getDate("GELDIGTOT");
			int klasse = rs.getInt("KLASSE");
			double saldo = rs.getDouble("SALDO");
			int reizigerid = rs.getInt("REIZIGERID");
			
			Reiziger reiziger = reizigerDao.findByReizigerID(reizigerid);
			OVChipkaart ov = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, reiziger);			
			list.add(ov);
		}
		
		rs.close();
		pstmt.close();
		closeConnection(conn);
		
		return list;
	}
	
	public OVChipkaart findByKaartnummer(int nummer) throws SQLException {
		ReizigerSnowflakeDao reizigerDao = new ReizigerSnowflakeDao();
		Connection conn = getConnection();
		
		String queryText = "SELECT * FROM ov_chipkaart WHERE kaartnummer = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setInt(1, nummer);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			int kaartnummer = rs.getInt("KAARTNUMMER");
			Date geldigtot = rs.getDate("GELDIGTOT");
			int klasse = rs.getInt("KLASSE");
			double saldo = rs.getDouble("SALDO");
			int reizigerid = rs.getInt("REIZIGERID");
			
			Reiziger reiziger = reizigerDao.findByReizigerID(reizigerid);
			OVChipkaart ov = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, reiziger);
		
			rs.close();
			pstmt.close();
			closeConnection(conn);
			
			return ov;
		}
		
		return null;
	}
	
	public ArrayList<OVChipkaart> findByKaarthouder(Reiziger kaarthouder) throws SQLException {
		Connection conn = getConnection();
		ArrayList<OVChipkaart> list = new ArrayList<OVChipkaart>();
		
		String queryText = "SELECT * FROM ov_chipkaart WHERE reizigerid = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setInt(1, kaarthouder.getReizigerID());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int kaartnummer = rs.getInt("KAARTNUMMER");
			Date geldigtot = rs.getDate("GELDIGTOT");
			int klasse = rs.getInt("KLASSE");
			double saldo = rs.getDouble("SALDO");
			
			OVChipkaart ov = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, kaarthouder);
			list.add(ov);
		}
		
		rs.close();
		pstmt.close();
		closeConnection(conn);
		
		return list;
	}

	public OVChipkaart save(OVChipkaart kaart) throws SQLException {
		Connection conn = getConnection();
		
		String queryText = "INSERT INTO ov_chipkaart VALUES (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setInt(1, kaart.getKaartnummer());
		pstmt.setDate(2, kaart.getGeldigTot());
		pstmt.setInt(3, kaart.getKlasse());
		pstmt.setDouble(4, kaart.getSaldo());
		pstmt.setInt(5, kaart.getKaarthouder().getReizigerID());
		pstmt.executeUpdate();
		
		pstmt.close();
		closeConnection(conn);
		
		return kaart;
	}

	public OVChipkaart update(OVChipkaart kaart) throws SQLException {
		Connection conn = getConnection();
		
		String queryText = "UPDATE ov_chipkaart SET geldigtot=?, klasse=?, saldo=?, reizigerid=?  WHERE kaartnummer=?";
		PreparedStatement pstmt = conn.prepareStatement(queryText);
		pstmt.setDate(1, kaart.getGeldigTot());
		pstmt.setInt(2, kaart.getKlasse());
		pstmt.setDouble(3, kaart.getSaldo());
		pstmt.setInt(4, kaart.getKaarthouder().getReizigerID());
		pstmt.setInt(5, kaart.getKaartnummer());
		pstmt.executeUpdate();
		
		pstmt.close();
		closeConnection(conn);
		
		return kaart;
	}

	public boolean delete(OVChipkaart kaart) throws SQLException {
		try {
			Connection conn = getConnection();
			
			String queryText = "DELETE FROM ov_chipkaart WHERE kaartnummer = ?";
			PreparedStatement pstmt = conn.prepareStatement(queryText);
			pstmt.setInt(1, kaart.getKaartnummer());
			pstmt.executeUpdate();
			
			pstmt.close();
			closeConnection(conn);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}