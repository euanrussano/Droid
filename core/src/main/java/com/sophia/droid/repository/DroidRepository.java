package com.sophia.droid.repository;

import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;

import java.util.ArrayList;
import java.util.List;

public class DroidRepository {

    ArrayList<Droid> droids = new ArrayList<>();

    public DroidRepository() {
        // create two droids
        Droid droid = new Droid();

        // position droid in the middle
        droid.setX(Arena.WIDTH / 2);
        droid.setY(Arena.HEIGHT / 2);

        Droid droid2 = new Droid();

        // position droid in the left corner
        droid2.setX(Arena.WIDTH / 3);
        droid2.setY(Arena.HEIGHT / 3);

        droids.add(droid);
        droids.add(droid2);
    }

    public List<Droid> findAll() {
        return droids;
    }
}
