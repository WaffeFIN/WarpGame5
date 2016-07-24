/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.gfx.LoadingBarComponent;

/**
 *
 * @author Walter
 */
public class LoadingBarSystem extends IteratingSystem {

    private ComponentMapper<ScaleComponent> scaleM;
    private ComponentMapper<LoadingBarComponent> loadingBarM;
    private float loadingProgress;

    public LoadingBarSystem() {
        super(Aspect.all(LoadingBarComponent.class));
        loadingProgress = 0.0f;
    }

    @Override
    protected void begin() {
        loadingProgress += 0.01f;
    }

    @Override
    protected void process(int e) {
        ScaleComponent scc = scaleM.get(e);
        if (scc != null) {
            scc.x = loadingProgress;
        }
        if (loadingProgress >= 1.0f) {
            loadingBarM.remove(e);
        }
    }

    @Override
    protected void removed(int entityId) {
        //signal for load completed
    }

}
