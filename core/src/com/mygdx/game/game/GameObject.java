package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameObject {
    public Vector2 position;
    public Vector2 target;
    public float speed;
    public float width;
    public float height;
    float _x;
    float animationSpeed;
    float shootWaitTime;
    float shootingTimer;
    int typeOfGO;
    ArrayList<Sprite> sprites;
    int spriteIndex;
    ParticleEffect _particleEffect = new ParticleEffect();
    public GameObject(){
        sprites = new ArrayList<Sprite>();
        position = new Vector2();
        //size = Vector2.Zero;
        speed = 0.0f;
    }
    public Sprite getCurrentSprite(){
        return sprites.get(spriteIndex);
    }
    public void update(float delta){

    }

    public void giveParticleEffect (String s)
    {
        _particleEffect.load(Gdx.files.internal(s), Gdx.files.internal(""));
    }

    public float lookAt(Vector2 target) {

        float angle = (float) Math.atan2(target.y - this.position.y, target.x - this.position.x);
        angle = (float) (angle * (180 / Math.PI));
        _x = MathUtils.cos(angle);
        setAngle(angle);
        this.position.x += _x;
        return _x;

    }

    public void setAngle(float ang)
    {
        this.getCurrentSprite().setRotation(ang);
    }

    public void destroyCol(Collision col)
    {
        col.desactivate();

    }

    public Collision getCollisionRect(){

        return null;
    }
    
}
