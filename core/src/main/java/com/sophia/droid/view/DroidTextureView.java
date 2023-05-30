package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sophia.droid.model.Droid;

public class DroidTextureView implements DroidView{

    private final TextureRegion region;

    public DroidTextureView() {
        Texture texture = new Texture(Gdx.files.internal("tankBody_green_outline.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
    }

    @Override
    public void render(Batch batch, float parentAlpha, Droid droid) {
        float x = droid.getX();
        float y = droid.getY();
        float rotation = droid.getBody().getLinearVelocity().angleDeg()+90;

        batch.draw(region, x-0.5f, y-0.5f, 0.5f, 0.5f,
                droid.getWidth(), droid.getHeight(), 1, 1, rotation);

    }
}
