package com.sophia.droid.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
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

            float dx = delta*direction.x*droid.getSpeed();
            float dy = delta*direction.y*droid.getSpeed();

//            float newPositionX = droid.getX() + dx;
//            float newPositionY = droid.getY() + dy;
//
//            this.droidBounds.set(newPositionX, newPositionY, droid.getWidth(), droid.getHeight());
//            for (Droid droid2 : droidRepository.findAll()) {
//                if (droid == droid2) continue;
//                this.objBounds.set(droid2.getX(), droid2.getY(), droid2.getWidth(), droid2.getHeight());
//                // if it collides then stop moving and make sure the droid is inside a grid cell
//                if (this.droidBounds.overlaps(objBounds)) {
//                    droid.removeTarget();
//                    droid.setDirection(direction.set(0,0));
//                    droid.setX(Math.round(droid.getX()));
//                    droid.setY(Math.round(droid.getY()));
//                    return;
//                }
//            }
            // make a polygon as a "ghost droid"
//            float[] ghostDroid = new float[]{droid.getX(), droid.getY(), droid.getX() + droid.getWidth(), droid.getY(), droid.getX() + droid.getWidth(), droid.getY()+droid.getHeight(), droid.getX(), droid.getY() + droid.getHeight()};
            // TODO (Droid can "go over object when is on its side")
            Ray ray = new Ray();
            ray.set(droid.getX()+droid.getWidth()/2f, droid.getY()+droid.getHeight()/2f, 0, dx, dy, 0);
            for (Obstacle obstacle : obstacleRepository.findAll()) {
                Vector3 minimum = new Vector3(obstacle.getX(), obstacle.getY(), 0);
                Vector3 maximum = new Vector3(minimum.x + obstacle.getWidth(), minimum.y + obstacle.getHeight(), 0);
                BoundingBox box = new BoundingBox(minimum, maximum);
                Vector3 intersection = new Vector3();
//                // for debugging
//                if (ray.direction.len() > 0) {
//                    System.out.println("droid pos = " + droid.getX() + ", " + droid.getY());
//                    System.out.println(ray);
//                    System.out.println("BB = " + box);
//                }
                if (Intersector.intersectRayBounds(ray, box, intersection)){
                    System.out.println(ray);
                    System.out.println("droid pos = " + droid.getX() + ", " + droid.getY());
                    System.out.println("obs pos = " + obstacle.getX() + ", " + obstacle.getY());
                    System.out.println("intersects");
                    System.out.println(intersection);
                    System.out.println("BB = " + box);
                    if (droid.getX() > obstacle.getX()){
                        droid.setX(obstacle.getX() + obstacle.getWidth());
                    }else if (droid.getX() < obstacle.getX()){
                        droid.setX(obstacle.getX() - droid.getWidth());
                    }

                    if (droid.getY() > obstacle.getY()){
                        droid.setY(obstacle.getY() + obstacle.getHeight());
                    }else if (droid.getY() < obstacle.getY()){
                        droid.setY(obstacle.getY()-droid.getHeight());
                    }
                    System.out.println(droid.getX() + ", " + droid.getY());
                    droid.removeTarget();
                    droid.setDirection(direction.set(0,0));

                    return;
                }
//                this.objBounds.set(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
//                // if it collides then stop moving and make sure the droid is inside a grid cell
//                if (this.droidBounds.overlaps(objBounds)) {
//                    droid.removeTarget();
//                    droid.setDirection(direction.set(0,0));
//                    droid.setX(Math.round(droid.getX()));
//                    droid.setY(Math.round(droid.getY()));
//                    return;
//                }
            }
//            for (Enemy enemy : enemyRepository.findAll()) {
//
//                this.objBounds.set(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
//                // if it collides then stop moving and make sure the droid is inside a grid cell
//                if (this.droidBounds.overlaps(objBounds)) {
//                    droid.removeTarget();
//                    droid.setDirection(direction.set(0,0));
//                    droid.setX(Math.round(droid.getX()));
//                    droid.setY(Math.round(droid.getY()));
//                    return;
//                }
//            }
        }
    }
}
