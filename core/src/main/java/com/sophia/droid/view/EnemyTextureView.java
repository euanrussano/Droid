package com.sophia.droid.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sophia.droid.model.Enemy;

public class EnemyTextureView implements EnemyView{

    private final TextureRegion region;

    public EnemyTextureView() {
        Texture texture = new Texture(Gdx.files.internal("tankBody_red_outline.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha, Enemy enemy) {
        float rotation = enemy.getBody().getLinearVelocity().angleDeg()+90;
        float x = enemy.getX();
        float y = enemy.getY();
        float width = enemy.getWidth();
        float height = enemy.getHeight();
        batch.draw(region, x-0.5f, y-0.5f, 0.5f, 0.5f,
                width, height, 1, 1, rotation);

    }
}
