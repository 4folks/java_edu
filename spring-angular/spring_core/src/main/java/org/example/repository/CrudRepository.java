package org.example.repository;

import java.util.List;

public interface CrudRepository<T> {
    void create(T item);
    void update(T item);

    void remove(T item);

    T get(int id);

    T get(String label);

    List<T> getAll();
}
