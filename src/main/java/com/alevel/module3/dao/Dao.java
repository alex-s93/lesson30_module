package com.alevel.module3.dao;

import java.util.List;

public interface Dao<T> {
    void create(T model);

    void update(T oldModel, T newModel);

    T findById(int id);

    List<T> findAll();

    void deleteById(int id);
}
