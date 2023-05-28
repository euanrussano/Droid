package com.sophia.droid.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Obstacle {



//    private float x;
//
//    private float y;
    private float width = 1f;

    private float height=1f;
    private Body body;


    public Obstacle(float x, float y) {

//        this.x = x;
//
//        this.y = y;

    }



    public float getX() {

        return body.getPosition().x;

    }

    public float getY() {

        return body.getPosition().y;

    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}