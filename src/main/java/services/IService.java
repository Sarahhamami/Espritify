package Services;

import java.util.List;

public interface IService<T> {
    boolean add(T t);
    void delete(T t);
    boolean update(T t);
    List<T> readAll();
    T readById(int id);
}
