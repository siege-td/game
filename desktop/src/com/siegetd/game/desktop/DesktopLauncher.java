package com.siegetd.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.siegetd.game.SiegeTd;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SiegeTd.APP_TITLE;
		config.width = SiegeTd.APP_WIDTH;
		config.height = SiegeTd.APP_HEIGHT;
		config.backgroundFPS = SiegeTd.APP_FPS;
		config.foregroundFPS = SiegeTd.APP_FPS;
		new LwjglApplication(new SiegeTd(), config);
	}
}