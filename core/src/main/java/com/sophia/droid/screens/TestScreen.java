package com.sophia.droid.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sophia.droid.DroidGame;

public class TestScreen implements Screen {
    private final DroidGame game;
    private SpriteBatch batch = new SpriteBatch();
    private TextureRegion region;
    private Viewport viewport = new FillViewport(10f, 10f);

    public TestScreen(DroidGame droidGame) {
        this.game = droidGame;
    }

    @Override
    public void show() {
        Texture texture = new Texture(Gdx.files.internal("tankBody_green_outline.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        region = new TextureRegion(texture);

        viewport.getCamera().position.set(5, 5, 0);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CYAN);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(region, 5, 5, 1, 1);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
