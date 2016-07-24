/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Archetype;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import static wg.games.warp.WarpGame.VIEW_WIDTH;
import wg.games.warp.components.*;
import wg.games.warp.components.gfx.*;
import wg.games.warp.entities.archetypes.Archetypes;

/**
 * Responsible for all initial entity creation. This includes level
 * initialization.
 *
 * @author Walter
 */
public class EntitySpawner extends BaseSystem {

    private final AssetManager manager;
    private Archetypes archetypes;

    private ComponentMapper<PositionComponent> positionM;
    private ComponentMapper<SizeComponent> sizeM;
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<ScaleComponent> scaleM;

    //THE LONG LIST OF ENTITY CONSTANTS
    //Loading bar
    private static final int LOADING_BAR_WIDTH = 256;
    private static final int LOADING_BAR_HEIGHT = 13;
    private static final int LOADING_BAR_EDGE = 2;
    private static final int LOADING_BAR_Y_POSITION = 96;
    
    public EntitySpawner(AssetManager manager) {
        this.manager = manager;
    }

    public void setArchetypes(Archetypes archetypes) {
        this.archetypes = archetypes;
    }

    public void createIntro() {
        EntitySpawner.this.createDef(archetypes.hudBack,
                (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2,
                LOADING_BAR_Y_POSITION,
                LOADING_BAR_WIDTH,
                LOADING_BAR_HEIGHT,
                manager.get("blank.png", Texture.class));

        createDef(archetypes.hudLoadingBar,
                (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2 + LOADING_BAR_EDGE,
                LOADING_BAR_Y_POSITION + LOADING_BAR_EDGE,
                LOADING_BAR_WIDTH - 2 * LOADING_BAR_EDGE,
                LOADING_BAR_HEIGHT - 2 * LOADING_BAR_EDGE,
                0.0f,
                1.0f,
                manager.get("blank.png", Texture.class));

    }

    public int createDef(Archetype a, int x, int y, int w, int h, Texture t) {
        return createDef(a, x, y, w, h, 1.0f, 1.0f, t);
    }

    public int createDef(Archetype a, int x, int y, int w, int h, float xScale, float yScale, Texture t) {
        int rv = world.create(a);
        PositionComponent position = positionM.get(rv);
        if (position != null) {
            position.x = x;
            position.y = y;
        }
        SizeComponent size = sizeM.get(rv);
        if (size != null) {
            size.w = w;
            size.h = h;
        }
        TextureComponent texture = textureM.get(rv);
        if (texture != null) {
            texture.texture = t;
        }
        ScaleComponent scale = scaleM.get(rv);
        if (scale != null) {
            scale.x = xScale;
            scale.y = yScale;
        }
        return rv;
    }

    @Override
    protected void processSystem() {
    }

}
