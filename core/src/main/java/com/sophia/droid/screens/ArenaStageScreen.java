package com.sophia.droid.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sophia.droid.DroidGame;
import com.sophia.droid.actor.*;
import com.sophia.droid.controller.CollisionManager;
import com.sophia.droid.controller.DroidController;
import com.sophia.droid.controller.EnemyController;
import com.sophia.droid.controller.OrthoCamController;
import com.sophia.droid.model.*;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.repository.EnemyRepository;
import com.sophia.droid.repository.ObstacleRepository;
import com.sophia.droid.service.ArenaGenerator;

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


    public ArenaStageScreen(DroidGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        mainStage = new Stage(new FillViewport(20f, 20f));
        uiStage = new Stage();

        world = new World(new Vector2(0,0), true);
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

    }

    private void setupUIStage() {
        //uiStage.addListener(droidController);


    }

    private void setupMainStage() {
        mainStage.getCamera().position.set(7, 5, 0);

        ArenaActor arenaActor = new ArenaActor(arena);
        arenaActor.addListener(droidController);
        mainStage.addActor(arenaActor);

        for (Droid droid : droidRepository.findAll()){
            DroidActor droidActor = new DroidActor(droid);
            droidActor.addListener(droidController);
            mainStage.addActor(droidActor);
        }

        for (Enemy enemy : arena.getEnemies()){
            EnemyActor enemyActor = new EnemyActor(enemy);
            mainStage.addActor(enemyActor);
        }

        for (Obstacle obstacle : arena.getObstacles()){
            ObstacleActor obstacleActor = new ObstacleActor(obstacle);
            mainStage.addActor(obstacleActor);
        }

        SelectionActor selectionActor = new SelectionActor();

        mainStage.addActor(selectionActor);
    }

    @Override
    public void render(float delta) {

        //collisionManager.update(delta);
        droidController.update(delta);
        enemyController.update(delta);

        uiStage.act(delta);
        mainStage.act(delta);
        ScreenUtils.clear(Color.BLACK);
        mainStage.draw();
        uiStage.draw();

        debugRenderer.render(world, mainStage.getCamera().combined);
        world.step(1/60f, 6, 2);


    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

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

        }
        return false;
    }
}
