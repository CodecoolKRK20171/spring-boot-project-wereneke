package com.codecool.wereneke.library.common;

import java.util.Map;

public interface Controller<T> {

    Iterable<T> index() throws NoSuchIdException;

    T show(Integer id) throws NoSuchIdException;

    T create(T obj);

    T update(Integer id, T obj) throws NoSuchIdException;

    void delete(Integer id) throws NoSuchIdException;
}
