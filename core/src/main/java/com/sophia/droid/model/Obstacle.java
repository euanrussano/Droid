package com.sophia.droid.model;

public class Obstacle {



    private float x;

    private float y;
    private float width = 1f;

    private float height=1f;


    public Obstacle(float x, float y) {

        this.x = x;

        this.y = y;

    }



    public float getX() {

        return x;

    }

    public float getY() {

        return y;

    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}