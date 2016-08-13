/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.PhysicsComponent;
import wg.games.warp.components.VelocityComponent;



/**
 *
 * @author Walter
 */
public class PrimitiveMovementSystem extends IteratingSystem {
    
    private ComponentMapper<PositionComponent> positionM;
    private ComponentMapper<VelocityComponent> velocityM;

    public PrimitiveMovementSystem() {
        super(Aspect.all(PositionComponent.class, VelocityComponent.class).exclude(PhysicsComponent.class));
    }

    @Override
    protected void process(int e) {
        PositionComponent positionC = positionM.get(e);
        VelocityComponent velocityC = velocityM.get(e);

        positionC.x += velocityC.x * world.delta;
        positionC.y += velocityC.y * world.delta;
    }
}
