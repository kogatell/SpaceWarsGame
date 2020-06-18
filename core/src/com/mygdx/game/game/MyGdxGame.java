package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import sun.applet.Main;

import javax.swing.*;

public class MyGdxGame implements ApplicationListener {
	WorldController controller;
	WorldRenderer renderer;
	InputScreen input;
	Boolean OnMainMenu;
	MainMenu menu;
	float dt;
	boolean pause;
	
	@Override
	public void create () {
		input = new InputScreen();
		GestureDetector gestDet = new GestureDetector(input);
		Gdx.input.setInputProcessor(gestDet);
		OnMainMenu = true;
		menu = new MainMenu();
		controller = new WorldController();
		renderer = new WorldRenderer(controller);
		pause = false;

	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);

	}

	@Override
	public void render () {
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !pause){
			pause = true;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && pause) {
			pause = false;
		}
		if(!pause)
		{
			dt = Gdx.graphics.getDeltaTime();
		}
		else dt = 0;

		controller.update(dt);
		Gdx.gl.glClearColor(0,0,0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		renderer.dispose();
	}
}
