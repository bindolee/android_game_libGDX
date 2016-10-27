package com.simple.shooter;

/**
 * Created by sbin on 10/26/2016.
 */
public class CollisionManager {

    private final AnimatedSprite spaceshipAnimated;
    private final Enemy enemy;
    private final ShotManager shotManager;

    public CollisionManager(AnimatedSprite spaceshipAnimated, Enemy enemy, ShotManager shotManager)
    {
        this.spaceshipAnimated = spaceshipAnimated;
        this.enemy = enemy;
        this.shotManager = shotManager;
    }

    public void handleCollision()
    {
        handleEnemyShot();
        handlePlayerShot();
    }

    private void handlePlayerShot()
    {
        //since player is only 1.. didn't create seperate class unlikely enemy.
        if (shotManager.enemyShotTouches(spaceshipAnimated.getBoundingBox())){
            spaceshipAnimated.setDead(true);
        }
    }

    //Colision detects when shot rectangle hits the enemy or player rectangle bounding box
    private void handleEnemyShot()
    {
        if (shotManager.playerShotTouches(enemy.getBoudingBox())){
            enemy.hit();
        }
    }
}
