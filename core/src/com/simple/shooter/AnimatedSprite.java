package com.simple.shooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by sbin on 10/26/2016.
 */
public class AnimatedSprite {
    //Frames per col and row 2x2 (total 4 frames)
    private static final int FRAMES_COL = 2;
    private static final int FRAMES_ROW = 2;

    private Sprite sprite;
    private Animation animation; //use this class to figure out animation
    private TextureRegion[] frames;
    private TextureRegion currentFrame;

    //how much time passed since last time we updated the image.
    //this will help us to keep track what frame we should be displaying  at given time
    private float stateTime;

    //passing sprite which will be animated.
    public AnimatedSprite(Sprite sprite)
    {
        this.sprite = sprite;
        Texture texture = sprite.getTexture();

        //Calculate HOw wide or high is the fram is..
        //Basically split out the 4 frame image/png into 1 array.
        TextureRegion[][] temp = TextureRegion.split(texture, texture.getWidth() / FRAMES_COL, texture.getHeight() / FRAMES_ROW);
        frames = new TextureRegion[FRAMES_COL * FRAMES_ROW];
        int index = 0;
        //this given image/png has 4 frames..
        for(int i = 0; i < FRAMES_ROW; i++)
        {
            for(int j = 0; j < FRAMES_COL; j++)
            {
                //Flatten out the every single frames into 1 dim array.
                frames[index++] = temp[i][j];
            }
        }

        // pass frames at 1/10 seconds speed
        animation = new Animation(0.1f, frames);
        stateTime = 0f;
    }

    public void draw(SpriteBatch spriteBatch)
    {
        //Tracking the time changes
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);

        spriteBatch.draw(currentFrame, sprite.getX(), sprite.getY());
    }

    //Since animated frame image has x number of column.. intead of 1 still image.
    //So you need to divide by number of col...rather than 2.
    public void setPosition(float x, float y)
    {
        float widthOffset = sprite.getWidth() / FRAMES_COL;
        sprite.setPosition(x - widthOffset / 2, y);
    }
}
