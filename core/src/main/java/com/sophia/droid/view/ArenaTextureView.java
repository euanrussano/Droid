package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ArenaTextureView implements ArenaView{

    private final TextureRegion grassTextureRegion;

    public ArenaTextureView() {
        Texture texture = new Texture(Gdx.files.internal("tileGrass1.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        grassTextureRegion = new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++){
                batch.draw(grassTextureRegion, x+i, y+j, 0, 0,
                        1, 1, 1, 1, 0);
            }
        }

    }
}
