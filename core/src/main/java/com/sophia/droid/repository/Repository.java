package com.sophia.droid.repository;

import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {

    ArrayList<T> entities = new ArrayList<>();

    public List<T> findAll() {
        return entities;
    }

    public void save(T entity) {
        entities.add(entity);
    }
}
