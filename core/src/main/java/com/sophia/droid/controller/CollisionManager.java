package com.sophia.droid.controller;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;

public class CollisionManager {


    private final DroidRepository droidRepository;
    private final EnemyRepository enemyRepository;
    private final ObstacleRepository obstacleRepository;

    private Rectangle droidBounds = new Rectangle(0, 0, 1, 1);
    private Rectangle objBounds = new Rectangle(0, 0, 1, 1);

    private Vector2 direction = new Vector2();

    public CollisionManager(DroidRepository droidRepository, EnemyRepository enemyRepository, ObstacleRepository obstacleRepository) {

        this.droidRepository = droidRepository;
        this.enemyRepository = enemyRepository;
        this.obstacleRepository = obstacleRepository;
    }

    public void update(float delta){
        for (Droid droid : droidRepository.findAll()) {
            //check if the droid will collide in the next potential move
            // check if the bounds of the droid overlaps with any object in the grid around it

            // get the droid direction and speed to predict the next position
            direction = droid.getDirection();

            float newPositionX = droid.getX() + delta*direction.x*droid.getSpeed();
            float newPositionY = droid.getY() + delta*direction.y*droid.getSpeed();
            this.droidBounds.set(newPositionX, newPositionY, droid.getWidth(), droid.getHeight());
            for (Droid droid2 : droidRepository.findAll()) {
                if (droid == droid2) continue;
                this.objBounds.set(droid2.getX(), droid2.getY(), droid2.getWidth(), droid2.getHeight());
                // if it collides then stop moving and make sure the droid is inside a grid cell
                if (this.droidBounds.overlaps(objBounds)) {
                    droid.removeTarget();
                    droid.setDirection(direction.set(0,0));
                    droid.setX(Math.round(droid.getX()));
                    droid.setY(Math.round(droid.getY()));
                    return;
                }
            }
            for (Obstacle obstacle : obstacleRepository.findAll()) {

                this.objBounds.set(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
                // if it collides then stop moving and make sure the droid is inside a grid cell
                if (this.droidBounds.overlaps(objBounds)) {
                    droid.removeTarget();
                    droid.setDirection(direction.set(0,0));
                    droid.setX(Math.round(droid.getX()));
                    droid.setY(Math.round(droid.getY()));
                    return;
                }
            }
            for (Enemy enemy : enemyRepository.findAll()) {

                this.objBounds.set(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
                // if it collides then stop moving and make sure the droid is inside a grid cell
                if (this.droidBounds.overlaps(objBounds)) {
                    droid.removeTarget();
                    droid.setDirection(direction.set(0,0));
                    droid.setX(Math.round(droid.getX()));
                    droid.setY(Math.round(droid.getY()));
                    return;
                }
            }
        }
    }
}
