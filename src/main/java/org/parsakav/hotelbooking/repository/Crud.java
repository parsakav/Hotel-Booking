package org.parsakav.hotelbooking.repository;

import java.util.List;

public interface Crud<T> {
    T save(T t);
    int delete(T t);
    List<T> findAll();
    T findById(long id);
    int update(T t);

}
