package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;

public class PursueDroidEnemyStrategy implements EnemyStrategy{

    private final Droid droid;
    private final float radius;
    private final Vector2 distance = new Vector2();
    private boolean inRadius = false;

    public PursueDroidEnemyStrategy(Droid droid, float radius) {
        this.droid = droid;
        this.radius = radius;


    }

    @Override
    public void update(Enemy enemy, float delta) {
        // calculate the distance to the droid
        distance.set( droid.getBody().getPosition()).sub(enemy.getBody().getPosition());

        // start pursue when distance is less than radius and droid wasnt already in radius
        if(distance.len() < radius & !inRadius){
            inRadius = true;
            distance.nor();
            enemy.getBody().setLinearVelocity(distance.x*enemy.getSpeed(), distance.y*enemy.getSpeed());
            enemy.getBody().setAngularVelocity(0);
        } else {
            inRadius = false;
            enemy.getBody().setLinearVelocity(0,0 );
            enemy.getBody().setTransform(enemy.getX(), enemy.getY(), 0);
        }


    }
}
