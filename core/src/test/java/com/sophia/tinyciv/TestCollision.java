package com.sophia.tinyciv;

import com.sophia.droid.controller.CollisionManager;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.MoveStraightDroidStrategy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;
import org.junit.jupiter.api.Test;

public class TestCollision {

    @Test
    private void testSimpleCollision(){

        DroidRepository droidRepository = new DroidRepository();
        EnemyRepository enemyRepository = new EnemyRepository();
        ObstacleRepository obstacleRepository = new ObstacleRepository();
        CollisionManager collisionManager = new CollisionManager(droidRepository, enemyRepository, obstacleRepository);

        Droid droid = new Droid();
        droid.setX(5);
        droid.setY(7);
        droid.addDroidStrategy(new MoveStraightDroidStrategy());
        droidRepository.save(droid);

        Obstacle obstacle = new Obstacle(4, 7);
        obstacleRepository.save(obstacle);

        droid.setTarget(3, 7);




    }
}
