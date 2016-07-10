package wg.games.warp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wg.games.warp.WarpGame;

public class DesktopLauncher {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = WarpGame.TICKRATE;
        config.width = (int) (WarpGame.VIEW_WIDTH * WarpGame.VIEW_SCALE);
        config.height = (int) (WarpGame.VIEW_HEIGHT * WarpGame.VIEW_SCALE);
        config.resizable = false;
        config.title = WarpGame.TITLE;
        new LwjglApplication(new WarpGame(), config);
    }
}
