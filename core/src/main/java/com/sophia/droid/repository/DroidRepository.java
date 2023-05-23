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
        droid.setX(7);
        droid.setY(7);

        Droid droid2 = new Droid();

        // position droid in the left corner
        droid2.setX(3);
        droid2.setY(3);

        droids.add(droid);
        droids.add(droid2);
    }

    public List<Droid> findAll() {
        return droids;
    }
}
