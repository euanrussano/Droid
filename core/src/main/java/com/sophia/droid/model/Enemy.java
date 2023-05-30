package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

public class Enemy {


    private final ArrayList<EnemyStrategy> enemyStrategies = new ArrayList<>();
    private float width = 1f;

    private float height = 1f;
    private int hitpoints = 10;
    private float speed = 1f;
    private Body body;
    private Arena arena;


    public Enemy(float x, float y) {

    }


    public float getX() {

        return body.getPosition().x;

    }

    public float getY() {

        return body.getPosition().y;

    }

    public int getHitpoints() {

        return hitpoints;

    }

    public void setHitpoints(int hitpoints) {

        this.hitpoints = hitpoints;

    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean removeEnemyStrategy(Class<? extends EnemyStrategy> enemyStrategyClass) {
        for (EnemyStrategy strategy : enemyStrategies){
            if (strategy.getClass().equals(enemyStrategyClass)){
                enemyStrategies.remove(strategy);
                return true;
            }
        }
        return false;
    }

    public void addEnemyStrategy(EnemyStrategy enemyStrategy) {
        // when adding a strategy make sure that it has only one element of class
        // e.g droid can not have 2 MoveToTarget strategies
        removeEnemyStrategy(enemyStrategy.getClass());
        this.enemyStrategies.add(enemyStrategy);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public void update(float delta){
        for (EnemyStrategy strategy : enemyStrategies){
            strategy.update(this, delta);
        }
        boolean outOfBounds = false;
        if (arena != null){
            if (getX() < 0.5f){
                body.getPosition().x = 0.5f;
                outOfBounds = true;
            }else if (getX() > arena.getWidth() - width/2f){
                body.getPosition().x = arena.getWidth() - width/2f;
                outOfBounds = true;
            }
            if (getY() < 0.5f){
                body.getPosition().y = 0.5f;
                outOfBounds = true;
            }else if (getY() > arena.getHeight() - height/2f){
                body.getPosition().y = arena.getHeight() - height/2f;
                outOfBounds = true;
            }

            if (outOfBounds){
                body.setLinearVelocity(0, 0);
                body.setAngularVelocity(0);
                body.setTransform(body.getPosition(), 0);
            }


        }
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Body getBody() {
        return body;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void interactWith(Droid droid) {
        droid.reduceHealthPoints(1);
        body.setLinearVelocity(0, 0);
    }
}