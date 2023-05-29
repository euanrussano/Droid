package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObstacleTextureView implements ObstacleView {

    private final TextureRegion region;
    private final float regionWHRatio;

    public ObstacleTextureView() {
        Texture texture = new Texture(Gdx.files.internal("barrelRust_side.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
        float regionWidth = region.getRegionWidth();
        float regionHeight = region.getRegionHeight();
        regionWHRatio = regionWidth/regionHeight;
    }

    @Override
    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        batch.draw(region, x, y, 0, 0,
                width*regionWHRatio, height, 1, 1, 0);
    }
}
