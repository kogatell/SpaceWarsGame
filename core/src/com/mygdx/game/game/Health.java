package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Health extends GameObject {
    
    public static final float WIDTH = 4;
    public static final float HEIGHT = 4;


    public boolean remove = false;

    public Health (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites.add(SpriteHelper.spriteFromTexture("spaceship.png", 0, 0));

    }

    }
