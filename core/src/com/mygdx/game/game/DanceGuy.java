package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DanceGuy extends GameObject{
    private static Animation anim = null;
    private Animator _anim;
    float x, y;
    float statetime;
    private Texture text;
    public DanceGuy (float x, float y)  {

        this.position.x = x;
        this.position.y = y;
        statetime = 0;
        text = new Texture("fl-studio-dance-art-sprite-animated-film-sprite-thumb.jpg");
        _anim = new Animator(new TextureRegion(new TextureRegion(text)), 3, 0.5f, false);

    }
}
