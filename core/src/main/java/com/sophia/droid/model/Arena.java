package com.sophia.droid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private int width = 1;

    private int height = 1;


    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    private List<Enemy>  enemies = new ArrayList<Enemy>();
    private List<Droid>  droids = new ArrayList<Droid>();



    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }



    public List<Obstacle> getObstacles() {

        return obstacles;

    }

    public List<Enemy> getEnemies() {

        return enemies;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addDroid(Droid droid) {
        droids.add(droid);
        droid.setArena(this);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }
}