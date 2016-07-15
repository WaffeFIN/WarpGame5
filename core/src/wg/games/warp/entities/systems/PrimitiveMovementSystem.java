/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

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
    
    private ComponentMapper<PositionComponent> posMapper;
    private ComponentMapper<VelocityComponent> velMapper;

    public PrimitiveMovementSystem() {
        super(Aspect.all(PositionComponent.class, VelocityComponent.class).exclude(PhysicsComponent.class));
    }

    @Override
    protected void process(int e) {
        PositionComponent position = posMapper.get(e);
        VelocityComponent velocity = velMapper.get(e);

        position.x += velocity.x * world.delta;
        position.y += velocity.y * world.delta;
    }
}
