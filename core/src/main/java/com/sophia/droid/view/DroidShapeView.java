package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sophia.droid.model.Droid;

public class DroidShapeView implements DroidView {
    private final ShapeRenderer shapeRenderer;

    public DroidShapeView() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void render(Batch batch, float parentAlpha, Droid droid) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the droid
        float x = droid.getX();
        float y = droid.getY();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.circle(x+0.5f, y+0.5f, droid.getWidth()/2f, 10);
        shapeRenderer.end();

    }

}
