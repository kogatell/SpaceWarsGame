package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MuteButton extends GameObject {

    public static final float WIDTH = 70;
    public static final float HEIGHT = 70;


    public boolean remove = false;

    public MuteButton (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites.add(SpriteHelper.spriteFromTexture("mute.png", 0, 0));
    }

}