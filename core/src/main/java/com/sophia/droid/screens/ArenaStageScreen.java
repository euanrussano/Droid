package com.sophia.droid.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sophia.droid.DroidGame;
import com.sophia.droid.actor.*;
import com.sophia.droid.controller.*;
import com.sophia.droid.model.*;
import com.sophia.droid.view.*;

public class ArenaStageScreen extends InputAdapter implements Screen {

    private final DroidGame game;
    public Stage mainStage;
    public Stage uiStage;

    private DroidController droidController;
    private EnemyController enemyController;
    private BoxController boxController;

    private Box2DDebugRenderer debugRenderer;
    private DroidContactListener droidContactListener;
    private boolean isPaused = true;
    private boolean win = false;
    private boolean gameOver = false;

    private Label gameOverLabel;
    private TextButton nextLevelButton;



    public ArenaStageScreen(DroidGame game) {
        this.game = game;

    }

    @Override
    public void show() {
        mainStage = new Stage(new FillViewport(20f, 20f));
        uiStage = new Stage(new FitViewport(300, 300));

//        world = new World(new Vector2(0,0), true);
        droidContactListener = new DroidContactListener();
        game.world.setContactListener(droidContactListener);
        debugRenderer = new Box2DDebugRenderer();

        //collisionManager = new CollisionManager(droidRepository, enemyRepository, obstacleRepository);

//        ArenaGenerator arenaGenerator = new ArenaGenerator();
//        arena = arenaGenerator.generateSimpleArena(world);

        droidController = new DroidController(game.arena.getDroid(), uiStage, mainStage);
        enemyController = new EnemyController(game.arena, uiStage, mainStage);
        boxController = new BoxController(game.boxFactory, game.arena, uiStage, mainStage);

        setupMainStage();
        setupUIStage();

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(this);
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            GestureDetector.GestureListener camGestureController = new OrthoCamGestureController((OrthographicCamera) mainStage.getCamera());
            im.addProcessor(new GestureDetector(camGestureController));
        } else {
            InputProcessor camController = new OrthoCamMouseController((OrthographicCamera) mainStage.getCamera());
            im.addProcessor(camController);
        }
        im.addProcessor(uiStage);
        im.addProcessor(mainStage);

        Gdx.input.setInputProcessor(im);

//        mainStage.setDebugAll(true);

        isPaused = false;

    }

    private void setupUIStage() {

        Table mainLayout = new Table();
        mainLayout.setFillParent(true);
        uiStage.addActor(mainLayout);

        Table topBar = new Table();
        topBar.defaults().pad(10f);

        Label droidHPLabel = new Label("0", game.skin);
        droidController.setHPLabel(droidHPLabel);

        Label boxesLabel = new Label("0", game.skin);
        droidController.setBoxesLabel(boxesLabel);

        topBar.add(new Label("HP:", game.skin)).pad(10f);
        topBar.add(droidHPLabel).pad(10f);

        topBar.add(new Label("Boxes:", game.skin)).pad(10f);
        topBar.add(boxesLabel).pad(10f);

        Table bottomBar = new Table();
        bottomBar.defaults().pad(10f);

        Button pauseButton = new TextButton("Pause", game.skin);
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = !isPaused;
            }
        });
        bottomBar.add(pauseButton);

        Button backToMainMenuButton = new TextButton("Back to Main", game.skin);
        backToMainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.restart();
            }
        });
        bottomBar.add(backToMainMenuButton);

        Table endGameTable = new Table();
        endGameTable.defaults().pad(10f);

        gameOverLabel = new Label("Game Over", game.skin);
        gameOverLabel.setVisible(false);
        endGameTable.add(gameOverLabel).row();

        nextLevelButton = new TextButton("Next", game.skin);
        nextLevelButton.setVisible(false);
        nextLevelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.advanceLevel();
            }
        });
        endGameTable.add(nextLevelButton).row();

        mainLayout.add(topBar).growX().row();
        mainLayout.add(endGameTable).expandX().expandY().row();
        mainLayout.add(bottomBar).growX();


    }

    private void setupMainStage() {
        mainStage.getCamera().position.set(7, 5, 0);

        DroidView droidView = new DroidTextureView();
        ArenaView arenaView = new ArenaTextureView();
        EnemyView enemyView = new EnemyTextureView();
        ObstacleView obstacleView = new ObstacleTextureView();
        BoxView boxView = new BoxTextureView();


        ArenaActor arenaActor = new ArenaActor(game.arena, arenaView);
        arenaActor.addListener(droidController);
        mainStage.addActor(arenaActor);



        DroidActor droidActor = new DroidActor(game.arena.getDroid(), droidView);
        droidActor.addListener(droidController);
        mainStage.addActor(droidActor);


        for (Enemy enemy : game.arena.getEnemies()){
            EnemyActor enemyActor = new EnemyActor(enemy, enemyView);
            mainStage.addActor(enemyActor);
        }

        for (Obstacle obstacle : game.arena.getObstacles()){
            ObstacleActor obstacleActor = new ObstacleActor(obstacle, obstacleView);
            mainStage.addActor(obstacleActor);
        }


        BoxActor boxActor = new BoxActor(game.arena.getBox(), boxView);
        mainStage.addActor(boxActor);

//        SelectionActor selectionActor = new SelectionActor();
//
//        mainStage.addActor(selectionActor);
    }

    @Override
    public void render(float delta) {


        uiStage.act(delta);
        mainStage.act(delta);
        ScreenUtils.clear(Color.BLACK);
        mainStage.getViewport().apply();
        mainStage.draw();
        debugRenderer.render(game.world, mainStage.getCamera().combined);
        uiStage.getViewport().apply();
        uiStage.draw();


        if(!isPaused){
            game.world.step(1/60f, 6, 2);

            // remove the bodies scheduled
            for (Body body : droidContactListener.getBodies()){
                game.world.destroyBody(body);
            }
            droidContactListener.clearBodiesForRemoval();

            droidController.update(delta);
            enemyController.update(delta);
            boxController.update(delta);
        }

        if (game.arena.getDroid().isDead){
            gameOver = true;
        } else if (game.arena.getDroid().hasMaxBoxes()){
            win = true;
        }

        if (win){
            isPaused = true;
            gameOverLabel.setText("You won!");
            gameOverLabel.setVisible(true);
            nextLevelButton.setVisible(true);
        } else if (gameOver) {
            isPaused = true;
            gameOverLabel.setText("You lost!");
            gameOverLabel.setVisible(true);
        }


    }

    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height);
        uiStage.getViewport().update(width, height);

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
        if (keycode == Input.Keys.SPACE) {
            isPaused = !isPaused;
        }
        return false;
    }
}
