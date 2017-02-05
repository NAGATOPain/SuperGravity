package luu.game.supergravity.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import luu.game.supergravity.GameConstants;
import luu.game.supergravity.MainGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GameConstants.DESKTOP_TITLE;
		config.width = GameConstants.DESKTOP_WIDTH;
		config.height = GameConstants.DESKTOP_HEIGHT;
		config.resizable = false;
		new LwjglApplication(new MainGame(), config);
	}

}
