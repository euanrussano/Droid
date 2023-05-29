package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Coin;
import com.sophia.droid.model.Droid;
import com.sophia.droid.view.CoinView;

public class CoinActor extends Actor {
    private final Coin coin;
    private final CoinView coinView;

    public CoinActor(Coin coin, CoinView coinView) {
        this.coin = coin;
        this.coinView = coinView;

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
        coinView.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());


    }
}
