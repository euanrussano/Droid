package com.sophia.droid.service;

import com.badlogic.gdx.physics.box2d.*;
import com.sophia.droid.controller.EnemyDroidContactListener;
import com.sophia.droid.model.*;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;

import java.util.Random;

public class ArenaGenerator {


    private final DroidRepository droidRepository;
    private final EnemyRepository enemyRepository;
    private final ObstacleRepository obstacleRepository;
    private static Random random = new Random(0); //System.currentTimeMillis()

    public ArenaGenerator(DroidRepository droidRepository, EnemyRepository enemyRepository, ObstacleRepository obstacleRepository) {
        this.droidRepository = droidRepository;
        this.enemyRepository = enemyRepository;
        this.obstacleRepository = obstacleRepository;
    }

    public Arena generateSimpleArena(World world) {
        Arena arena = new Arena(15, 10);

        // use a boolean grid to mark the occupied locations
        boolean[][] occupied = new boolean[arena.getHeight()][arena.getWidth()];

        DroidStrategy moveStrategy = new MoveStraightDroidStrategy();
        //DroidStrategy moveStrategy = new MoveXYDroidStrategy();

        // create two droids
        Droid droid = new Droid();
        // position droid in the middle
        // First we create a body definition
        {
            BodyDef bodyDef = new BodyDef();
            // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            // Set our body's starting position in the world
            bodyDef.position.set(7.5f, 7.5f);

            // Create our body in the world using our body definition
            Body body = world.createBody(bodyDef);
            body.setUserData(droid);

            // Create a circle shape and set its radius to 6
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(0.5f, 0.5f);

            // Create a fixture definition to apply our shape to
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.0f;
            fixtureDef.restitution = 0.0f; // Make it bounce a little bit

            // Create our fixture and attach it to the body
            Fixture fixture = body.createFixture(fixtureDef);
            body.setFixedRotation(true);
            droid.setBody(body);
            polygonShape.dispose();
        }
        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.

        // add move strategy in first droid
        droid.addDroidStrategy(moveStrategy);

        arena.addDroid(droid);
        occupied[7][7] = true;

//        Droid droid2 = new Droid();
//        // position droid in the left corner
//        droid2.setX(3);
//        droid2.setY(3);
//        arena.addDroid(droid2);
//        occupied[3][3] = true;

        droidRepository.save(droid);
//        droidRepository.save(droid2);

        EnemyStrategy pursueEnemyStrategy = new PursueDroidEnemyStrategy(droid, 3f);

        // add 5 obstacles, 5 enemies and 5 coins at random positions
        int x = 0;
        int y = 0;
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(0, arena.getWidth());
                y = random.nextInt(0, arena.getHeight());
            }while (occupied[y][x]);

            Obstacle obstacle = new Obstacle(x, y);
            // First we create a body definition
            BodyDef bodyDef = new BodyDef();
            // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
            bodyDef.type = BodyDef.BodyType.StaticBody;
            // Set our body's starting position in the world
            bodyDef.position.set(x+0.5f, y+0.5f);

            // Create our body in the world using our body definition
            Body body = world.createBody(bodyDef);
            body.setUserData(obstacle);

            // Create a circle shape and set its radius to 6
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(0.5f,0.5f );

            // Create a fixture definition to apply our shape to
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.0f;
            fixtureDef.restitution = 0.0f; // Make it bounce a little bit

            // Create our fixture and attach it to the body
            Fixture fixture = body.createFixture(fixtureDef);
            body.setFixedRotation(true);

            obstacle.setBody(body);


            arena.addObstacle(obstacle);
            obstacleRepository.save(obstacle);
        }
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(0, arena.getWidth());
                y = random.nextInt(0, arena.getHeight());
            }while (occupied[y][x]);

            Enemy enemy = new Enemy(x, y);

            // First we create a body definition
            BodyDef bodyDef = new BodyDef();
            // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            // Set our body's starting position in the world
            bodyDef.position.set(x+0.5f, y+0.5f);

            // Create our body in the world using our body definition
            Body body = world.createBody(bodyDef);
            body.setUserData(enemy);

            // Create a circle shape and set its radius to 6
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(0.5f,0.5f );

            // Create a fixture definition to apply our shape to
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.0f;
            fixtureDef.restitution = 0f; // Make it bounce a little bit

            // Create our fixture and attach it to the body
            Fixture fixture = body.createFixture(fixtureDef);
            body.setFixedRotation(true);

            enemy.setBody(body);

            enemy.addEnemyStrategy(pursueEnemyStrategy);
            arena.addEnemy(enemy);
            enemyRepository.save(enemy);
        }
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(0, arena.getWidth());
                y = random.nextInt(0, arena.getHeight());
            }while (occupied[y][x]);

            Coin coin = new Coin();

            // First we create a body definition
            BodyDef bodyDef = new BodyDef();
            // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            // Set our body's starting position in the world
            bodyDef.position.set(x+0.5f, y+0.5f);

            // Create our body in the world using our body definition
            Body body = world.createBody(bodyDef);
            body.setUserData(coin);

            // Create a circle shape and set its radius to 6
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.setAsBox(0.5f,0.5f );

            // Create a fixture definition to apply our shape to
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = polygonShape;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.0f;
            fixtureDef.restitution = 0f; // Make it bounce a little bit

            // Create our fixture and attach it to the body
            Fixture fixture = body.createFixture(fixtureDef);
            body.setFixedRotation(true);

            coin.setBody(body);

            arena.addCoin(coin);
        }

        return arena;
    }

}
