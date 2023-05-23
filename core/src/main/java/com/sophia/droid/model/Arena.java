package com.sophia.droid.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {



    private final int width = 15;

    private final int height = 10;

    private static Random random = new Random(0); //System.currentTimeMillis()


    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    private List<Enemy>  enemies = new ArrayList<Enemy>();
    private List<Droid>  droids = new ArrayList<Droid>();



    public Arena() {
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

    public void populate() {

        // add obstacles and enemies at random positions
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean occupied = false;
                for (Droid droid : droids){
                    if (droid.getX() == x & droid.getY() == y){
                        occupied = true;
                        break;
                    }
                }
                if (occupied) continue;
                float p = random.nextFloat();
                if (p<0.05){
                    obstacles.add(new Obstacle(x, y));
                }else if (p < 0.1){
                    enemies.add(new Enemy(x, y));
                }
            }
        }

    }
}