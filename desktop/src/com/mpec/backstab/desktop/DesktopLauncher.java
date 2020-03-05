package com.mpec.backstab.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mpec.backstab.game.Backstab;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Backstab(), config);
		config.title = "Backstab";
		config.width = 1024;
		config.height = 768;
		config.foregroundFPS = 30;
	}
}
