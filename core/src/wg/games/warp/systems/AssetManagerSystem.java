/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.Bag;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import wg.games.warp.components.graphics.LoadingBarComponent;

/**
 Manages the AssetManager. In charge of loading/unloading and updating.

 @author waxwax
 */
public class AssetManagerSystem extends IteratingSystem {

    private final AssetManager manager;
    private ComponentMapper<LoadingBarComponent> loadingProgressM;

    public AssetManagerSystem(AssetManager manager) {
        super(Aspect.all(LoadingBarComponent.class));
        this.manager = manager;
    }

    @Override
    protected void initialize() {
        manager.load("blank.png", Texture.class);
        manager.finishLoading();
    }

    @Override
    protected void begin() {
        manager.update();
    }

    @Override
    protected void process(int e) {
        LoadingBarComponent loadingBarC = loadingProgressM.get(e);
        if (loadingBarC == null){
            System.out.println(e);
            Bag<Component> bag = new Bag();
            world.getComponentManager().getComponentsFor(e, bag);
            System.out.println("Comps:");
            for (Component c : bag) {
                System.out.println(c.getClass());
            }
            return;
        }
        loadingBarC.loadingProgress = manager.getProgress();
    }

    void signalStateSwitch(GameState unload, GameState load, boolean finish) {
        unload(unload);
        load(load);

        if (finish)
            manager.finishLoading();
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
        manager.load("menuButtonLevelSelect.png", Texture.class);
        manager.load("menuButtonCustom.png", Texture.class);
        manager.load("menuButtonSettings.png", Texture.class);
        manager.load("menuButtonExit.png", Texture.class);
    }
}
