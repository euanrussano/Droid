package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sophia.droid.model.Enemy;

public class EnemyShapeView implements EnemyView{

    private final ShapeRenderer shapeRenderer;

    public EnemyShapeView() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, Enemy enemy) {
        float x = enemy.getX();
        float y = enemy.getY();
        float width = enemy.getWidth();
        float height = enemy.getHeight();
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(x + width/2f, y + height/2f, width/2f, 10);
        shapeRenderer.end();

        batch.begin();
    }
}
