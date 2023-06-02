package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Box;
import com.sophia.droid.view.BoxView;

public class BoxActor extends Actor {
    private final Box box;
    private final BoxView boxView;

    public BoxActor(Box box, BoxView boxView) {
        this.box = box;
        this.boxView = boxView;

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
        boxView.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());


    }
}
