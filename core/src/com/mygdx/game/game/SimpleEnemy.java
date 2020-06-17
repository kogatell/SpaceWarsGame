package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SimpleEnemy extends GameObject{

    public static final int SPEED = 5;
    public static final int WIDTH = 2;
    public static final int HEIGHT = 2;
    private static Sprite texture;
    float ang;

    float x, y;
    Collision col;
    public boolean remove = false;

    public SimpleEnemy (float x) {
        this.position.x = x;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.position.y = Constants.VIEWPORT_HEIGHT;
        this.col = new Collision(x, y, WIDTH, HEIGHT);
        this.texture = new Sprite(new Texture("Asteroid.png"));
        this.sprites.add(SpriteHelper.spriteFromTexture("Asteroid.png", 0 , 0));
        this.typeOfGO = 1;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        if (y < -HEIGHT)
            remove = true;
        col.move(this.position.x, this.position.y);
        ang += 500f * deltaTime;
        Gdx.app.log("Rotacion", String.valueOf(ang));
        this.setAngle(ang);
    }


    public void render (SpriteBatch batch) {

        texture.setPosition(this.position.x, this.position.y);
        texture.draw(batch);
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }

    public float getX () {
        return this.position.x;
    }

    public float getY () {
        return this.position.y;
    }

}