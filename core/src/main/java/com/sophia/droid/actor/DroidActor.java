package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;

public class DroidActor extends Actor {

    private final ShapeRenderer shapeRenderer;
    private final Droid droid;

    public DroidActor(Droid droid) {
        this.droid = droid;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setBounds(droid.getX(), droid.getY(), droid.getWidth(), droid.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(droid.getX(), droid.getY(), droid.getWidth(), droid.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the droid
        float x = droid.getX();
        float y = droid.getY();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(x+0.5f, y+0.5f, droid.getWidth()/2f, 10);
        // render square on droid
        if (droid.isSelected) {
            shapeRenderer.setColor(Color.BROWN);
            shapeRenderer.rect(x +0.4f, y +0.4f, 0.2f, 0.2f);
        }
        shapeRenderer.end();

    }

    public Droid getDroid() {
        return droid;
    }
}
