package com.mygdx.game.game;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public class InputScreen implements GestureDetector.GestureListener, EventListener {
    static public Player player;
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {


        

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {



        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(player == null) return false;
        //player.position.x = WorldRenderer.camera.unproject(new Vector3(x-22, y, 0)).x;
        player.position.x = WorldRenderer.camera.getInputInGameWorld().x - player.width/2;
        //Gdx.app.error("cojones", player.position.y+ "position");
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    @Override
    public boolean handle(Event event) {
        return false;
    }
}
