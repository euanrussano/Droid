package com.sophia.droid;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sophia.droid.controller.OrthoCamController;
import com.sophia.droid.model.Arena;
import com.sophia.droid.repository.DroidRepository;
import com.sophia.droid.service.DroidService;
import com.sophia.droid.view.Renderer;
import com.sophia.droid.view.SimpleArenaRenderer;
import com.sophia.droid.controller.ArenaController;

public class DroidGame extends Game implements InputProcessor {


	private Arena arena;
	private Renderer renderer;
	private ArenaController arenaController;
	private OrthoCamController camController;

	@Override
	public void create() {
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
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void render() {
		arenaController.update(Gdx.graphics.getDeltaTime());
		ScreenUtils.clear(Color.BLACK);
		renderer.render();

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
		Vector3 unproject = renderer.unproject(new Vector3(screenX, screenY, 0));
		//arenaController.onClick(unproject.x, unproject.y);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 unproject = renderer.unproject(new Vector3(screenX, screenY, 0));
		//arenaController.onTouchUp(unproject.x, unproject.y);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 unproject = renderer.unproject(new Vector3(screenX, screenY, 0));
		//arenaController.onTouchDrag(unproject.x, unproject.y);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		//camController.scrolled(amountX, amountY);
		return false;
	}
}