package com.simple.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

/**
 * Created by sbin on 10/26/2016.
 */
public class Enemy {

    private final Texture enemyTexture;
    private AnimatedSprite animatedSprite;

    public Enemy(Texture enemyTexture)
    {
        this.enemyTexture = enemyTexture;
        spawn();
    }

    private void spawn()
    {
        Sprite enemySprite = new Sprite(enemyTexture);
        animatedSprite = new AnimatedSprite(enemySprite);
        int xPosition = createRandomPosition();
        animatedSprite.setPosition(xPosition, shooterGame.SCREEN_HEIGHT-animatedSprite.getHeight());

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
}
