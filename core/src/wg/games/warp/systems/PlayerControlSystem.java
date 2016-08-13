/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.systems.IteratingSystem;
import wg.games.warp.components.LocallyControlledComponent;
import wg.games.warp.components.PhysicsComponent;

/**
 *
 * @author Walter
 */
public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Aspect.all(LocallyControlledComponent.class, PhysicsComponent.class));
    }

    @Override
    protected void process(int entityId) {
        
    }
    
}
