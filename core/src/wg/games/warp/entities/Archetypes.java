/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.entities;

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
import wg.games.warp.components.ui.ClickableComponent;

/**
 Contains archetypes.

 @author waxwax
 */
public class Archetypes {

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
        button = new ArchetypeBuilder(def)
                .add(ClickableComponent.class)
                .build(world);
    }

    /**
     Position + size + renderable + texture. Used mostly as a building block for
     other archetypes.
     */
    public final Archetype def;
    /**
     Default + HUD.
     */
    public final Archetype hud;
    /**
     Default + HUD + Background.
     */
    public final Archetype hudBack;
    /**
     Default + HUD + Background.
     */
    public final Archetype hudLoadingBar;
    /**
     Default + HUD + Scale + Loading bar.
     */
    public final Archetype button;

}
