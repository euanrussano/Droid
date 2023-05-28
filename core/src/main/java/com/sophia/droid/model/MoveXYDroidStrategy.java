package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;

public class MoveXYDroidStrategy implements DroidStrategy{

    private final Vector2 direction = new Vector2();
    @Override
    public void update(Droid droid, float delta) {
        if (droid.hasTarget()) {

            // first align the droid in the X direction, then
            // move in the Y direction
            direction.x = 0;
            direction.y = 0;
            if (Math.abs(droid.getX() - droid.getTarget().x) < 0.1f){
                //droid.setX(droid.getTarget().x);
                droid.getBody().setTransform(new Vector2(droid.getTarget().x, droid.getY()), 0);
                if ((Math.abs(droid.getY() - droid.getTarget().y) < 0.1f)){
                    droid.getBody().setTransform(new Vector2(droid.getTarget().x, droid.getTarget().y), 0);
                    droid.getBody().setLinearVelocity(0, 0);
                    return;
                } else if (droid.getY() > droid.getTarget().y){
                    direction.y = -1;
                }else if (droid.getY() < droid.getTarget().y){
                    direction.y = 1;
                }
            } else if (droid.getX() > droid.getTarget().x){
                direction.x = -1;
            } else if (droid.getX() < droid.getTarget().x){
                direction.x = 1;
            }

//            droid.setDirection(direction);
//            droid.setX(droid.getX() + delta*droid.getDirection().x*droid.getSpeed());
//            droid.setY(droid.getY() + delta*droid.getDirection().y*droid.getSpeed());
            droid.getBody().setLinearVelocity(direction.x*droid.getSpeed(), direction.y*droid.getSpeed());
            droid.getBody().setAngularVelocity(0);
        }

    }
}
