package com.codecool.wereneke.library.common;

public interface Service<T> {

    T create(T obj);
    T update(Integer id, T obj) throws NoSuchIdException;
    void delete(Integer id) throws NoSuchIdException;
    T findOne(Integer id) throws NoSuchIdException;
    Iterable<T> findAll(boolean arch);

}
