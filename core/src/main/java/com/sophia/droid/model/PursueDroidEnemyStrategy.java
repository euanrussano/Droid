package com.sophia.droid.model;

import com.badlogic.gdx.math.Vector2;

public class PursueDroidEnemyStrategy implements EnemyStrategy{

    private final Droid droid;
    private final float radius;

    private Vector2 droidPosition = new Vector2();
    private Vector2 enemyPosition = new Vector2();
    private Vector2 distance = new Vector2();

    public PursueDroidEnemyStrategy(Droid droid, float radius) {
        this.droid = droid;
        this.radius = radius;
    }

    @Override
    public void update(Enemy enemy, float delta) {
        // calculate the distance to the droid
        distance.set( droidPosition.set(droid.getX(), droid.getY()).sub(enemyPosition.set(enemy.getX(), enemy.getY())) );

        // start pursue when distance is less than radius
        if(distance.len() < radius){
            enemy.setDirection(distance.nor());
            enemy.setX(enemy.getX() + delta*enemy.getDirection().x*enemy.getSpeed());
            enemy.setY(enemy.getY() + delta*enemy.getDirection().y*enemy.getSpeed());

        }


    }
}
