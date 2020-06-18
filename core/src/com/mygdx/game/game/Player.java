package com.mygdx.game.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Player extends GameObject{
    Collision col;
    public static final float WIDTH = 10;
    public static final float HEIGHT = 10;
    public float dir;
    public Player(){
        super();
        InputScreen.player = this;
        this.position.x = 0.0f;
        this.position.y = 0.0f;
        dir = 0;
        this.speed = 10;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.typeOfGO = 3;
        sprites.add(SpriteHelper.spriteFromTexture("spaceship.png", 0 , 0));
        this.col = new Collision(this.position.x, this.position.y, this.width, this.height);
    }
    public Vector2 GetPosition()
    {
        return this.position;
    }
    @Override
    public void update(float deltaTime){
        if(Gdx.app.getType() == Application.ApplicationType.Desktop)
        {
           Gdx.app.log("Presiono", "la key");
            Boolean a = Gdx.input.isKeyPressed(Input.Keys.A);
            Boolean d = Gdx.input.isKeyPressed(Input.Keys.D);
            int p = 2;
            if (a)
            {
                dir = -1;
            }
            else if (d)
            {
                dir = 1;
            }
            else
            {
                dir = 0;
            }
        }
        else if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            dir = Gdx.input.getGyroscopeX();
        }
        //else if()
        col.move(this.position.x,this.position.y);
        this.position.x += (this.speed * deltaTime) * dir;
        if(this.position.x < -Constants.VIEWPORT_WIDTH * 2)
        {
            this.position.x = -Constants.VIEWPORT_WIDTH * 2;
        }
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
}
