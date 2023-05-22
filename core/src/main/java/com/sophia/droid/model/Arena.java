package com.sophia.droid.model;

import com.badlogic.gdx.math.Rectangle;
import com.sophia.droid.service.DroidService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {



    public static final int WIDTH = 15;

    public static final int HEIGHT = 10;

    private static Random random = new Random(0); //System.currentTimeMillis()
    private final DroidService droidService;


    private Object[][] grid;

    private List<Obstacle> obstacles = new ArrayList<Obstacle>();

    private List<Enemy>  enemies = new ArrayList<Enemy>();

    private Rectangle selectionRectangle = new Rectangle(-1f, -1f, 0f, 0f);



    public Arena(DroidService droidService) {
        this.droidService = droidService;
        grid = new Object[HEIGHT][WIDTH];
        for (int i = 0; i < WIDTH; i++) {

            for (int j = 0; j < HEIGHT; j++) {

                grid[j][i] = null;

            }

        }

        // place the droids in the arena
        for (Droid droid : droidService.findAll()){
            this.addDroid(droid);
        }
    }



    public List<Obstacle> getObstacles() {

        return obstacles;

    }

    public List<Enemy> getEnemies() {

        return enemies;

    }

    public Object[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public void addDroid(Droid droid) {

        grid[(int)droid.getY()][(int) droid.getX()] = droid;
        droid.setArena(this);
    }

    public void populate() {

        // add 5 obstacles and 5 enemies at random positions

        for (int i = 0; i < 5; i++) {

            int x = random.nextInt(WIDTH);

            int y = random.nextInt(HEIGHT);

            while (grid[y][x] != null) {

                x = random.nextInt(WIDTH);

                y = random.nextInt(HEIGHT);

            }

            grid[y][x] = new Obstacle(x, y);

            obstacles.add((Obstacle) grid[y][x]);

            while (grid[y][x] != null) {

                x = random.nextInt(WIDTH);

                y = random.nextInt(HEIGHT);

            }

            grid[y][x] = new Enemy(x, y);

            enemies.add((Enemy) grid[y][x]);

        }

    }


    public DroidService getDroidService() {
        return this.droidService;
    }

    public Rectangle getSelectionRectangle(){
        return selectionRectangle;
    }
}