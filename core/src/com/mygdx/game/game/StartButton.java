package com.mygdx.game.game;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;

public class StartButton extends GameObject {

    public static final float WIDTH = 200;
    public static final float HEIGHT = 70;


    public boolean remove = false;

    public StartButton (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.sprites.add(SpriteHelper.spriteFromTexture("startbutton.png", 0, 0));
    }

}