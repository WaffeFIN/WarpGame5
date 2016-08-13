/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import wg.games.warp.systems.spawning.EntityManager;
import com.artemis.BaseSystem;

/**

 @author Walter
 */
public class GameStateManager extends BaseSystem {

    private GameState state;

    public GameStateManager() {
        super();
    }

    @Override
    protected void initialize() {
        setState(GameState.INTRO);
    }

    void signal(GameState newState) {
        setState(newState);
    }

    private void setState(GameState newState) {
        if (newState == null || state == newState)
            return;

        world.getSystem(AssetManagerSystem.class).signalStateSwitch(state, newState, false);
        world.getSystem(EntityManager.class).signalStateSwitch(newState);

        state = newState;
        System.out.println("State switched to " + state);
    }

    @Override
    protected void processSystem() {
    }
}
