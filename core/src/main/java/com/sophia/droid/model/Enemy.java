package com.sophia.droid.model;

public class Enemy {



    private float x;

    private float y;

    private float width = 1f;

    private float height = 1f;
    private int hitpoints = 10;




    public Enemy(float x, float y) {

        this.x = x;

        this.y = y;

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
}