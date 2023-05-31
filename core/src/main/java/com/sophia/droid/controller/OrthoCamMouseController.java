package com.sophia.droid.controller;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class OrthoCamMouseController implements InputProcessor {

    final OrthographicCamera camera;
    final Vector3 curr = new Vector3();
    final Vector3 last = new Vector3(-1, -1, -1);
    final Vector3 delta = new Vector3();
    public OrthoCamMouseController(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        last.set(-1, -1, -1);
        return false;
    }

        @Override
    public boolean touchDragged (int x, int y, int pointer) {
        camera.unproject(curr.set(x, y, 0));
        if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
            camera.unproject(delta.set(last.x, last.y, 0));
            delta.sub(curr);
            camera.position.add(delta.x, delta.y, 0);
        }
        last.set(x, y, 0);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        camera.zoom += amountY/10f;
        return false;
    }
}
