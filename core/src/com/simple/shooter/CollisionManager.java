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
}
