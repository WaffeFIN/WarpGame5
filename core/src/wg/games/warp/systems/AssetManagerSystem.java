/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import wg.games.warp.components.signals.AssetManagerSignal;

/**
 * Manages the AssetManager. In charge of loading/unloading and updating.
 *
 * @author waxwax
 */
public class AssetManagerSystem extends BaseEntitySystem {

    private final AssetManager manager;

    private ComponentMapper<AssetManagerSignal> amSignalM;

    public AssetManagerSystem(AssetManager manager) {
        super(Aspect.all(AssetManagerSignal.class));
        this.manager = manager;
    }

    @Override
    protected void initialize() {
        manager.load("white.png", Texture.class);
        manager.load("black.png", Texture.class);
        manager.finishLoading();
    }

    @Override
    protected void inserted(int e) {
        AssetManagerSignal signal = amSignalM.get(e);

        load(signal.load);
        unload(signal.unload);

        if (signal.finish)
            manager.finishLoading();

        amSignalM.remove(e);
    }

    private void load(GameState state) {
        if (state == null)
            return;

        switch (state) {
            case INTRO:
                loadMenu();
                break;
            case MENU:
                break;
            case LOAD_GAME:
                break;
            case GAME:
                break;

        }
    }

    private void unload(GameState state) {
        if (state == null)
            return;

        switch (state) {
            case INTRO:
                break;
            case MENU:
                break;
            case LOAD_GAME:
                break;
            case GAME:
                break;
        }
    }

    private void loadMenu() {
        manager.load("menuButtonGame.png", Texture.class);
    }

    @Override
    protected void processSystem() {
        manager.update();
    }
}
