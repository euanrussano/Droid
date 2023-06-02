package com.sophia.droid.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.sophia.droid.model.Box;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;

import java.util.ArrayList;
import java.util.List;

public class DroidContactListener implements ContactListener {

    private ArrayList<Body> bodiesForRemoval = new ArrayList<Body>();
    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureB().getBody().getUserData() instanceof Enemy){
            if (contact.getFixtureA().getBody().getUserData() instanceof Droid){
                Enemy enemy = (Enemy) contact.getFixtureB().getBody().getUserData();
                Droid droid = (Droid)contact.getFixtureA().getBody().getUserData();
                enemy.interactWith(droid);
                contact.setEnabled(false);
                if (droid.getHealthPoints() <= 0){
                    bodiesForRemoval.add(droid.getBody());
                }
            }
        } else if(contact.getFixtureA().getBody().getUserData() instanceof Droid){
            if (contact.getFixtureB().getBody().getUserData() instanceof Box) {
                Droid droid = (Droid)contact.getFixtureA().getBody().getUserData();
                Box box = (Box) contact.getFixtureB().getBody().getUserData();
                droid.interactWith(box);
                contact.setEnabled(false);
                bodiesForRemoval.add(box.getBody());
            }
        }



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public List<Body> getBodies() {
        return bodiesForRemoval;
    }

    public void clearBodiesForRemoval() {
        bodiesForRemoval.clear();
    }
}
