package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.applet.Main;

import javax.swing.*;

public class MyGdxGame implements ApplicationListener {
	WorldController controller;
	WorldRenderer renderer;
	InputScreen input;
	Boolean OnMainMenu;
	Boolean OnMusic;
	MainMenu menu;
	float dt;
	boolean hardcore;
	boolean pause;
	MenuRenderer menuRenderer;
	boolean repeat;
	
	@Override
	public void create () {
		hardcore = false;
		input = new InputScreen();
		GestureDetector gestDet = new GestureDetector(input);
		Controllers.addListener(new ControllerListener() {
			public void connected(Controller controller) {

			}

			@Override
			public void disconnected(Controller controller) {

			}

			@Override
			public boolean buttonDown(Controller controller, int buttonCode) {
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
		Gdx.input.setInputProcessor(gestDet);
		OnMainMenu = true;
		menu = new MainMenu(this);
		controller = new WorldController();
		menuRenderer = new MenuRenderer(menu);
		renderer = new WorldRenderer(controller);
		pause = false;
		repeat = false;

	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);

	}

	@Override
	public void render () {

		for (Controller controller : Controllers.getControllers()) {
			boolean oButton = controller.getButton(Ouya.BUTTON_L1);
			if(oButton) Gdx.app.log("FUNCIONO", controller.getName());
			Gdx.app.log("yo", controller.getName());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			repeat = true;
		}
		if(repeat)
		{
			controller = null;
			renderer.dispose();
			controller = new WorldController();
			renderer = new WorldRenderer(controller);
			repeat = false;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && pause) {
			pause = false;
		}
		if(!pause)
		{
			dt = Gdx.graphics.getDeltaTime();
		}
		else dt = 0;
		if(!OnMainMenu)
		{
			if(hardcore){
				controller.health = 1;
				hardcore = false;
			}
			controller.update(dt);
			Gdx.gl.glClearColor(1,0,0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			renderer.render();
		}
		else {
			menu.update(dt);
			Gdx.gl.glClearColor(1,0,0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			menuRenderer.render();
		}


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
