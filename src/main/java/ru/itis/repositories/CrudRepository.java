package ru.itis.repositories;

import java.util.ArrayList;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    void update(T entity);
    void delete(T entity);

    Optional<T> findById(Long id);
    ArrayList<T> findAll();

    void deleteById(Long id);

}
