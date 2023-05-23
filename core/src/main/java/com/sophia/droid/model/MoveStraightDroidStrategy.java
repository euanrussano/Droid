package com.sophia.droid.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class MoveStraightDroidStrategy implements DroidStrategy{
    private Vector2 distanceFromTarget = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 movingDistance = new Vector2();

    @Override
    public void update(Droid droid, float delta) {
        if (droid.hasTarget()) {
            // update droid direction according its current position and
            // distance to target
            distanceFromTarget.set(droid.getTarget().x, droid.getTarget().y).sub(droid.getX(), droid.getY());
            direction.set(distanceFromTarget.x, distanceFromTarget.y).nor();
            movingDistance.set(direction.x, direction.y).scl(droid.getSpeed()*delta);
            droid.setDirection(direction);

            // check if the vector distance is smaller than the speed*delta*direction,
            // if yes, directly moves the droid to the target
            // otherwise, do a regular move
            if(distanceFromTarget.len() < movingDistance.len()){
                droid.setX(droid.getTarget().x);
                droid.setY(droid.getTarget().y);
                droid.removeTarget();
            }else {
                droid.setX(droid.getX() + movingDistance.x);
                droid.setY(droid.getY() + movingDistance.y);
            }
        }

    }
}
