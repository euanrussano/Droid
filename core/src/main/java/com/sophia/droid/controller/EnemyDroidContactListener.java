package com.sophia.droid.controller;

import com.badlogic.gdx.physics.box2d.*;
import com.sophia.droid.model.Coin;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyDroidContactListener implements ContactListener {

    private ArrayList<Body> bodiesForRemoval = new ArrayList<Body>();
    @Override
    public void beginContact(Contact contact) {
        if (contact.getFixtureB().getBody().getUserData() instanceof Enemy enemy){
            if (contact.getFixtureA().getBody().getUserData() instanceof Droid droid){
                enemy.interactWith(droid);
                contact.setEnabled(false);
                if (droid.getHealthPoints() <= 0){
                    droid.getArena().removeDroid(droid);
                    bodiesForRemoval.add(droid.getBody());
                }
            }
        } else if(contact.getFixtureA().getBody().getUserData() instanceof Droid droid){
            if (contact.getFixtureB().getBody().getUserData() instanceof Coin coin) {
                droid.interactWith(coin);
                contact.setEnabled(false);
                bodiesForRemoval.add(coin.getBody());
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
