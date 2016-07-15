/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IntervalIteratingSystem;
import wg.games.warp.WarpGame;
import wg.games.warp.components.ai.AIComponent;

/**
 *
 * @author Walter
 */
public class AISystem extends IntervalIteratingSystem {
    
    ComponentMapper<AIComponent> AIMapper;

    public AISystem() {
        super(Aspect.all(AIComponent.class), WarpGame.AI_SYSTEM_INTERVAL / WarpGame.TICKRATE);
    }

    @Override
    protected void process(int e) {
    }

}
