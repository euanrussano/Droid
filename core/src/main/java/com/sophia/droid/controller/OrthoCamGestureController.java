package com.sophia.droid.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class OrthoCamGestureController implements GestureDetector.GestureListener {
    final OrthographicCamera camera;

    public OrthoCamGestureController(OrthographicCamera camera) {

        this.camera = camera;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        String message = "PAN";
        Gdx.app.log("INFO", message);

        Gdx.app.log("INFO", deltaX + "," + deltaY);
        Gdx.app.log("INFO", "Camera position before: " + camera.position);

        float scale = 0.01f;

        camera.position.add(-deltaX*scale, deltaY*scale, 0);
        camera.update();

        Gdx.app.log("INFO", "Camera position after: " + camera.position);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        String message = "Zoom performed";
        Gdx.app.log("INFO", message);
//
        camera.zoom = (initialDistance / distance);
        camera.update();

        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

        return false;
    }

    @Override
    public void pinchStop() {

    }

}