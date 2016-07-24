/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import wg.games.warp.components.signals.AssetManagerSignal;
import wg.games.warp.components.signals.EntityManagerSignal;
import wg.games.warp.components.signals.GameStateSignal;

/**

 @author Walter
 */
public class GameStateManager extends BaseEntitySystem {

    private GameState state;

    private ComponentMapper<GameStateSignal> gsSignalM;
    private ComponentMapper<AssetManagerSignal> amSignalM;
    private ComponentMapper<EntityManagerSignal> emSignalM;

    public GameStateManager() {
        super(Aspect.all(GameStateSignal.class));
    }

    @Override
    protected void initialize() {
        setState(GameState.INTRO);
    }

    @Override
    protected void inserted(int e) {
        GameState newState = gsSignalM.get(e).newState;
        setState(newState);
        gsSignalM.remove(e);
    }

    private void setState(GameState newState) {
        if (newState == null || state == newState)
            return;
        AssetManagerSignal amSignal = amSignalM.create(world.create());
        amSignal.unload = state;
        amSignal.load = newState;

        EntityManagerSignal emSignal = emSignalM.create(world.create());
        emSignal.toSpawn = getEntityGroup(newState);
        emSignal.toDestroy = EntityGroup.ALL;

        state = newState;
        System.out.println("State switched to '" + state + "'");
    }

    private EntityGroup getEntityGroup(GameState newState) {
        switch (newState) {
            case INTRO:
                return EntityGroup.INTRO;
            case MENU:
                return EntityGroup.MENU;
            case LOAD_GAME:
                return EntityGroup.LOAD_GAME;
            case GAME:
                return EntityGroup.GAME;
            default:
                return null;
        }
    }

    @Override
    protected void processSystem() {
    }
}
