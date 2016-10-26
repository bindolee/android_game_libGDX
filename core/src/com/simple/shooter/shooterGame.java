package com.simple.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import javax.microedition.khronos.opengles.GL10;


public class shooterGame implements ApplicationListener {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 480;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture background;
	private AnimatedSprite spaceshipAnimated;
	private ShotManager shotManager;

	@Override
	public void create() {

		//Texture.setEnforcePotImages(false); <- this has been removed from libGDX

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT); // pixel for 800 x 480

		batch = new SpriteBatch();

		//Open the background.png
		background = new Texture(Gdx.files.internal("data/background.png"));

		//Need texture for spaceship.
		Texture spaceShipTexture = new Texture(Gdx.files.internal("data/spaceship-spritesheet.png"));
		Sprite spaceShipSprite = new Sprite(spaceShipTexture); //construct the sprite using this texture.
		spaceshipAnimated = new AnimatedSprite(spaceShipSprite);
		spaceshipAnimated.setPosition(800/2, 0);

		//Create shotmanager w/ passing shot texture.
		Texture shotTexture = new Texture(Gdx.files.internal("data/shot-spritesheet.png"));
		shotManager = new ShotManager(shotTexture);

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
		spaceshipAnimated.draw(batch); //Sprite know how to draw by themselves
		shotManager.draw(batch);
		batch.end();

		handleInput();

		spaceshipAnimated.move();
		shotManager.update();

	}

	private void handleInput() {
		//handle touch input here..
		// get the x, y coord when touch input event is happened
		if (Gdx.input.isTouched()){
			//3 dimen vector to translate/calculate touch position
			// to get the correct cooredinates for based on camera position.
			Vector3 touchPosition =
					new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPosition);
			//int yTouch = Gdx.input.getY(); //our ship moves only x axis..so..no need here
			//Log.i(getClass().getSimpleName(), "x: "+ xTouch + "y: "+ yTouch);

			if (touchPosition.x > spaceshipAnimated.getX()){
				spaceshipAnimated.moveRight();
			}
			else{
				spaceshipAnimated.moveLeft();
			}

			shotManager.firePlayerShot(spaceshipAnimated.getX());

		}
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
