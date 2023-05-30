package com.sophia.droid.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.repository.EnemyRepository;

public class EnemyController {
    private final Arena arena;
    private final Stage uiStage;
    private final Stage mainStage;

    public EnemyController(Arena arena, Stage uiStage, Stage mainStage) {
        this.arena = arena;
        this.uiStage = uiStage;
        this.mainStage = mainStage;
    }

    public void update(float delta) {
        for (Enemy enemy : arena.getEnemies()) {
            enemy.update(delta);
        }

    }
}
