package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DroidShapeView implements DroidView {
    private final ShapeRenderer shapeRenderer;

    public DroidShapeView() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void render(Batch batch, float parentAlpha, float x, float y, float width, float height, boolean isSelected) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the droid
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.circle(x+0.5f, y+0.5f, width/2f, 10);
        // render square on droid
        if (isSelected) {
            shapeRenderer.setColor(Color.BROWN);
            shapeRenderer.rect(x +0.4f, y +0.4f, 0.2f, 0.2f);
        }
        shapeRenderer.end();

    }

}
