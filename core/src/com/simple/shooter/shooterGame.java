package com.simple.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.microedition.khronos.opengles.GL10;


public class shooterGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	@Override
	public void create() {

		//Texture.setEnforcePotImages(false); <- this has been removed from libGDX

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480); // pixel for 800 x 480

		batch = new SpriteBatch();

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
