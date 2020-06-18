package com.mygdx.game.game;

public class Selection extends GameObject{
    Collision col;
    public static final float WIDTH = 10;
    public static final float HEIGHT = 10;
    public Selection(){
        this.position.x = -100;
        this.position.y = -100;
        this.col = new Collision(this.position.x, this.position.y, this.width, this.height);
    }
    public void update(float dt)
    {
        this.col.move(this.position.x, this.position.y);
    }

}
