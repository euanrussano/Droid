package com.sophia.droid.controller;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.service.DroidService;

public class ArenaController {

    private Arena arena;
    /** the target cell **/

    /** true if the droid moves **/
    private Rectangle droidBounds;
    private Rectangle objBounds;


    private boolean dragging = false;
    private Vector3 touchPos = new Vector3();
    //private Vector3 last = new Vector3(-1, -1, -1);
    //final Vector3 curr = new Vector3();
    //final Vector3 delta = new Vector3();

    public ArenaController(Arena arena) {

        this.arena = arena;
        this.droidBounds = new Rectangle(0f, 0f, 1f, 1f);
        this.objBounds = new Rectangle(-1f, -1f, 1f, 1f);


    }

    public void update(float delta) {
        for (Droid droid : arena.getDroidService().findAll()) {
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
                this.droidBounds.setPosition(newPositionX, newPositionY);
                for (int i = (int) newPositionY - 1; i < newPositionY + 2; i++) {
                    for (int j = (int) newPositionX - 1; j < newPositionX + 2; j++) {
                        if (i >= arena.getGrid().length || j >= arena.getGrid()[0].length || i < 0 || j < 0) {
                            continue;
                        }
                        Object obj = arena.getGrid()[i][j];
                        if (obj == null) continue;
                        else if (obj instanceof Droid & obj != droid) {
                            Droid droid2 = (Droid) obj;
                            this.objBounds.setPosition(droid2.getX(), droid2.getY());
                        } else if (obj instanceof Enemy enemy) {
                            this.objBounds.setPosition(enemy.getX(), enemy.getY());
                        } else if (obj instanceof Obstacle obstacle) {
                            this.objBounds.setPosition(obstacle.getX(), obstacle.getY());
                        }

                        // if it collides then stop moving and make sure the droid is inside a grid cell
                        if (this.objBounds.getX() != -1f) {
                            if (this.droidBounds.overlaps(objBounds)) {
                                droid.removeTarget();
                                droid.setX(Math.round(droid.getX()));
                                droid.setY(Math.round(droid.getY()));
                            }
                        }

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

    /** triggered with the coordinates every click **/
    public boolean onClick(float x, float y) {
        System.out.println(x + "," + y);
        arena.getSelectionRectangle().setPosition(x, y);
        touchPos.set(x, y, 0);

        // when clicks go outside the arena, unselect all droids
        if ((x >= arena.getWidth()) | (y > arena.getHeight()) | (x < 0) | (y < 0)) {
            for (Droid droid : arena.getDroidService().findAll()) {
                droid.isSelected = false;
            }
            return true;
        }
        int targetX = (int)x ;
        int targetY = (int)y ;

        // if clicks in a droid select it and unselect the others
        for (Droid droid : arena.getDroidService().findAll()) {
            droidBounds.setPosition(droid.getX(), droid.getY());
            if (droidBounds.contains(targetX, targetY)) {
                System.out.println(droid.isSelected);
                for (Droid droid2 : arena.getDroidService().findAll()) {
                    if (droid2 != droid)
                        droid2.isSelected = false;
                }
                droid.isSelected = !droid.isSelected;
                return true;
            }
        }

        // else if clicks in clear location, move the selected droids towards the location
        if (arena.getGrid()[targetY][targetX] == null) {
            for (Droid droid : arena.getDroidService().findAll()){
                if (!(droid.isSelected)) continue;

                // set the location as the target location for the droid
                droid.setTarget(targetX, targetY);
            }
            return true;
        }
        return false;

    }


    public void onTouchDrag(float x, float y) {
        if (x > arena.getSelectionRectangle().x) {
            arena.getSelectionRectangle().setWidth(x - touchPos.x);
        } else{
            arena.getSelectionRectangle().setX(x);
            arena.getSelectionRectangle().setWidth(touchPos.x - x);
        }

        if (y  > arena.getSelectionRectangle().y) {
            arena.getSelectionRectangle().setHeight(y - arena.getSelectionRectangle().y);
        } else{
            arena.getSelectionRectangle().setY(y);
            arena.getSelectionRectangle().setHeight(touchPos.y - y);
        }
        dragging = true;
        System.out.println("dragging");
        System.out.println(arena.getSelectionRectangle());
    }

    public void onTouchUp(float x, float y) {
        if (dragging){
            for (Droid droid : arena.getDroidService().findAll()){
                droidBounds.setPosition(droid.getX(), droid.getY());
                if (arena.getSelectionRectangle().overlaps(droidBounds))
                    droid.isSelected = !droid.isSelected;
            }
        }
        dragging = false;
        //last.set(-1, -1, -1);
        arena.getSelectionRectangle().setSize(0f, 0f);
    }
}
