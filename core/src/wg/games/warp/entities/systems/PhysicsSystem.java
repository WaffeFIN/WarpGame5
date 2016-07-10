/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.WarpGame;
import wg.games.warp.components.PhysicsComponent;

/**
 *
 * @author Walter
 */
public class PhysicsSystem extends IntervalSystem {
    
    private World engine; //Box2D

    public PhysicsSystem(World engine) {
        super(Aspect.all(PhysicsComponent.class), WarpGame.PHYSICS_SYSTEM_INTERVAL / WarpGame.TICKRATE);
        this.engine = engine;
    }

    @Override
    protected void processSystem() {
        engine.step(world.delta, 6, 2);
    }

}
