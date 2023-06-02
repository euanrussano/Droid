package com.sophia.droid.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;
import com.sophia.droid.actor.ArenaActor;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;

public class DroidController extends InputListener {
    private final Droid droid;
    private final Stage uiStage;
    private final Stage mainStage;
    private Rectangle droidBounds = new Rectangle(0, 0, 1, 1);
    private Rectangle objBounds = new Rectangle(0, 0, 1, 1);
    private Vector2 touchPos = new Vector2();
    private Vector2 dragPosUI = new Vector2();
    private boolean dragging = false;

    private Label hpLabel;
    private Label boxesLabel;

    public DroidController(Droid droid, Stage uiStage, Stage mainStage) {
        this.uiStage = uiStage;
        this.mainStage = mainStage;
        this.droid = droid;
    }

    public void update(float delta) {
        droid.update(delta);
        mainStage.getViewport().getCamera().position.set(droid.getX(), droid.getY(), 0);
        if (hpLabel != null){
            hpLabel.setText(droid.getHealthPoints());
        }
        if (boxesLabel != null){
            boxesLabel.setText(droid.getBoxes());
        }

    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        touchPos.set(x, y);
        if (event.getTarget() instanceof ArenaActor){
            ArenaActor arenaActor = (ArenaActor)event.getTarget();
            Arena arena = arenaActor.getArena();
            onArenaTouched(arena, x, y);
            return true;
        }
        return false;
    }

    public void onArenaTouched(Arena arena, float x, float y) {
        int targetX = (int)x;
        int targetY = (int)y;
        droid.setTarget(targetX+0.5f, targetY+0.5f);
        droid.getBody().setAngularVelocity(0f);

    }

    public void setHPLabel(Label label) {
        this.hpLabel = label;
    }

    public void setBoxesLabel(Label boxesLabel) {
        this.boxesLabel = boxesLabel;
    }
}
