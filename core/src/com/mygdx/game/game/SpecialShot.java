package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpecialShot extends GameObject {

    public static final float WIDTH = 4;
    public static final float HEIGHT = 4;


    public boolean remove = false;

    public SpecialShot (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites.add(SpriteHelper.spriteFromTexture("DoubleShot.png", 0, 0));
    }

}