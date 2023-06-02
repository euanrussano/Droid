package com.sophia.droid.model;

import java.util.ArrayList;
import java.util.List;

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
    private Box box;
    private Droid droid;


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

    public void addEnemy(Enemy enemy) {

        enemies.add(enemy);
        enemy.setArena(this);
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void setBox(Box box) {
        this.box = box;
        box.setArena(this);
    }

    public Box getBox() {
        return box;
    }

    public void removeBox() {
        box.setArena(null);
        this.box = null;

    }

    public void setDroid(Droid droid) {
        this.droid = droid;
        droid.setArena(this);
    }

    public void removeDroid() {
        droid = null;
    }

    public Droid getDroid() {
        return droid;
    }
}