package POJOs;

import java.sql.SQLException;
import java.util.List;

public interface ReizigerDao {
    List<Reiziger> findAll() throws SQLException;
    List<Reiziger> findByGbdatum(String gbdatum) throws SQLException;
    Reiziger save(Reiziger reiziger) throws SQLException;
    Reiziger update(Reiziger reiziger) throws SQLException;
    boolean delete(Reiziger reiziger) throws SQLException;
}