package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Droid {

    public boolean isSelected = false;

    private float x;

    private float y;

    private float speed = 100f;

    private float rotation = 0f;

    private float damage = 2f;
    private Arena arena;
    private Vector2 target = null;

    private float width = 1f;
    private float height = 1f;
    private final Vector2 direction = new Vector2();
    private final ArrayList<DroidStrategy> droidStrategies = new ArrayList<>();



    public float getX() {

        return x;

    }

    public void setX(float x) {

        this.x = x;

    }

    public float getY() {

        return y;

    }

    public void setY(float y) {

        this.y = y;

    }

    public float getSpeed() {

        return speed;

    }

    public void setSpeed(float speed) {

        this.speed = speed;

    }

    public float getRotation() {

        return rotation;

    }

    public void setRotation(float rotation) {

        this.rotation = rotation;

    }

    public float getDamage() {

        return damage;

    }

    public void setDamage(float damage) {

        this.damage = damage;

    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setTarget(int targetX, int targetY) {

        this.target = new Vector2(targetX, targetY);
        // TODO(remove the line below later - wrong location to do this!)
        this.direction.set(this.target.cpy().sub(x, y).nor());
    }

    public boolean hasTarget() {
        return this.target != null;
    }

    public Vector2 getTarget() {
        return this.target;
    }

    public void removeTarget() {
        this.target = null;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setDirection(Vector2 direction) {
        this.direction.set(direction);
    }

    public Vector2 getDirection() {
        return direction.cpy();
    }

    public boolean removeDroidStrategy(Class<? extends DroidStrategy> droidStrategyClass) {
        for (DroidStrategy strategy : droidStrategies){
            if (strategy.getClass().equals(droidStrategyClass)){
                droidStrategies.remove(strategy);
                return true;
            }
        }
        return false;
    }

    public void addDroidStrategy(DroidStrategy droidStrategy) {
        // when adding a strategy make sure that it has only one element of class
        // e.g droid can not have 2 MoveToTarget strategies
        removeDroidStrategy(droidStrategy.getClass());
        this.droidStrategies.add(droidStrategy);
    }

    public void update(float delta) {

        for (DroidStrategy droidStrategy : droidStrategies){
            droidStrategy.update(this, delta);
        }
    }
}
