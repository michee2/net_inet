package ci.aho.demo.models.repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T>{
    void create(T t) throws SQLException;

    T getById(int id);

    List<T> getAll();

    boolean delete(Long i) throws SQLException;

    void update(T t) throws SQLException;
}
