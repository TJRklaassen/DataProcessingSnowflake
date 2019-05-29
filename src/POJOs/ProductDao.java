package POJOs;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> findAll() throws SQLException;
    List<Product> findByKaart(OVChipkaart kaart) throws SQLException;
    Product save(Product product) throws SQLException;
    Product update(Product product) throws SQLException;
    boolean delete(Product product) throws SQLException;
    List<Product> findAllWithCards() throws SQLException;
}