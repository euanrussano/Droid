package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Box;
import com.sophia.droid.view.CoinView;

public class CoinActor extends Actor {
    private final Box box;
    private final CoinView coinView;

    public CoinActor(Box box, CoinView coinView) {
        this.box = box;
        this.coinView = coinView;

        setBounds(box.getX()-0.5f, box.getY()-0.5f, box.getWidth(), box.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(box.getX()-0.5f, box.getY()-0.5f, box.getWidth(), box.getHeight());
        if (box.getBody().getFixtureList().isEmpty()){
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        coinView.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());


    }
}
