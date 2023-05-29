package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DroidTextureView implements DroidView{

    private final TextureRegion region;
    private final ShapeRenderer shapeRenderer;

    public DroidTextureView() {
        Texture texture = new Texture(Gdx.files.internal("tankBody_green_outline.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void render(Batch batch, float parentAlpha, float x, float y, float width, float height, boolean isSelected) {
        batch.draw(region, x, y, 0, 0,
                width, height, 1, 1, 0);

        if (isSelected) {
            batch.end();
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            // render the selection point
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

                shapeRenderer.setColor(Color.BROWN);
                shapeRenderer.rect(x +0.4f, y +0.4f, 0.2f, 0.2f);

            shapeRenderer.end();
            batch.begin();
        }

    }
}
