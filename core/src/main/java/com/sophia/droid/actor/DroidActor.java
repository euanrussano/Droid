package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.view.DroidShapeView;
import com.sophia.droid.view.DroidView;

public class DroidActor extends Actor {
    private final Droid droid;
    private final DroidView droidView;

    private Label hpLabel;
    private Label boxesLabel;

    public DroidActor(Droid droid, DroidView droidView) {
        this.droid = droid;
        this.droidView = droidView;

        setBounds(droid.getX()-0.5f, droid.getY()-0.5f, droid.getWidth(), droid.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(droid.getX()-0.5f, droid.getY()-0.5f, droid.getWidth(), droid.getHeight());
        if (droid.getArena() == null){
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.droidView.render(batch, parentAlpha, droid);
    }

    public Droid getDroid() {
        return droid;
    }
}
