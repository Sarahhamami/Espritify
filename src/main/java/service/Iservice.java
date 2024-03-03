package service;

import java.util.List;

public interface Iservice<T> {
    void add(T t);
    void delete(T t);
    void update(T t);
    List<T> displayAll();
    T displayByid(int id);




}
