package com.sophia.droid;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sophia.droid.controller.EnemyDroidContactListener;
import com.sophia.droid.model.Arena;
import com.sophia.droid.screens.ArenaStageScreen;
import com.sophia.droid.screens.MainMenuScreen;
import com.sophia.droid.service.ArenaGenerator;
//import com.sophia.droid.controller.ArenaController;

public class DroidGame extends Game implements InputProcessor {

	public Skin skin;
	public World world;
	public Arena arena;

	@Override
	public void create() {
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Texture texture = new Texture( pixmap );

		BitmapFont font = new BitmapFont();

		skin = new Skin();
		skin.add("default", font);
		skin.add("default", texture);
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("default", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("default", Color.WHITE);
		textButtonStyle.checked = skin.newDrawable("default", Color.BLUE);
		textButtonStyle.font = font;
		skin.add("default", textButtonStyle);
		Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
		skin.add("default", labelStyle);
		ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("default", Color.DARK_GRAY), skin.newDrawable("default", Color.GREEN));
		barStyle.knobBefore = barStyle.knob;
		skin.add("default-horizontal", barStyle);


		setScreen(new MainMenuScreen(this));
	}

	public void newGame(int arenaSize){

		world = new World(new Vector2(0,0), true);
		ArenaGenerator arenaGenerator = new ArenaGenerator();
		arena = arenaGenerator.generateArena(arenaSize, world);

		setScreen(new ArenaStageScreen(this));
//		setScreen(new TestScreen(this));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		super.render();
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