package com.simple.shooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbin on 10/26/2016.
 */
public class ShotManager {

    public static final int SHOT_Y_OFFSET = 90;
    public static final int SHOT_SPEED = 500;
    private final Texture shotTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();

    public ShotManager(Texture shotTexture)
    {
        this.shotTexture = shotTexture;
    }

    public void firePlayerShot(int shipCenterXLocation)
    {
        if (canFireShot()){
            Sprite newShot = new Sprite(shotTexture);
            AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
            newShotAnimated.setPosition(shipCenterXLocation, SHOT_Y_OFFSET);
            newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
            shots.add(newShotAnimated);
        }
    }

    private boolean canFireShot() {
        return true;
    }

    public void update() {
        for (AnimatedSprite shot : shots){
            shot.move();
        }
    }

    public void draw(SpriteBatch batch) {
        for (AnimatedSprite shot : shots){
            shot.draw(batch);
        }
    }
}
