package com.sophia.droid.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.view.ObstacleView;

public class ObstacleActor extends Actor {
    private final Obstacle obstacle;
    private final ObstacleView obstacleView;

    public ObstacleActor(Obstacle obstacle, ObstacleView obstacleView) {
        this.obstacle = obstacle;
        this.obstacleView = obstacleView;

        setBounds(obstacle.getX()-0.5f, obstacle.getY()-0.5f, obstacle.getWidth(), obstacle.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setBounds(obstacle.getX()-0.5f, obstacle.getY()-0.5f, obstacle.getWidth(), obstacle.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        obstacleView.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());


    }
}
