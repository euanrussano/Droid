package com.sophia.droid.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;

public class SimpleArenaRenderer implements Renderer{

    private Arena arena;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;

    public SimpleArenaRenderer(Arena arena) {
        this.arena = arena;
        viewport = new FillViewport(20,20);
        viewport.getCamera().position.set(Arena.WIDTH/2f, Arena.HEIGHT/2f, 0);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

    }
    @Override
    public void render() {
        viewport.getCamera().update();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        // render the grid
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.begin();
        for (int i = 0; i <= Arena.WIDTH; i++) {
            shapeRenderer.line(i, 0, i , Arena.HEIGHT);

        }
        for (int i = 0; i <= Arena.HEIGHT; i++) {
            shapeRenderer.line(0, i, Arena.WIDTH, i);
        }
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // render the obstacles
        shapeRenderer.setColor(Color.BLUE);
        for (Obstacle obs : arena.getObstacles()) {
            float x = obs.getX();
            float y = obs.getY();
            shapeRenderer.rect(x+0.1f, y+0.1f, 0.8f, 0.8f);
        }


        // render the enemies
        shapeRenderer.setColor(Color.RED);
        for (Enemy enemy : arena.getEnemies()) {
            float x = enemy.getX();
            float y = enemy.getY();
            shapeRenderer.circle(x + 0.5f, y + 0.5f, 0.8f/2f, 10);
        }

        // render player droid
        shapeRenderer.setColor(Color.GREEN);
        Droid droid = arena.getDroid();
        float x = droid.getX();
        float y = droid.getY();
        shapeRenderer.circle(x + 0.5f, y + 0.5f, 0.8f/2f, 10);
        // render square on droid
        shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(x + 0.4f, y + 0.4f, 0.2f, 0.2f);

        shapeRenderer.end();

    }

    @Override
    public Vector3 unproject(Vector3 screenCords) {
        return viewport.unproject(screenCords);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
