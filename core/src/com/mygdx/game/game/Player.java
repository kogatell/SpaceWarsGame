package com.mygdx.game.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player extends GameObject{
    Collision col;
    public static final float WIDTH = 10;
    public static final float HEIGHT = 10;
    public float dirX;
    public float dirY;
    public boolean isDead;
    public Player(){
        super();
        InputScreen.player = this;
        this.position.x = 0.0f;
        this.position.y = 0.0f;
        isDead = false;
        dirX = 0;
        dirY = 0;
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
        Controllers.addListener(new ControllerListener() {
            @Override
            public void connected(Controller controller) {

            }

            @Override
            public void disconnected(Controller controller) {

            }

            @Override
            public boolean buttonDown(Controller controller, int buttonCode) {
                Gdx.app.log("BOTON", String.valueOf(buttonCode));
                if(buttonCode == 0)
                {
                    dirX = -1;
                }
                if(buttonCode == 2)
                {
                    dirX = 1;
                }
                if(buttonCode == 3)
                {
                    dirY = 1;
                }
                if(buttonCode == 4)
                {
                    dirY = -1;
                }
                return false;
            }

            @Override
            public boolean buttonUp(Controller controller, int buttonCode) {
                return false;
            }

            @Override
            public boolean axisMoved(Controller controller, int axisCode, float value) {
                return false;
            }

            @Override
            public boolean povMoved(Controller controller, int povCode, PovDirection value) {
                return false;
            }

            @Override
            public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {

                return false;
            }

            @Override
            public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
                return false;
            }

            @Override
            public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
                return false;
            }
        });

        if(Gdx.app.getType() == Application.ApplicationType.Desktop)
        {
           Gdx.app.log("Presiono", "la key");
            Boolean a = Gdx.input.isKeyPressed(Input.Keys.A);
            Boolean d = Gdx.input.isKeyPressed(Input.Keys.D);
            Boolean w = Gdx.input.isKeyPressed(Input.Keys.W);
            Boolean s = Gdx.input.isKeyPressed(Input.Keys.S);
            int p = 2;
            if (a)
            {
                dirX = -1;
            }
            else if (d)
            {
                dirX = 1;
            }
            else
            {
                dirX = 0;
            }
            if(w)
            {
                dirY = 1;
            }
            else if(s)
            {
                dirY = -1;
            }
            else
            {
                dirY = 0;
            }
        }
        else if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            dirX = Gdx.input.getGyroscopeX();
            dirY = Gdx.input.getGyroscopeY();
        }
        //else if()
        col.move(this.position.x,this.position.y);
        this.position.x += (this.speed * deltaTime) * dirX;
        this.position.y += (this.speed * deltaTime) * dirY;
        if(this.position.x < -Constants.VIEWPORT_WIDTH)
        {
            this.position.x = -Constants.VIEWPORT_WIDTH;
        }
        if(this.position.x > Constants.VIEWPORT_WIDTH)
        {
            this.position.x = Constants.VIEWPORT_WIDTH;
        }
        if(this.position.y < 0)
        {
            this.position.y = 0;
        }
        if(this.position.y > Constants.VIEWPORT_HEIGHT)
        {
            this.position.y = Constants.VIEWPORT_HEIGHT;
        }
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
}
