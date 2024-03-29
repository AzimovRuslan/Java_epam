package dao;

public interface Dao<T> {
    T getByID(int id);

    void save(T t);

    void update(T t);

    void delete(T t);
}
