package com.simple.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sbin on 10/26/2016.
 */
public class ShotManager {

    public static final int SHOT_Y_OFFSET = 90;
    public static final int SHOT_SPEED = 500;
    public static final double MINMUM_TIME_BETWEEN_SHOTS = .5f; //every 5/10 seconds
    private static final float ENEMY_SHOT_Y_OFFSET = 400;

    private final Texture shotTexture;
    private Texture enemyShoTexture;
    private List<AnimatedSprite> shots = new ArrayList<AnimatedSprite>();
    private float timeSinceLastShot = 0;
    private Sound laser = Gdx.audio.newSound(Gdx.files.internal("data/laser-bolt.mp3"));
    private List<AnimatedSprite> enemyShots = new ArrayList<AnimatedSprite>();

    public ShotManager(Texture shotTexture, Texture enemyshoTexture)
    {
        this.shotTexture = shotTexture;
        this.enemyShoTexture = enemyshoTexture;
    }

    public void firePlayerShot(int shipCenterXLocation)
    {
        if (canFireShot()){
            Sprite newShot = new Sprite(shotTexture);
            AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
            newShotAnimated.setPosition(shipCenterXLocation, SHOT_Y_OFFSET);
            newShotAnimated.setVelocity(new Vector2(0, SHOT_SPEED));
            shots.add(newShotAnimated);
            timeSinceLastShot = 0f;
            laser.play();
        }
    }

    private boolean canFireShot() {
        return timeSinceLastShot > MINMUM_TIME_BETWEEN_SHOTS;
    }

    public void update() {
        Iterator<AnimatedSprite> i = shots.iterator();

        //Using iterator, can remove the objects...unlikely using foreach
        while(i.hasNext()) {
            AnimatedSprite shot = i.next();
            shot.move();
            if (shot.getY() > shooterGame.SCREEN_HEIGHT){
                i.remove();
            }
        }

        Iterator<AnimatedSprite> j = enemyShots.iterator();
        //Using iterator, can remove the objects...unlikely using foreach
        while(j.hasNext()) {
            AnimatedSprite shot = j.next();
            shot.move();
            if (shot.getY() < 0){
                j.remove();
            }
        }

        // Increment by delta time
        timeSinceLastShot += Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {
        for (AnimatedSprite shot : shots){
            shot.draw(batch);
        }

        for (AnimatedSprite shot : enemyShots){
            shot.draw(batch);
        }
    }

    public void fireEnemyShot(int enemyCenterXLocation)
    {
        Sprite newShot = new Sprite(enemyShoTexture);
        AnimatedSprite newShotAnimated = new AnimatedSprite(newShot);
        newShotAnimated.setPosition(enemyCenterXLocation, ENEMY_SHOT_Y_OFFSET);
        newShotAnimated.setVelocity(new Vector2(0, -SHOT_SPEED));
        enemyShots.add(newShotAnimated);
    }

    public boolean playerShotTouches(Rectangle boundingBox)
    {
        return shotTouches(shots, boundingBox);
    }

    public boolean enemyShotTouches(Rectangle boundingBox) {
        return shotTouches(enemyShots, boundingBox);
    }

    private boolean shotTouches(List<AnimatedSprite> shots,
                                Rectangle boundingBox) {
        Iterator<AnimatedSprite> i = shots.iterator();
        while(i.hasNext()){
            AnimatedSprite shot = i.next();
            //if(Intersector.intersectRectangles( shot.getBoundingBox(), boundingBox,0))
            {
                i.remove();
                return true;
            }
        }
        return false;
    }
}
