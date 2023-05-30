package com.sophia.droid.model;

import com.badlogic.gdx.physics.box2d.Body;

public class Box {
    private Body body;
    private float width = 1f;
    private float height = 1f;
    private Arena arena;

    public void setBody(Body body) {
        this.body = body;
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

    public Body getBody() {
        return body;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
