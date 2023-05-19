package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public interface Renderer {

    public void render();

    /*
    Transform from screen coordinates to world coordinates
     */
    Vector3 unproject(Vector3 screenCords);

    /*
    Adjust the renderer view to the screen size
     */
    void resize(int width, int height);
}
