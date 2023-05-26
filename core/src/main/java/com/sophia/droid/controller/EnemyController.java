package com.sophia.droid.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.repository.EnemyRepository;

public class EnemyController {
    private final EnemyRepository enemyRepository;
    private final Stage uiStage;
    private final Stage mainStage;

    public EnemyController(EnemyRepository enemyRepository, Stage uiStage, Stage mainStage) {
        this.enemyRepository = enemyRepository;
        this.uiStage = uiStage;
        this.mainStage = mainStage;
    }

    public void update(float delta) {
        for (Enemy enemy : enemyRepository.findAll()) {
            enemy.update(delta);
        }

    }
}
