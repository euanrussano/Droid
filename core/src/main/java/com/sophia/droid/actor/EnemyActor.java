package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.view.EnemyView;

public class EnemyActor extends Actor {

    private final Enemy enemy;
    private final EnemyView enemyView;

    public EnemyActor(Enemy enemy, EnemyView enemyView) {
        this.enemy = enemy;
        this.enemyView = enemyView;

        setBounds(enemy.getX()-0.5f, enemy.getY()-0.5f, enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(enemy.getX()-0.5f, enemy.getY()-0.5f, enemy.getWidth(), enemy.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        enemyView.draw(batch, parentAlpha, enemy);


    }


}
