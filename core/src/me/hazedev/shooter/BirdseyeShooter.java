package me.hazedev.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

public class BirdseyeShooter implements ApplicationListener {

	public Assets assets;
	public World world;

	@Override
	public void create() {
		setFullscreen(true);
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Input.Keys.ESCAPE:
						exit();
						return true;
					case Input.Keys.F11:
						toggleFullscreen();
						return true;
				}
				return false;
			}
		});

		assets = new Assets();
		assets.loadAll();

		assets.getMusic().play();

		world = new World(assets, 512 + 128);
	}

	@Override
	public void resize(int width, int height) {
		world.viewport.update(width, height);
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.BLACK);
		world.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		assets.dispose();
		world.dispose();
	}

	public void exit() {
		Gdx.app.exit();
	}

	public void toggleFullscreen() {
		setFullscreen(!Gdx.graphics.isFullscreen());
	}

	public void setFullscreen(boolean fullscreen) {
		if (fullscreen && !Gdx.graphics.isFullscreen()) {
			Graphics.DisplayMode fullscreenMode = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(fullscreenMode);
			Gdx.graphics.setForegroundFPS(fullscreenMode.refreshRate);
		} else if (!fullscreen && Gdx.graphics.isFullscreen()) {
			Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			Gdx.graphics.setForegroundFPS(-1);
		}
	}

}
