package com.sophia.droid.view;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface DroidView {
    void render(Batch batch, float parentAlpha, float x, float y, float width, float height, boolean isSelected);
}
