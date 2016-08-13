/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.World;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.graphics.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.graphics.BackgroundComponent;
import wg.games.warp.components.graphics.ColorComponent;
import wg.games.warp.components.graphics.HUDComponent;
import wg.games.warp.components.graphics.LoadingBarComponent;
import wg.games.warp.components.graphics.TextureComponent;
import wg.games.warp.components.ui.ClickableComponent;

/**
 Contains archetypes.

 @author waxwax
 */
public class Archetypes {

    public Archetypes(World world) {
        defaultArchetype = new ArchetypeBuilder()
                .add(PositionComponent.class)
                .add(SizeComponent.class)
                .add(RenderableComponent.class)
                .add(TextureComponent.class)
                .build(world);
        hud = new ArchetypeBuilder(defaultArchetype)
                .add(HUDComponent.class)
                .build(world);
        hudBackground = new ArchetypeBuilder(hud)
                .add(BackgroundComponent.class)
                .build(world);
        coloredHudBackground = new ArchetypeBuilder(hudBackground)
                .add(ColorComponent.class)
                .build(world);
        loadingBar = new ArchetypeBuilder(hud)
                .add(ScaleComponent.class)
                .add(ColorComponent.class)
                .add(LoadingBarComponent.class)
                .build(world);
        menuButton = new ArchetypeBuilder(defaultArchetype)
                .add(ClickableComponent.class)
                .build(world);
    }

    /**
     Position + size + renderable + texture. Used mostly as a building block for
     other archetypes.
     */
    public final Archetype defaultArchetype;
    /**
     Default + HUD.
     */
    public final Archetype hud;
    /**
     Default + HUD + Background.
     */
    public final Archetype hudBackground;
    /**
     Default + HUD + Background + Color.
     */
    public final Archetype coloredHudBackground;
    /**
     Default + Clickable.
     */
    public final Archetype menuButton;
    /**
     Default + HUD + Scale + Loading bar + Color.
     */
    public final Archetype loadingBar;

}
