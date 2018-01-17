package com.codecool.wereneke.library.common;

public interface Service<T> {

    void create(T obj);
    void update(T obj);
    void delete(T obj);
    T findOne(Integer id) throws NoSuchIdException;
    Iterable<T> findAll();
}
