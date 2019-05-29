package DAOs;

import CONN.SnowflakeBaseDao;
import POJOs.OVChipkaart;
import POJOs.OVChipkaartDao;
import POJOs.Reiziger;

import java.sql.*;
import java.util.List;

public class OVChipkaartSnowflakeDao extends SnowflakeBaseDao implements OVChipkaartDao {

    private Reiziger toReiziger(ResultSet resultSet) {
        Reiziger reiziger = new Reiziger(
                resultSet.getInt("REIZIGERID"),
                resultSet.getString("")
        )
    }

    public List<OVChipkaart> findAll() {

        try{

        }
    }
}
