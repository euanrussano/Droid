package com.sophia.droid.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sophia.droid.DroidGame;
import com.sophia.droid.actor.*;
import com.sophia.droid.controller.DroidController;
import com.sophia.droid.controller.OrthoCamController;
import com.sophia.droid.model.Arena;
import com.sophia.droid.model.Droid;
import com.sophia.droid.model.Enemy;
import com.sophia.droid.model.Obstacle;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.service.DroidService;

public class ArenaStageScreen extends InputAdapter implements Screen {

    private final DroidGame game;
    public Stage mainStage;
    public Stage uiStage;
    public Arena arena;

    DroidRepository droidRepository = new DroidRepository();
    DroidController droidController;

    OrthoCamController camController;

    public ArenaStageScreen(DroidGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        mainStage = new Stage(new FillViewport(20f, 20f));
        uiStage = new Stage();

        droidController = new DroidController(droidRepository, uiStage, mainStage);
        camController = new OrthoCamController((OrthographicCamera) mainStage.getCamera());

        arena = new Arena();
        //populate arena with droids
        for (Droid droid : droidRepository.findAll()){
            arena.addDroid(droid);
        }
        //populate arena with obstacles, enemies, etc
        arena.populate();

        setupMainStage();
        setupUIStage();

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(this);
        im.addProcessor(uiStage);
        im.addProcessor(camController);
        Gdx.input.setInputProcessor(im);

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
        droidController.update(delta);

        uiStage.act(delta);
        mainStage.act(delta);
        ScreenUtils.clear(Color.BLACK);
        mainStage.draw();
        uiStage.draw();

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
