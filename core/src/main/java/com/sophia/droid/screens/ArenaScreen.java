package com.sophia.droid.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sophia.droid.DroidGame;
import com.sophia.droid.controller.ArenaController;
import com.sophia.droid.controller.OrthoCamController;
import com.sophia.droid.model.Arena;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.service.DroidService;
import com.sophia.droid.view.Renderer;
import com.sophia.droid.view.SimpleArenaRenderer;

public class ArenaScreen implements Screen, InputProcessor {
    private final DroidGame game;

    private Arena arena;
    private Renderer renderer;
    private ArenaController arenaController;
    private OrthoCamController camController;

    public ArenaScreen(DroidGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        System.out.println("in arena screen");

        DroidRepository droidRepository = new DroidRepository();
        DroidService droidService = new DroidService(droidRepository);
        arena = new Arena(droidService);



        //populate arena with obstacles, enemies, etc
        arena.populate();

        // view
        renderer = new SimpleArenaRenderer(arena);

        // controller
        arenaController = new ArenaController(renderer, arena);
        camController = new OrthoCamController((OrthographicCamera) renderer.getCamera());

        InputMultiplexer im = new InputMultiplexer();
        im.addProcessor(camController);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);

    }

    @Override
    public void render(float delta) {
        arenaController.update(delta);
        ScreenUtils.clear(Color.BLACK);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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
            im.removeProcessor(arenaController);
            im.addProcessor(camController);
        } else if (keycode == Input.Keys.S){
            // select mode
            InputMultiplexer im = (InputMultiplexer) Gdx.input.getInputProcessor();
            im.removeProcessor(camController);
            im.addProcessor(arenaController);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
