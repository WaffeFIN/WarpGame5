/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;

import wg.games.warp.components.LoadingComponent;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.graphics.LoadingBarComponent;

/**
 * 
 * @author Walter
 */
public class LoadingBarSystem extends IteratingSystem {

	private static final float FAST_SPEED_DIVISOR = 10.0f;
	private static final float SLOW_SPEED_DIVISOR = 30.0f;

	private ComponentMapper<ScaleComponent> scaleM;
	private ComponentMapper<LoadingBarComponent> loadingBarM;
	private ComponentMapper<LoadingComponent> loadingM;

	public LoadingBarSystem() {
		super(Aspect.all(LoadingComponent.class, LoadingBarComponent.class, ScaleComponent.class));
	}

	@Override
	protected void process(int e) {
		LoadingComponent loadingC = loadingM.get(e);
		LoadingBarComponent loadingBarC = loadingBarM.get(e);

		loadingBarC.displayProgressDelta = Math.max(loadingBarC.displayProgressDelta,
				(loadingC.loadingProgress - loadingBarC.displayProgress));

		if (loadingBarC.displayProgress < loadingC.loadingProgress)
			loadingBarC.displayProgress += loadingBarC.displayProgressDelta / FAST_SPEED_DIVISOR;
		else
			loadingBarC.displayProgress += loadingBarC.displayProgressDelta / SLOW_SPEED_DIVISOR;
		if (loadingBarC.displayProgress > 1.0f)
			loadingBarC.displayProgress = 1.0f;

		scaleM.get(e).x = loadingBarC.displayProgress;
	}
}
