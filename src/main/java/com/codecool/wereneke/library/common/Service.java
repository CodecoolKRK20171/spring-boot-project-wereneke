package com.codecool.wereneke.library.common;

import java.util.List;

public interface Service<T> {

    void create(T obj);
    void update(T obj);
    void delete(T obj);
    T findOne(int id);
    Iterable<T> findAll();
}
