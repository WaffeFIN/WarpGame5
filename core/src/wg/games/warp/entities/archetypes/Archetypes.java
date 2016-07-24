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
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.gfx.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.gfx.BackgroundComponent;
import wg.games.warp.components.gfx.HUDComponent;
import wg.games.warp.components.gfx.LoadingBarComponent;
import wg.games.warp.components.gfx.TextureComponent;

/**
 * Contains archetypes.
 *
 * @author waxwax
 */
public class Archetypes {

    /**
     * Default archetype. Used mostly as a building block for other archetypes.
     */
    public final Archetype def;
    
    /**
     * Default + HUD.
     */
    public final Archetype hud;
    public final Archetype hudBack;
    public final Archetype hudLoadingBar;

    public Archetypes(World world) {
        def = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(SizeComponent.class)
                .add(RenderableComponent.class)
                .add(TextureComponent.class)
                .build(world);
        hud = new ArchetypeBuilder(def)
                .add(HUDComponent.class)
                .build(world);
        hudBack = new ArchetypeBuilder(hud)
                .add(BackgroundComponent.class)
                .build(world);
        hudLoadingBar = new ArchetypeBuilder(hud)
                .add(ScaleComponent.class)
                .add(LoadingBarComponent.class)
                .build(world);
    }

}
