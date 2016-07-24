/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

import com.artemis.Archetype;
import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import static wg.games.warp.WarpGame.VIEW_WIDTH;
import wg.games.warp.components.*;
import wg.games.warp.components.gfx.*;
import wg.games.warp.components.signals.EntityManagerSignal;
import wg.games.warp.entities.Archetypes;

/**
 Responsible for all initial entity creation. This includes level
 initialization.

 @author Walter
 */
public class EntityManager extends BaseEntitySystem {

    private final AssetManager assetManager;
    private Archetypes archetypes;
    private final World engine;

    private ComponentMapper<PositionComponent> positionM;
    private ComponentMapper<SizeComponent> sizeM;
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<ScaleComponent> scaleM;
    private ComponentMapper<EntityManagerSignal> emSignalM;

    public EntityManager(AssetManager assetManager, World engine) {
        super(Aspect.one(EntityManagerSignal.class));
        this.assetManager = assetManager;
        this.engine = engine;
    }

    @Override
    protected void initialize() {
        archetypes = new Archetypes(world);
    }

    @Override
    protected void inserted(int e) {
        EntityManagerSignal signal = emSignalM.get(e);
        destroy(signal.toDestroy);
        spawn(signal.toSpawn);
        emSignalM.remove(e);
    }

    private void spawnIntro() {
        spawnDef(archetypes.hudBack,
                (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2,
                LOADING_BAR_Y_POSITION,
                LOADING_BAR_WIDTH,
                LOADING_BAR_HEIGHT,
                assetManager.get("white.png", Texture.class));

        spawnDef(archetypes.hudLoadingBar,
                (VIEW_WIDTH - LOADING_BAR_WIDTH) / 2 + LOADING_BAR_EDGE,
                LOADING_BAR_Y_POSITION + LOADING_BAR_EDGE,
                LOADING_BAR_WIDTH - 2 * LOADING_BAR_EDGE,
                LOADING_BAR_HEIGHT - 2 * LOADING_BAR_EDGE,
                0.0f,
                1.0f,
                assetManager.get("black.png", Texture.class));

    }

    private void spawnMenu() {
        spawnDef(archetypes.button,
                (VIEW_WIDTH - MENU_BUTTON_WIDTH) / 2,
                MENU_BUTTON_Y_POSITION,
                MENU_BUTTON_WIDTH,
                MENU_BUTTON_HEIGHT,
                assetManager.get("menuButtonGame.png", Texture.class));

        spawnDef(archetypes.button,
                (VIEW_WIDTH - MENU_BUTTON_WIDTH) / 2,
                MENU_BUTTON_Y_POSITION + 1 * (MENU_BUTTON_HEIGHT + MENU_BUTTON_Y_SEPARATION),
                MENU_BUTTON_WIDTH,
                MENU_BUTTON_HEIGHT,
                assetManager.get("menuButtonGame.png", Texture.class));
    }

    private void spawnLoadGame() {
    }

    private void spawnGame() {
    }

    private int spawnDef(Archetype a, int x, int y, int w, int h, Texture t) {
        return spawnDef(a, x, y, w, h, 1.0f, 1.0f, t);
    }

    private int spawnDef(Archetype a, int x, int y, int w, int h, float xScale, float yScale, Texture t) {
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
        if (texture != null)
            texture.texture = t;
        ScaleComponent scale = scaleM.get(rv);
        if (scale != null) {
            scale.x = xScale;
            scale.y = yScale;
        }
        return rv;
    }

    public void clear() {
        IntBag ents = world.getAspectSubscriptionManager().get(Aspect.all()).getEntities();
        int[] ids = ents.getData();

        for (int i = 0; i < ids.length; i++) {
            world.delete(ids[i]);
        }
    }

    private void spawn(EntityGroup group) {
        if (group == null)
            return;

        switch (group) {
            case INTRO:
                spawnIntro();
                break;
            case MENU:
                spawnMenu();
                break;
            case LOAD_GAME:
                spawnLoadGame();
                break;
            case GAME:
                spawnGame();
                break;
        }
    }

    private void destroy(EntityGroup group) {
        if (group == null)
            return;

        switch (group) {
            case ALL:
                clear();
                break;
        }
    }

    @Override
    protected void processSystem() {
    }

    //THE LONG LIST OF ENTITY CONSTANTS
    //Loading bar
    private static final int LOADING_BAR_WIDTH = 256;
    private static final int LOADING_BAR_HEIGHT = 13;
    private static final int LOADING_BAR_EDGE = 2;
    private static final int LOADING_BAR_Y_POSITION = 96;

    //Menu button
    private static final int MENU_BUTTON_WIDTH = 256;
    private static final int MENU_BUTTON_HEIGHT = 128;
    private static final int MENU_BUTTON_Y_SEPARATION = 20;
    private static final int MENU_BUTTON_Y_POSITION = 60;

}
