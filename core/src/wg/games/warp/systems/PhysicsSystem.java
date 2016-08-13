/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.components.PhysicsComponent;

/**
 Does (only) physics engine step.

 @author Walter
 */
public class PhysicsSystem extends IntervalSystem {

    private static final float GRAVITY = 10.0f;
    private final World engine;

    public PhysicsSystem(World engine, float interval) {
        super(Aspect.all(PhysicsComponent.class), interval);
        this.engine = engine;
    }

    @Override
    protected void initialize() {
        engine.setGravity(new Vector2(0.0f, -GRAVITY));
    }

    @Override
    protected void processSystem() {
        engine.step(world.delta, 6, 2);
    }

}
