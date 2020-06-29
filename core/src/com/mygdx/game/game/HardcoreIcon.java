package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HardcoreIcon extends GameObject {

    public static final float WIDTH = 70;
    public static final float HEIGHT = 70;


    public boolean remove = false;

    public HardcoreIcon (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites.add(SpriteHelper.spriteFromTexture("hardcore.png", 0, 0));
    }

}