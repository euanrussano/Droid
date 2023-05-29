package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EnemyTextureView implements EnemyView{

    private final TextureRegion region;

    public EnemyTextureView() {
        Texture texture = new Texture(Gdx.files.internal("tankBody_red_outline.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        batch.draw(region, x, y, 0, 0,
                width, height, 1, 1, 0);

    }
}
