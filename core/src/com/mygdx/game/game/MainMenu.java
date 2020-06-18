package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.game.InputScreen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.animation.Animation;

import java.util.ArrayList;

public class MainMenu {
    Animation anim;
    public static Camera camera;
    public GameObject targ;
    ArrayList<Button> buttons;
    BackgroundImage background;
    Button b1;
    MyGdxGame _game;
    Selection selection;

    public MainMenu(MyGdxGame game)
    {
        background = new BackgroundImage(0);
        targ = new GameObject();
        targ.position.x = 0;
        targ.position.y = 0;
        _game = game;
        camera = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, targ);
        b1 = new Button("COMENZAR",0,0,600,70);
    }

    public MainMenu() {

    }

    public void update(float deltaTime)
    {
        Vector3 touch = new Vector3(Gdx.input.getX(), 700-Gdx.input.getY(), 0);
        this.camera.unproject(touch);
        float y = Gdx.graphics.getHeight()-Gdx.input.getY();
        if(Gdx.input.justTouched()){
            System.out.println("X= "+Gdx.input.getX()+"Y= "+y);
        }
        if(b1.click(Gdx.input.getX(), y))
        {
            _game.OnMainMenu = false;
            Gdx.app.log("Toco", "FUNCIONA");
        }
    }
    public void render(Batch batch)
    {
        b1.render(batch);
        //startButton.getCurrentSprite().draw(batch);
    }

}
