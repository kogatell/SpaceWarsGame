package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import sun.java2d.Disposer;

public class Animator {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTIme;
    private int frameCount;
    private int frame;
    private boolean animRepeat;


    public Animator(TextureRegion region, int frameCount, float cycleTime, boolean repeatAnim)
    {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        animRepeat = repeatAnim;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt)
    {
        currentFrameTIme += dt;
        if(currentFrameTIme > maxFrameTime)
        {
            frame++;
            currentFrameTIme = 0;
        }
        if (frame >= frameCount )
        {
            frame = 0;
        }

        if (frame >= frameCount && !animRepeat)
        {
            for (int i = 0 ; i < frameCount ; i++ )
            {
                //frames.removeIndex(i);
            }
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
