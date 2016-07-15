package wg.games.warp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wg.games.warp.WarpGame;

public class DesktopLauncher {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = WarpGame.TICKRATE;
        config.width = WarpGame.DISPLAY_WIDTH;
        config.height = WarpGame.DISPLAY_HEIGHT;
        config.resizable = false;
        config.title = WarpGame.TITLE;
        new LwjglApplication(new WarpGame(), config);
    }
}
