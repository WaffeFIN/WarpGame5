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
import wg.games.warp.components.signals.GameStateSignal;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.gfx.LoadingBarComponent;

/**

 @author Walter
 */
public class LoadingBarSystem extends IteratingSystem {

    private static final float FAST_SPEED_DIVISOR = 10.0f;
    private static final float SLOW_SPEED_DIVISOR = 30.0f;

    private ComponentMapper<ScaleComponent> scaleM;
    private ComponentMapper<LoadingBarComponent> loadingBarM;
    private ComponentMapper<GameStateSignal> gsSignalM;

    private float loadingProgress;
    private float displayProgress;
    private float displayProgressDelta;
    private final AssetManager assetManager;

    public LoadingBarSystem(AssetManager assetManager) {
        super(Aspect.all(LoadingBarComponent.class));
        this.assetManager = assetManager;
    }

    @Override
    protected void begin() {
        loadingProgress = assetManager.getProgress();
        displayProgressDelta = Math.max(displayProgressDelta, (loadingProgress - displayProgress));
        if (loadingProgress < 1.0f) {
            if (displayProgress < loadingProgress)
                displayProgress += displayProgressDelta / FAST_SPEED_DIVISOR;
            else
                displayProgress += displayProgressDelta / SLOW_SPEED_DIVISOR;
            if (displayProgress > 1.0f)
                displayProgress = 1.0f;
        } else
            displayProgress = 1.0f;
    }

    @Override
    protected void process(int e) {
        ScaleComponent scc = scaleM.get(e);
        if (scc != null)
            scc.x = displayProgress;
        if (displayProgress >= 1.0f && loadingProgress >= 1.0f) {
            gsSignalM.create(world.create()).newState = GameState.MENU;
            loadingBarM.remove(e);
        }
    }

    @Override
    protected void removed(int entityId) {
    }

}
