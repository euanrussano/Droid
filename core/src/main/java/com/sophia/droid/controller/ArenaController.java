package com.sophia.droid.controller;

import com.badlogic.gdx.math.Rectangle;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;

public class ArenaController {

    //private static final int unit = 32;
    private Arena arena;

    /** the target cell **/
    private float targetX, targetY;

    /** true if the droid moves **/
    private boolean moving = false;
    private Rectangle droidBounds;
    private Rectangle objBounds;

    public ArenaController(Arena arena) {

        this.arena = arena;
        this.droidBounds = new Rectangle(0f, 0f, 1f, 1f);
        this.objBounds = new Rectangle(-1f, -1f, 1f, 1f);
    }

    public void update(float delta) {
        Droid droid = arena.getDroid();

        if (moving) {
            // current position
            float newPositionX = droid.getX();
            float newPositionY = droid.getY();

            // move on X
            int bearingX = 1;
            if (droid.getX() > targetX) {
                bearingX = -1;
            }
            if (droid.getX() != targetX) {
                // find the "potential" new position
                newPositionX += bearingX * droid.getSpeed() * delta;
                //droid.setX(droid.getX() + bearing * droid.getSpeed() * delta);

            }
            // move on Y
            int bearingY = 1;
            if (droid.getY() > targetY) {
                bearingY = -1;
            }
            if (droid.getY() != targetY) {
                // find the "potential" new position
                newPositionY += bearingY * droid.getSpeed() * delta;
                //droid.setY(droid.getY() + bearing * droid.getSpeed() * delta);

            }

            // check if the droid will collide in the next potential move
            // check if the bounds of the droid overlaps with any object in the grid around it
            this.droidBounds.setPosition(newPositionX, newPositionY);
            for (int i = (int)newPositionY-1; i < newPositionY + 2; i++){
                for (int j = (int)newPositionX-1; j < newPositionX + 2; j++){
                    if (i >= arena.getGrid().length || j >= arena.getGrid()[0].length || i < 0 || j < 0){
                        continue;
                    }
                    Object obj = arena.getGrid()[i][j];
                    if (obj == null || obj == droid) continue;
                    else if (obj instanceof Enemy enemy){
                        this.objBounds.setPosition(enemy.getX(), enemy.getY());
                    } else if (obj instanceof Obstacle obstacle){
                        this.objBounds.setPosition(obstacle.getX(), obstacle.getY());
                    }

                    // if it collides then stop moving and make sure the droid is inside a grid cell
                    if (this.objBounds.getX() != -1f){
                        if (this.droidBounds.overlaps(objBounds)){
                            moving = false;
                        }
                    }

                }
            }

            if (moving){
                droid.setX(newPositionX);
                droid.setY(newPositionY);
            }

            // check if arrived
            if ((droid.getX() < targetX && bearingX == -1) || (droid.getX() > targetX && bearingX == 1))
                droid.setX(targetX);
            // check if arrived
            if ((droid.getY() < targetY && bearingY == -1) || (droid.getY() > targetY && bearingY == 1))
                droid.setY(targetY);
            // check if arrived
            if (droid.getX() == targetX && droid.getY() == targetY)
                moving = false;

        }

    }

    /** triggered with the coordinates every click **/

    public boolean onClick(int x, int y) {

        System.out.println(x + "," + y);
        System.out.println(arena.getWidth() + "," + arena.getHeight());
        if ((x < arena.getWidth()) & (y < arena.getHeight()) & (x > 0) & (y > 0)){
            targetX = x ;
            targetY = y ;

            if (arena.getGrid()[(int) targetY][(int) targetX] == null) {
                // start moving the droid towards the target
                moving = true;
                return true;
            }
        }

        return false;

    }


}
