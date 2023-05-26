package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sophia.droid.model.Arena;

public class ArenaActor extends Group {
    private final Arena arena;
    private final ShapeRenderer shapeRenderer;

    public ArenaActor(Arena arena) {
        this.arena = arena;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setBounds(0, 0, arena.getWidth(), arena.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the grid
        shapeRenderer.setColor(Color.FOREST);
        shapeRenderer.begin();
        for (int i = 0; i <= arena.getWidth(); i++) {
            shapeRenderer.line(i, 0, i , arena.getHeight());

        }
        for (int i = 0; i <= arena.getHeight(); i++) {
            shapeRenderer.line(0, i, arena.getWidth(), i);
        }
        shapeRenderer.end();
        super.draw(batch, parentAlpha);
    }

    public Arena getArena() {
        return arena;
    }
}
