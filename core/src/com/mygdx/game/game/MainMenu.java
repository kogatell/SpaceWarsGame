package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import javafx.animation.Animation;

public class MainMenu {
    Animation anim;
    public static Camera camera;
    public GameObject targ;
    public GameObject musicButton;
    public GameObject muteButton;
    public GameObject startButton;
    BackgroundImage background;
    GameObject selection;

    public MainMenu()
    {
        background = new BackgroundImage(0);
        targ = new GameObject();
        targ.position.x = 0;
        targ.position.y = 0;
        camera = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, targ);
        selection = new GameObject();
        //selection.
    }

    public void update(float deltaTime)
    {
        if(targ.getCollisionRect().collidesWith(startButton.getCollisionRect()))
        {
            Gdx.app.log("Empiezo partida", "pues eos");
        }

    }
}
