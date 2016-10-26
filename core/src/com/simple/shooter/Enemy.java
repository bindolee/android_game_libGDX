package com.simple.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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


    }

    private int createRandomPosition()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(shooterGame.SCREEN_WIDTH - animatedSprite.getWidth() + 1);

        return randomNumber + animatedSprite.getWidth()/2;
    }
}
