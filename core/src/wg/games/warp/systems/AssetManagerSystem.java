/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import wg.games.warp.components.LoadingComponent;
import wg.games.warp.components.graphics.LoadingBarComponent;

/**
 * Manages the AssetManager. In charge of loading/unloading and updating.
 * 
 * @author waxwax
 */
public class AssetManagerSystem extends IteratingSystem {

	private final AssetManager manager;
	private ComponentMapper<LoadingComponent> loadingM;
	private ComponentMapper<LoadingBarComponent> loadingBarM;

	public AssetManagerSystem(AssetManager manager) {
		super(Aspect.all(LoadingComponent.class));
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

		LoadingComponent loadingC = loadingM.get(e);

		loadingC.loadingProgress = manager.getProgress();

		if (loadingC.loadingProgress >= 1.0f) {
			LoadingBarComponent loadingBarC = loadingBarM.get(e);
			if (loadingBarC != null) {
				if (loadingBarC.displayProgress < 1.0f)
					return;
			}
			GameState nextState = loadingC.nextState;
			world.getSystem(GameStateManager.class).signal(nextState);
			loadingM.remove(e);
		}
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
		case MAP_MAKER:
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
		case MAP_MAKER:
			break;
		}
	}
}
