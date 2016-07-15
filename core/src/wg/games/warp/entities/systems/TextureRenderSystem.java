/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import wg.games.warp.components.VisibilityComponent;

/**
 *
 * @author Walter
 */
public class RenderSystem extends IteratingSystem {

    ComponentMapper<VisibilityComponent> visibilityMapper;

    public RenderSystem() {
        super(Aspect.all(VisibilityComponent.class));
    }
    
    @Override
    protected void begin() {
        //batch
    }

    @Override
    protected void end() {
        //batch
    }

    @Override
    protected void process(int e) {
        if (isVisible(e)) {
            //draw
        }
    }
    
    private boolean isVisible(int e) {
        VisibilityComponent c = visibilityMapper.get(e);
        if (c == null) {
            return false;
        }
        return c.visible;
    }
}

