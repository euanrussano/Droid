package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Coin;
import com.sophia.droid.model.Droid;

public class CoinActor extends Actor {
    private final ShapeRenderer shapeRenderer;
    private final Coin coin;

    public CoinActor(Coin coin) {
        this.coin = coin;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        setBounds(coin.getX()-0.5f, coin.getY()-0.5f, coin.getWidth(), coin.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(coin.getX()-0.5f, coin.getY()-0.5f, coin.getWidth(), coin.getHeight());
        if (coin.getBody().getFixtureList().isEmpty()){
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        // render the coin
        float x = getX();
        float y = getY();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(x+0.5f, y+0.5f, coin.getWidth()/2f, 10);
        shapeRenderer.end();

    }
}
