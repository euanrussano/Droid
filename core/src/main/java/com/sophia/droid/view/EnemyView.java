package com.sophia.droid.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.sophia.droid.model.Enemy;

public interface EnemyView {
    void draw(Batch batch, float parentAlpha, Enemy enemy);
}
