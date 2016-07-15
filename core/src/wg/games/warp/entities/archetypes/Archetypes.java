/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.archetypes;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.World;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.gfx.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.gfx.TextureComponent;

/**
 * Contains archetypes.
 * @author waxwax
 */
public class Archetypes {

    /**
     * p,s,rend,tex
     */
    public final Archetype def;

    public Archetypes(World world) {
        def = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(SizeComponent.class)
                .add(RenderableComponent.class)
                .add(TextureComponent.class)
                .build(world);
    }

}
