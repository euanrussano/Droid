package com.sophia.droid.model;

/*
Defines an action (move, attack, etc) that can be shared among droids.
 */
public interface DroidStrategy {

    void update(Droid droid, float delta);
}
