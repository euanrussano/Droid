package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy {


    private final ArrayList<EnemyStrategy> enemyStrategies = new ArrayList<>();
    private float x;

    private float y;

    private float width = 1f;

    private float height = 1f;
    private int hitpoints = 10;
    private float speed = 1f;
    private final Vector2 direction = new Vector2();




    public Enemy(float x, float y) {

        this.x = x;

        this.y = y;

    }

    public Vector2 getDirection(){
        return direction;
    }

    private void setDirection(float x, float y){
        direction.set(x, y);
    }



    public float getX() {

        return x;

    }

    public float getY() {

        return y;

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

    public void setDirection(Vector2 direction) {
        this.direction.set(direction);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void update(float delta){
        for (EnemyStrategy strategy : enemyStrategies){
            strategy.update(this, delta);
        }
    }
}