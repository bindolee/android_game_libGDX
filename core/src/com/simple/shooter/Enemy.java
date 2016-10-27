package com.simple.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by sbin on 10/26/2016.
 */
public class Enemy {

    private static final float ENEMY_SPEED = 205;
    private final Texture enemyTexture;
    private AnimatedSprite animatedSprite;
    private final ShotManager shotManager;

    public Enemy(Texture enemyTexture, ShotManager shotManager)
    {
        this.enemyTexture = enemyTexture;
        this.shotManager = shotManager;
        spawn();
    }

    private void spawn()
    {
        Sprite enemySprite = new Sprite(enemyTexture);
        animatedSprite = new AnimatedSprite(enemySprite);
        int xPosition = createRandomPosition();
        animatedSprite.setPosition(xPosition, shooterGame.SCREEN_HEIGHT-animatedSprite.getHeight());
        animatedSprite.setVelocity(new Vector2(ENEMY_SPEED,0));
    }

    private int createRandomPosition()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(shooterGame.SCREEN_WIDTH - animatedSprite.getWidth() + 1);
        return randomNumber + animatedSprite.getWidth()/2;
    }

    public void draw(SpriteBatch batch)
    {
        animatedSprite.draw(batch);
    }

    public void update()
    {
        if(shouldChangeDirection()) {
            animatedSprite.changeDirection();
        }
        if (shouldFire()){
            shotManager.fireEnemyShot(animatedSprite.getX());
        }
        animatedSprite.move();
    }

    private boolean shouldFire() {
        Random random = new Random();
        return random.nextInt(51) == 0;// 1 in 50 chance. 5% chance
    }

    private boolean shouldChangeDirection()
    {
        Random random = new Random();
        return random.nextInt(21) == 0;// 1 in 20 chance. 5% chance
    }
}
