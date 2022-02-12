package dao;

import javax.transaction.Transactional;
import java.util.List;

public interface Dao<T>{
    T getByID(int id);

    void save(T t);

    void update(T t);

    void delete(T t);

    List<T> getAll();
}
