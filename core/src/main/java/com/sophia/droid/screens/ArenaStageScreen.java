package com.sophia.droid.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sophia.droid.DroidGame;
import com.sophia.droid.actor.*;
import com.sophia.droid.controller.*;
import com.sophia.droid.model.*;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;
import com.sophia.droid.service.ArenaGenerator;
import com.sophia.droid.view.*;

public class ArenaStageScreen extends InputAdapter implements Screen {

    private final DroidGame game;
    public Stage mainStage;
    public Stage uiStage;
    public Arena arena;

    private EnemyRepository enemyRepository = new EnemyRepository();
    private ObstacleRepository obstacleRepository = new ObstacleRepository();
    private DroidRepository droidRepository = new DroidRepository();
    private DroidController droidController;
    private EnemyController enemyController;

    private OrthoCamController camController;
    //private CollisionManager collisionManager;

    public World world;
    private Box2DDebugRenderer debugRenderer;
    private EnemyDroidContactListener enemyDroidContactListener;
    private boolean isPaused = true;
    private boolean win = false;
    private boolean gameOver = false;
    private TextureRegion region;


    public ArenaStageScreen(DroidGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        mainStage = new Stage(new FillViewport(20f, 20f));
        uiStage = new Stage();

        world = new World(new Vector2(0,0), true);
        enemyDroidContactListener = new EnemyDroidContactListener();
        world.setContactListener(enemyDroidContactListener);
        debugRenderer = new Box2DDebugRenderer();

        //collisionManager = new CollisionManager(droidRepository, enemyRepository, obstacleRepository);

        droidController = new DroidController(droidRepository, uiStage, mainStage);
        enemyController = new EnemyController(enemyRepository, uiStage, mainStage);
        camController = new OrthoCamController((OrthographicCamera) mainStage.getCamera());

        ArenaGenerator arenaGenerator = new ArenaGenerator(droidRepository, enemyRepository, obstacleRepository);
        arena = arenaGenerator.generateSimpleArena(world);

        setupMainStage();
        setupUIStage();

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(camController);
        Gdx.input.setInputProcessor(im);

//        mainStage.setDebugAll(true);

        isPaused = false;

    }

    private void setupUIStage() {
        //uiStage.addListener(droidController);


    }

    private void setupMainStage() {
        mainStage.getCamera().position.set(7, 5, 0);

        DroidView droidView = new DroidTextureView();
        ArenaView arenaView = new ArenaTextureView();
        EnemyView enemyView = new EnemyTextureView();
        ObstacleView obstacleView = new ObstacleTextureView();
        CoinView coinView = new CoinTextureView();


        ArenaActor arenaActor = new ArenaActor(arena, arenaView);
        arenaActor.addListener(droidController);
        mainStage.addActor(arenaActor);


        for (Droid droid : droidRepository.findAll()){
            DroidActor droidActor = new DroidActor(droid, droidView);
            droidActor.addListener(droidController);
            mainStage.addActor(droidActor);
        }

        for (Enemy enemy : arena.getEnemies()){
            EnemyActor enemyActor = new EnemyActor(enemy, enemyView);
            mainStage.addActor(enemyActor);
        }

        for (Obstacle obstacle : arena.getObstacles()){
            ObstacleActor obstacleActor = new ObstacleActor(obstacle, obstacleView);
            mainStage.addActor(obstacleActor);
        }

        for (Coin coin : arena.getCoins()){
            CoinActor coinActor = new CoinActor(coin, coinView);
            mainStage.addActor(coinActor);
        }

        SelectionActor selectionActor = new SelectionActor();

        mainStage.addActor(selectionActor);
    }

    @Override
    public void render(float delta) {

        //uiStage.act(delta);
        mainStage.act(delta);
        ScreenUtils.clear(Color.BLACK);
        mainStage.draw();
        //uiStage.draw();

        //debugRenderer.render(world, mainStage.getCamera().combined);

        if(!isPaused){
            //collisionManager.update(delta);
            droidController.update(delta);
            enemyController.update(delta);
            world.step(1/60f, 6, 2);

            // remove the bodies scheduled
            for (Body body : enemyDroidContactListener.getBodies()){
                world.destroyBody(body);
            }
            enemyDroidContactListener.clearBodiesForRemoval();
        }

        if (arena.getDroids().size() == 0){
            gameOver = true;
        }

        if (!gameOver & (arena.getCoins().size() == 0)){
            isPaused = true;
            win = true;
        }





    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height);

    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = false;

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.P){
            // pan mode
            InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
            im.removeProcessor(mainStage);
            im.removeProcessor(uiStage);
            if (!im.getProcessors().contains(camController, false)){
                im.addProcessor(camController);
            }
        } else if (keycode == Input.Keys.S){
            // select mode
            InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
            im.removeProcessor(camController);
            if (!im.getProcessors().contains(uiStage, false)) {
                im.addProcessor(uiStage);
            }
            if (!im.getProcessors().contains(mainStage, false)) {
                im.addProcessor(mainStage);
            }

        } else if (keycode == Input.Keys.SPACE){
            isPaused = !isPaused;
        }
        return false;
    }
}
