/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.physics.box2d.World;
import wg.games.warp.components.*;
import wg.games.warp.systems.GameState;

/**
 Responsible for all initial entity creation. This includes level
 initialization. This class contains hard-coded entity setups as the
 {@link DefaultDataMapper} holds all data.

 @author Walter
 */
public class EntityManager extends BaseSystem {

    private final AssetManager assetManager;
    private final World engine;
    private DefaultDataMapper defaultDataMapper;

    public EntityManager(AssetManager assetManager, World engine) {
        super();
        this.assetManager = assetManager;
        this.engine = engine;
    }

    @Override
    protected void initialize() {
        defaultDataMapper = new DefaultDataMapper(new Archetypes(world));
    }

    public void signalStateSwitch(GameState newState) {
        if (newState != GameState.INTRO)
            clear();
        spawn(EntityGroup.get(newState));
    }

    public void spawn(EntityGroup group) {
        if (group == null)
            return;
        EntityType[] types = EntityType.getAllFromGroup(group);

        for (EntityType type : types) {
            spawn(type);
        }
    }

    //type -> data package; data -> archetype; data -> default components
    public int spawn(EntityType type, Component... values) {
        if (type == null)
            return -1;
        EntityData data = defaultDataMapper.getDefaultEntityData(type);

        if (data == null)
            return -1;
        int e = world.create(data.archetype);

        setComponentValues(e, data.defaultComponentValues);
        setComponentValues(e, values);

        return e;
    }

    private void setComponentValues(int e, Iterable<Component> components) {
        for (Component value : components) {
            Class componentClass = value.getClass();
            if (CopyableComponent.class.isAssignableFrom(componentClass)) {
                ComponentMapper<CopyableComponent> mapper = world.getMapper(componentClass);
                CopyableComponent component = mapper.get(e);
                component.copyFrom(value);
            }
        }
    }

    private void setComponentValues(int e, Component[] components) {
        for (Component value : components) {
            Class componentClass = value.getClass();
            if (CopyableComponent.class.isAssignableFrom(componentClass)) {
                ComponentMapper<CopyableComponent> mapper = world.getMapper(componentClass);
                CopyableComponent component = mapper.get(e);
                component.copyFrom(value);
            }
        }
    }

    public void clear() {
        IntBag ents = world.getAspectSubscriptionManager().get(Aspect.all()).getEntities();
        int[] ids = ents.getData();

        for (int i = 0; i < ids.length; i++) {
            world.delete(ids[i]);
        }
    }

    @Override
    protected boolean checkProcessing() {
        return false;
    }

    @Override
    protected void processSystem() {
    }

}
