/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.BaseSystem;

/**
 *
 * @author Walter
 */
public class TimerSystem extends BaseSystem {

    private double preciseTime;

    @Override
    protected void processSystem() {
        preciseTime += world.delta;
    }

}
