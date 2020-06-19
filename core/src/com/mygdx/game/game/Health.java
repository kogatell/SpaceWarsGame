package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Health extends GameObject {

    public int SPEED = 50;
    public static final float DEFAULT_Y = -1.75f;
    public static final float WIDTH = 4;
    public static final float HEIGHT = 4;
    private static Sprite texture;

    float x, y;
    Collision col;
    public boolean remove = false;

    public Health (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        texture = new Sprite(new Texture("spaceship.png"));
        this.col = new Collision(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.sprites.add(SpriteHelper.spriteFromTexture("spaceship.png", 0, 0));
        //this.giveParticleEffect("particlesgood.party");
        _particleEffect.setPosition(x, y);
        _particleEffect.start();
        this.typeOfGO = 0;
        //sprites.add(SpriteHelper.spriteFromTexture("laser.png", 0 , 0));
    }

    }
