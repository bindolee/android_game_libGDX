package com.simple.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.microedition.khronos.opengles.GL10;


public class shooterGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private Sprite spaceShipSprite; //need to declare sprite for spaceship

	@Override
	public void create() {

		//Texture.setEnforcePotImages(false); <- this has been removed from libGDX

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480); // pixel for 800 x 480

		batch = new SpriteBatch();

		//Open the background.png
		background = new Texture(Gdx.files.internal("data/background.png"));

		//Need texture for spaceship.
		Texture spaceShipTexture = new Texture(Gdx.files.internal("data/spaceship.png"));
		spaceShipSprite = new Sprite(spaceShipTexture); //construct the sprite using this texture.
		spaceShipSprite.setPosition(800/2 - (spaceShipSprite.getWidth()/2), 0); //place it in the middle of bottom. x is 800 pixel, fyi.

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
		batch.draw(background,0,0);
		spaceShipSprite.draw(batch); //Sprite know how to draw by themselves
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
