package com.codecool.wereneke.library.common;

public interface Controller<T> {

    Iterable<T> index() throws NoSuchIdException;

    T show(Integer id) throws NoSuchIdException;

    T create(T obj);
}
