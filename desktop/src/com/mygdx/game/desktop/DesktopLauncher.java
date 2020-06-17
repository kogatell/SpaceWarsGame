package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.game.MyGdxGame;
import com.mygdx.game.game.WorldRenderer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "StarWars";
		config.width = 1024;
		config.height = 768;
		config.fullscreen = false; //CHANGE IT BEFORE SENDING IT
		new LwjglApplication(new MyGdxGame(), config);
	}
}
