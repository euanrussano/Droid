package com.sophia.droid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sophia.droid.DroidGame;

public class MainMenuScreen implements Screen {

    private final DroidGame game;
    private Stage stage;
    private Table pickArenaLayout;
    private Table mainLayout;

    public MainMenuScreen(DroidGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("in main menu screen");

        setupStage();
        setupArenaLayout();

        Gdx.input.setInputProcessor(stage);

    }

    private void setupStage() {

        this.stage = new Stage();

        mainLayout = new Table();
        mainLayout.setFillParent(true);
        this.stage.addActor(mainLayout);

        Label title = new Label("Droid", game.skin);
        TextButton newGame = new TextButton("New Game", game.skin);
        TextButton loadGame = new TextButton("Load Game", game.skin);
        TextButton exitGame = new TextButton("Exit Game", game.skin);

        mainLayout.add(title).pad(50f).row();
        mainLayout.add(newGame).pad(10f).row();
        mainLayout.add(loadGame).pad(10f).row();
        mainLayout.add(exitGame).pad(10f).row();


        newGame.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {

                mainLayout.remove();
                stage.addActor(pickArenaLayout);
            }
        });

        exitGame.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void setupArenaLayout() {
        pickArenaLayout = new Table();
        pickArenaLayout.setFillParent(true);

        TextButton smallArena = new TextButton("Small", game.skin);
        smallArena.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int arenaSize = 0;
                game.newGame(arenaSize);
            }
        });
        TextButton mediumArena = new TextButton("Medium", game.skin);
        mediumArena.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int arenaSize = 1;
                game.newGame(arenaSize);
            }
        });
        TextButton bigArena = new TextButton("Big", game.skin);
        bigArena.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int arenaSize = 2;
                game.newGame(arenaSize);
            }
        });

        pickArenaLayout.add(smallArena).pad(10f).row();
        pickArenaLayout.add(mediumArena).pad(10f).row();
        pickArenaLayout.add(bigArena).pad(10f).row();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        ScreenUtils.clear(Color.BLACK);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
