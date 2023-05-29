package com.sophia.droid.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.sophia.droid.model.Droid;

public interface DroidView {
    void render(Batch batch, float parentAlpha, Droid droid);
}
