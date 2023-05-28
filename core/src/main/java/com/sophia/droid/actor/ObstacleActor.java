package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;

public class ObstacleActor extends Actor {
    private final ShapeRenderer shapeRenderer;
    private final Obstacle obstacle;

    public ObstacleActor(Obstacle obstacle) {
        this.obstacle = obstacle;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setBounds(obstacle.getX()-0.5f, obstacle.getY()-0.5f, obstacle.getWidth(), obstacle.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(obstacle.getX()-0.5f, obstacle.getY()-0.5f, obstacle.getWidth(), obstacle.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the enemy
        float x = getX();
        float y = getY();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(x, y, obstacle.getWidth(), obstacle.getHeight());
        shapeRenderer.end();

    }
}
