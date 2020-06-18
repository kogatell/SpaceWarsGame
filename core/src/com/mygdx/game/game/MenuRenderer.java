package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuRenderer extends MainMenu{
    public static Camera cam;
    private MainMenu _menu;
    SpriteBatch batch;
    public MenuRenderer(MainMenu menu)
    {
        super();
        //cam = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, menu.targ);
        _menu = menu;
        batch = new SpriteBatch();
    }

    public void render()
    {
        batch.begin();
         float dt = Gdx.graphics.getDeltaTime();
        _menu.update(dt);
        _menu.render(batch);
        batch.end();
    }

}
