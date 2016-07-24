/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalIteratingSystem;
import wg.games.warp.WarpGame;
import wg.games.warp.components.LogicComponent;

/**
 *
 * @author Walter
 */
public class LogicSystem extends IntervalIteratingSystem {

    public LogicSystem() {
        super(Aspect.all(LogicComponent.class), WarpGame.LOGIC_SYSTEM_INTERVAL / WarpGame.TICKRATE);
    }

    @Override
    protected void process(int entityId) {

    }
}
