package com.sophia.droid;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.sophia.droid.model.Arena;
import com.sophia.droid.screens.ArenaStageScreen;
import com.sophia.droid.screens.MainMenuScreen;
import com.sophia.droid.service.AppPreferences;
import com.sophia.droid.service.ArenaGenerator;
import com.sophia.droid.service.BoxFactory;

import java.util.prefs.Preferences;
//import com.sophia.droid.controller.ArenaController;

public class DroidGame extends Game{

	public Skin skin;
	public World world;
	public Arena arena;
	public BoxFactory boxFactory;
	public AppPreferences preferences;

	@Override
	public void create() {
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888 );
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		Texture texture = new Texture( pixmap );

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/kenvector_future.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		parameter.minFilter = Texture.TextureFilter.Linear;
		parameter.magFilter = Texture.TextureFilter.Linear;
		BitmapFont font = generator.generateFont(parameter); // font size 30 pixels

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
		CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle(skin.newDrawable("default", Color.DARK_GRAY), skin.newDrawable("default", Color.GREEN), font, Color.WHITE );
		skin.add("default" ,checkBoxStyle);

		preferences = new AppPreferences();

		setScreen(new MainMenuScreen(this));
	}

	public void newGame(int arenaSize){


		world = new World(new Vector2(0,0), true);
		boxFactory = new BoxFactory(world);
		ArenaGenerator arenaGenerator = new ArenaGenerator(boxFactory);
		arena = arenaGenerator.generateArena(arenaSize, world);

		setScreen(new ArenaStageScreen(this));
//		setScreen(new TestScreen(this));
	}

	public void advanceLevel() {
		System.out.println("TO next level");
	}

	public void restart() {
		getScreen().dispose();
		setScreen(new MainMenuScreen(this));
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



}