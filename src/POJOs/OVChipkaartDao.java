package POJOs;

import java.sql.SQLException;
import java.util.List;

public interface OVChipkaartDao {
    List<OVChipkaart> findAll() throws SQLException;
    OVChipkaart findByKaartnummer(int nummer) throws SQLException;
    List<OVChipkaart> findByKaarthouder(Reiziger kaarthouder) throws SQLException;
    OVChipkaart save(OVChipkaart kaart) throws SQLException;
    OVChipkaart update(OVChipkaart kaart) throws SQLException;
    boolean delete(OVChipkaart kaart) throws SQLException;
}