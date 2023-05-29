package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.sophia.droid.model.Arena;
import com.sophia.droid.view.ArenaView;

public class ArenaActor extends Group {
    private final Arena arena;

    private final ArenaView arenaView;

    public ArenaActor(Arena arena, ArenaView arenaView) {
        this.arena = arena;
        this.arenaView = arenaView;

        setBounds(0, 0, arena.getWidth(), arena.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        arenaView.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());

    }

    public Arena getArena() {
        return arena;
    }
}
