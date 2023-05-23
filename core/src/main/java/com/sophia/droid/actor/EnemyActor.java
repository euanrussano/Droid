package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Enemy;

public class EnemyActor extends Actor {

    private final ShapeRenderer shapeRenderer;
    private final Enemy enemy;

    public EnemyActor(Enemy enemy) {
        this.enemy = enemy;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setBounds(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the enemy
        float x = enemy.getX();
        float y = enemy.getY();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(x + enemy.getWidth()/2f, y + enemy.getHeight()/2f, enemy.getWidth()/2f, 10);
        shapeRenderer.end();

    }

   
}
