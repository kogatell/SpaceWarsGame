package com.mygdx.game.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion extends GameObject{

    public static final float FRAME_LENGTH = 0.2f;
    public static final int OFFSET = 8;
    public static final int SIZE = 12;
    public static final int IMAGE_SIZE = 32;

    private static Animation anim = null;
    private Animator _anim;
    float x, y;
    float statetime;
    private Texture text;

    public boolean remove = false;

    public Explosion (float x, float y) {

        this.position.x = x - OFFSET;
        this.position.y = y - OFFSET;
        statetime = 0;
        text = new Texture("explosion.png");
        _anim = new Animator(new TextureRegion(new TextureRegion(text)), 3, 0.5f, false);
    }

    public void update (float deltatime) {
        _anim.update(deltatime);

    }

    public void render (SpriteBatch batch) {
        batch.draw( _anim.getFrame(), this.x, this.y);
    }
    @Override
    public Collision getCollisionRect () {
        return null;
    }

}
