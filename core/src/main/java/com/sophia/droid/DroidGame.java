package com.sophia.droid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sophia.droid.model.Arena;
import com.sophia.droid.view.Renderer;
import com.sophia.droid.view.SimpleArenaRenderer;
import com.sophia.droid.controller.DroidController;
import com.sophia.droid.model.Droid;

public class DroidGame extends Game implements InputProcessor {


	private Arena arena;

	private Droid droid;
	private Renderer renderer;
	private DroidController droidController;
	@Override
	public void create() {
		droid = new Droid();

		// position droid in the middle
		droid.setX(Arena.WIDTH / 2);
		droid.setY(Arena.HEIGHT / 2);

		arena = new Arena(droid);

		// view
		renderer = new SimpleArenaRenderer(arena);

		// controller
		droidController = new DroidController(droid);

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void render() {
		droidController.update(Gdx.graphics.getDeltaTime());
		ScreenUtils.clear(Color.BLACK);
		renderer.render();

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean keyDown(int keycode) {
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
		Vector3 unproject = renderer.unproject(new Vector3(screenX, screenY, 0));
		droidController.onClick((int)unproject.x, (int)unproject.y);
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