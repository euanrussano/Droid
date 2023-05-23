package com.sophia.droid.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sophia.droid.actor.ArenaActor;
import com.sophia.droid.actor.DroidActor;
import com.sophia.droid.actor.SelectionActor;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.repository.DroidRepository;

public class DroidController extends InputListener {
    private final DroidRepository droidRepository;
    private final Stage uiStage;
    private final Stage mainStage;
    private Rectangle droidBounds = new Rectangle(0, 0, 1, 1);
    private Rectangle objBounds = new Rectangle(0, 0, 1, 1);
    private Vector2 touchPos = new Vector2();
    private Vector2 dragPosUI = new Vector2();
    private boolean dragging = false;

    public DroidController(DroidRepository droidRepository, Stage uiStage, Stage mainStage) {
        this.uiStage = uiStage;
        this.mainStage = mainStage;
        this.droidRepository = droidRepository;
    }

    public void update(float delta) {
        for (Droid droid : droidRepository.findAll()) {

            Arena arena = droid.getArena();
            if (droid.hasTarget()) {
                // current position
                float newPositionX = droid.getX();
                float newPositionY = droid.getY();

                // move on X
                int bearingX = 1;
                if (droid.getX() > droid.getTarget().x) {
                    bearingX = -1;
                }
                if (droid.getX() != droid.getTarget().x) {
                    // find the "potential" new position
                    newPositionX += bearingX * droid.getSpeed() * delta;
                    //droid.setX(droid.getX() + bearing * droid.getSpeed() * delta);

                }
                // move on Y
                int bearingY = 1;
                if (droid.getY() > droid.getTarget().y) {
                    bearingY = -1;
                }
                if (droid.getY() != droid.getTarget().y) {
                    // find the "potential" new position
                    newPositionY += bearingY * droid.getSpeed() * delta;
                    //droid.setY(droid.getY() + bearing * droid.getSpeed() * delta);

                }

                // check if the droid will collide in the next potential move
                // check if the bounds of the droid overlaps with any object in the grid around it

                this.droidBounds.set(newPositionX, newPositionY, droid.getWidth(), droid.getHeight());
                for (Droid droid2 : droidRepository.findAll()) {
                    if (droid == droid2) continue;
                    this.objBounds.set(droid2.getX(), droid2.getY(), droid2.getWidth(), droid2.getHeight());
                    // if it collides then stop moving and make sure the droid is inside a grid cell
                    if (this.droidBounds.overlaps(objBounds)) {
                        droid.removeTarget();
                        droid.setX(Math.round(droid.getX()));
                        droid.setY(Math.round(droid.getY()));
                    }
                }
                for (Obstacle obstacle : arena.getObstacles()) {

                    this.objBounds.set(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
                    // if it collides then stop moving and make sure the droid is inside a grid cell
                    if (this.droidBounds.overlaps(objBounds)) {
                        droid.removeTarget();
                        droid.setX(Math.round(droid.getX()));
                        droid.setY(Math.round(droid.getY()));
                    }
                }
                for (Enemy enemy : arena.getEnemies()) {

                    this.objBounds.set(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
                    // if it collides then stop moving and make sure the droid is inside a grid cell
                    if (this.droidBounds.overlaps(objBounds)) {
                        droid.removeTarget();
                        droid.setX(Math.round(droid.getX()));
                        droid.setY(Math.round(droid.getY()));
                    }
                }

                if (droid.hasTarget()) {
                    droid.setX(newPositionX);
                    droid.setY(newPositionY);

                    boolean arrivedX = Math.abs(droid.getX() - droid.getTarget().x) <  0.05f;
                    boolean arrivedY = Math.abs(droid.getY() - droid.getTarget().y) <  0.05f;
                    if (arrivedX & arrivedY) {
                        droid.setX(droid.getTarget().x);
                        droid.setY(droid.getTarget().y);
                        droid.removeTarget();
                    }
                }

            }
        }

    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        touchPos.set(x, y);
        if (event.getTarget() instanceof ArenaActor arenaActor){
            Arena arena = arenaActor.getArena();
            onArenaTouched(arena, x, y);
            return true;
        } else  if (event.getTarget() instanceof DroidActor droidActor){
            Droid droid = droidActor.getDroid();
            onDroidTouched(droid);
            return true;
        }
        return true;
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        if (event.getStage() == mainStage){
            dragPosUI.set(x, y);
            SelectionActor selectionActor = (SelectionActor)mainStage.getRoot().findActor(SelectionActor.class.getSimpleName());
            selectionActor.setPosition(touchPos.x, touchPos.y);
            if (dragPosUI.x < touchPos.x){
                selectionActor.setX(dragPosUI.x);
            }
            if (dragPosUI.y < touchPos.y){
                selectionActor.setY(dragPosUI.y);
            }
            selectionActor.setSize(Math.abs(dragPosUI.x - touchPos.x), Math.abs(dragPosUI.y - touchPos.y));
            dragging = true;
        }
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        SelectionActor selectionActor = mainStage.getRoot().findActor(SelectionActor.class.getSimpleName());
        if (dragging & Math.abs(dragPosUI.x - touchPos.x)>0.2f){
            objBounds.set(selectionActor.getX(), selectionActor.getY(), selectionActor.getWidth(), selectionActor.getHeight());
            for (Droid droid : droidRepository.findAll()){
                droid.isSelected = false;
                droidBounds.set(droid.getX(), droid.getY(), droid.getWidth(), droid.getHeight());
                if (objBounds.overlaps(droidBounds)){
                    droid.isSelected = true;
                }
            }


        }
        selectionActor.setBounds(0,0,0,0);
        dragging = false;
        dragPosUI.set(0, 0);
        touchPos.set(0, 0);

    }

    public void onDroidTouched(Droid droid) {
        for (Droid droid2 : droidRepository.findAll()){
            droid2.isSelected = false;
        }
        droid.isSelected = true;
    }

    public void onArenaTouched(Arena arena, float x, float y) {
        int targetX = (int)x;
        int targetY = (int)y;
        for (Droid droid : droidRepository.findAll()){
            if (droid.isSelected){
                droid.setTarget(targetX, targetY);
            }
        }
    }

}
