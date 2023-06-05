package com.sophia.droid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sophia.droid.DroidGame;

public class PreferencesScreen implements Screen {

    private final DroidGame game;
    private Stage uiStage;

    public PreferencesScreen(DroidGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        uiStage = new Stage(new FillViewport(300f, 300f));

        Table mainLayout = new Table();
        mainLayout.setFillParent(true);
        uiStage.addActor(mainLayout);

        final CheckBox cameraDroidCheckBox = new CheckBox(null, game.skin);
        cameraDroidCheckBox.getImage().scaleBy(10f);
        cameraDroidCheckBox.setChecked( game.preferences.isCameraOnDroid() );
        cameraDroidCheckBox.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean enabled = cameraDroidCheckBox.isChecked();
                game.preferences.setCameraOnDroid( enabled );
            }
        });

        final TextButton mainMenuButton = new TextButton("Back to Main Menu", game.skin);
        mainMenuButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        mainLayout.add(new Label("Lock Camera on Droid:", game.skin)).pad(10f);
        mainLayout.add(cameraDroidCheckBox).row();
        mainLayout.add(mainMenuButton);


        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        uiStage.act(delta);
        uiStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        uiStage.getViewport().update(width, height);
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
}
