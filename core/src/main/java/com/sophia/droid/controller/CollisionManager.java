package com.sophia.droid.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;

public class CollisionManager implements ContactListener {


//    private final DroidRepository droidRepository;
//    private final EnemyRepository enemyRepository;
//    private final ObstacleRepository obstacleRepository;
//
//    private final Rectangle droidBounds = new Rectangle(0, 0, 1, 1);
//    private final Rectangle objBounds = new Rectangle(0, 0, 1, 1);
//    private final Polygon droidPoly = new Polygon(new float[]{0,0,1, 0, 1, 1, 0, 1});
//    private final Polygon objPoly = new Polygon(new float[]{0,0,1, 0, 1, 1, 0, 1});
//    private final Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
//
//    private final Vector2 direction = new Vector2();
//
//    public CollisionManager(DroidRepository droidRepository, EnemyRepository enemyRepository, ObstacleRepository obstacleRepository) {
//
//        this.droidRepository = droidRepository;
//        this.enemyRepository = enemyRepository;
//        this.obstacleRepository = obstacleRepository;
//    }
//
//    public void update(float delta){
//        for (Droid droid : droidRepository.findAll()) {
//            //check if the droid will collide in the next potential move
//            // check if the bounds of the droid overlaps with any object in the grid around it
//
//            // get the droid direction and speed to predict the next position
////            direction.set(droid.getDirection());
//
////            float dx = delta*direction.x*droid.getSpeed();
////            float dy = delta*direction.y*droid.getSpeed();
////
////            float newPositionX = droid.getX() + dx;
////            float newPositionY = droid.getY() + dy;
//
//            this.droidBounds.set(droid.getX(), droid.getY(), droid.getWidth(), droid.getHeight());
//            for (Droid droid2 : droidRepository.findAll()) {
//                if (droid == droid2) continue;
//                this.objBounds.set(droid2.getX(), droid2.getY(), droid2.getWidth(), droid2.getHeight());
//                // if it collides then stop moving and make sure the droid is inside a grid cell
//                if (this.droidBounds.overlaps(objBounds)) {
////                    droid.removeTarget();
////                    droid.setDirection(direction.set(0,0));
//
//                    return;
//                }
//            }
//
//            for (Obstacle obstacle : obstacleRepository.findAll()) {
//                objBounds.set(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
//                if (droidBounds.overlaps(objBounds)){
//                    System.out.println(droid.getX() + ", " + droid.getY());
//                    droidPoly.setPosition(droid.getX(), droid.getY());
//                    objPoly.setPosition(obstacle.getX(), obstacle.getY());
//                    if (Intersector.overlapConvexPolygons(droidPoly, objPoly, mtv)){
//                        System.out.println(mtv.depth);
//                        System.out.println(mtv.normal);
////                        droid.setX(droid.getX() + mtv.depth*mtv.normal.x);
////                        droid.setY(droid.getY() + mtv.depth*mtv.normal.y);
//
//                    }
////                    droid.setDirection(direction.set(0,0));
//
//                    return;
//                }
//            }
//            for (Enemy enemy : enemyRepository.findAll()) {
//
//                this.objBounds.set(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
//                // if it collides then stop moving and make sure the droid is inside a grid cell
//                if (this.droidBounds.overlaps(objBounds)) {
//                    droidPoly.setPosition(droid.getX(), droid.getY());
//                    objPoly.setPosition(enemy.getX(), enemy.getY());
//                    if (Intersector.overlapConvexPolygons(droidPoly, objPoly, mtv)){
////                        droid.setX(droid.getX() + mtv.depth*mtv.normal.x);
////                        droid.setY(droid.getY() + mtv.depth*mtv.normal.y);
//                        enemy.setSpeed(0);
//                    }
////                    droid.setDirection(direction.set(0,0));
//                    return;
//                }
//            }
//        }
//    }

    @Override
    public void beginContact(Contact contact) {


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
