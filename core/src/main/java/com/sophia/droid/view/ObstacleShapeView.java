package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ObstacleShapeView implements ObstacleView{

    private final ShapeRenderer shapeRenderer;

    public ObstacleShapeView() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();

        batch.begin();
    }
}
