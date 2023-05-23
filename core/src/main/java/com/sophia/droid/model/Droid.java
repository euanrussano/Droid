package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Droid {

    public boolean isSelected = false;

    private float x;

    private float y;

    private float speed = 2f;

    private float rotation = 0f;

    private float damage = 2f;
    private Arena arena;
    private Vector2 target = null;

    private float width = 1f;
    private float height = 1f;

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
}
