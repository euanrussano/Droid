package com.sophia.droid.model;

public class Droid {

    private float x;

    private float y;

    private float speed = 2f;

    private float rotation = 0f;

    private float damage = 2f;
    private Arena arena;


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
}
