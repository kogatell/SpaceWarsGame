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
    StartButton start;
    Button b2;
    MuteButton mute;
    Button b3;
    HardcoreIcon hardcore;

    public MainMenu(MyGdxGame game)
    {
        background = new BackgroundImage(0);
        targ = new GameObject();
        targ.position.x = 0;
        targ.position.y = 0;
        _game = game;
        start = new StartButton(Gdx.graphics.getWidth()/2  - 100, Gdx.graphics.getHeight()/2);
        hardcore = new HardcoreIcon(Gdx.graphics.getWidth()/2 - 35, Gdx.graphics.getHeight() -280);

        camera = new Camera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, targ);
        b1 = new Button("COMENZAR",Gdx.graphics.getWidth()/2 - 100,Gdx.graphics.getHeight()/2,200,70);
        b2 = new Button("MUTE", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight() -140, 200, 70);
        b3 = new Button("HARDCORE", Gdx.graphics.getWidth()/2 - 100, Gdx.graphics.getHeight() - 280, 200, 70);
        mute = new MuteButton(Gdx.graphics.getWidth()/2 - 35, Gdx.graphics.getHeight() -140 );
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
        if(b1.click(Gdx.input.getX(), y) && Gdx.input.justTouched())
        {
            _game.OnMainMenu = false;
            Gdx.app.log("Toco", "FUNCIONA");
        }
        if(b2.click(Gdx.input.getX(), y) && Gdx.input.justTouched())
        {
            if(_game.OnMusic) _game.OnMusic = false;
            else _game.OnMusic = true;

            Gdx.app.log("Toco", "FUNCIONA");
        }
        if(b3.click(Gdx.input.getX(), y) && Gdx.input.justTouched())
        {
            if(!_game.hardcore) _game.hardcore = true;

            Gdx.app.log("Toco", "FUNCIONA");
        }
    }
    public void render(Batch batch)
    {

        b1.render(batch);
        b2.render(batch);
        b3.render(batch);
        /*start.getCurrentSprite().setPosition(start.position.x , start.position.y);
        start.getCurrentSprite().setSize(start.width, start.height);
        start.getCurrentSprite().draw(batch);
        hardcore.getCurrentSprite().setPosition(hardcore.position.x , hardcore.position.y);
        hardcore.getCurrentSprite().setSize(hardcore.width, hardcore.height);
        hardcore.getCurrentSprite().draw(batch);
        mute.getCurrentSprite().setPosition(mute.position.x , mute.position.y);
        mute.getCurrentSprite().setSize(mute.width, mute.height);
        mute.getCurrentSprite().draw(batch);*/
        //startButton.getCurrentSprite().draw(batch);
    }

}
