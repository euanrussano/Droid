package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ArenaShapeView implements ArenaView{

    private final ShapeRenderer shapeRenderer;

    public ArenaShapeView() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the grid
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.begin();
        for (int i = 0; i <= width; i++) {
            shapeRenderer.line(i, 0, i , height);

        }
        for (int i = 0; i <= height; i++) {
            shapeRenderer.line(0, i, width, i);
        }
        shapeRenderer.end();

        batch.begin();
    }
}
