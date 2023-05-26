package com.sophia.droid.service;

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

    public Arena generateSimpleArena() {
        Arena arena = new Arena(15, 10);

        // use a boolean grid to mark the occupied locations
        boolean[][] occupied = new boolean[arena.getHeight()][arena.getWidth()];

        //DroidStrategy moveStrategy = new MoveStraightDroidStrategy();
        DroidStrategy moveStrategy = new MoveXYDroidStrategy();

        // create two droids
        Droid droid = new Droid();
        // position droid in the middle
        droid.setX(7);
        droid.setY(7);
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

        // add 5 obstacles and 5 enemies at random positions
        int x = 0;
        int y = 0;
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(0, arena.getWidth());
                y = random.nextInt(0, arena.getHeight());
            }while (occupied[y][x]);

            Obstacle obstacle = new Obstacle(x, y);
            arena.addObstacle(obstacle);
            obstacleRepository.save(obstacle);
        }
        for (int i = 0; i < 5; i++) {
            do {
                x = random.nextInt(0, arena.getWidth());
                y = random.nextInt(0, arena.getHeight());
            }while (occupied[y][x]);

            Enemy enemy = new Enemy(x, y);
            enemy.addEnemyStrategy(pursueEnemyStrategy);
            arena.addEnemy(enemy);
            enemyRepository.save(enemy);
        }

        return arena;
    }
}
