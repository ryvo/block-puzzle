package cz.ryvo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cz.ryvo.game.G001;
import cz.ryvo.game.config.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Config.SCREEN_WIDTH;
		config.height = Config.SCREEN_HEIGHT;
		new LwjglApplication(new G001(), config);
	}
}
