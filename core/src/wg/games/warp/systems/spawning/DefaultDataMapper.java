/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import java.util.EnumMap;
import java.util.Map;
import wg.games.warp.WarpGame;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.graphics.ColorComponent;
import wg.games.warp.components.graphics.TextureComponent;

/**
 

 @author Walter
 */
public class DefaultDataMapper {

    private final Map<EntityType, EntityData> entityDataMap;

    private final Archetypes archetypes;

    public DefaultDataMapper(Archetypes archetypes) {
        this.archetypes = archetypes;
        this.entityDataMap = new EnumMap<EntityType, EntityData>(EntityType.class);

        initDataMap();
    }

    EntityData getDefaultEntityData(EntityType type) {
        if (type == null)
            return null;
        else
            return entityDataMap.get(type);
    }

    //THE LONG LIST OF ENTITY CONSTANTS
    //Loading bar
    private static final int LOADING_BAR_WIDTH = 256;
    private static final int LOADING_BAR_HEIGHT = 13;
    private static final int LOADING_BAR_EDGE = 2;
    private static final int LOADING_BAR_Y_POSITION = 96;

    //Menu button
    private static final int MENU_BUTTON_WIDTH = 192;
    private static final int MENU_BUTTON_HEIGHT = 96;
    private static final int MENU_BUTTON_Y_GAP = 16;
    private static final int MENU_BUTTON_Y_POSITION = 48;

    
    private float centered(float n) {
        return (WarpGame.VIEW_WIDTH - n) / 2;
    }
    
    /** All default values for every spawnable entity are inserted here. */
    private void initDataMap() {
        entityDataMap.put(EntityType.INTRO_LOADING_BAR_BACK,
                new EntityData(archetypes.coloredHudBackground)
                .add(new PositionComponent(centered(LOADING_BAR_WIDTH), LOADING_BAR_Y_POSITION))
                .add(new SizeComponent(LOADING_BAR_WIDTH, LOADING_BAR_HEIGHT))
                .add(new TextureComponent("blank.png"))
                .add(new ColorComponent(Color.DARK_GRAY))
        );
        entityDataMap.put(EntityType.INTRO_LOADING_BAR_FRONT,
                new EntityData(archetypes.loadingBar)
                .add(new PositionComponent(centered(LOADING_BAR_WIDTH) + LOADING_BAR_EDGE, LOADING_BAR_Y_POSITION + LOADING_BAR_EDGE))
                .add(new SizeComponent(LOADING_BAR_WIDTH - 2 * LOADING_BAR_EDGE, LOADING_BAR_HEIGHT - 2 * LOADING_BAR_EDGE))
                .add(new TextureComponent("blank.png"))
        );
        entityDataMap.put(EntityType.MENU_BUTTON_TO_LOAD_GAME,
                new EntityData(archetypes.menuButton)
                .add(new PositionComponent(centered(MENU_BUTTON_WIDTH), MENU_BUTTON_Y_POSITION + 2 * (MENU_BUTTON_Y_GAP + MENU_BUTTON_HEIGHT)))
                .add(new SizeComponent(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT))
                .add(new TextureComponent("menuButtonGame.png"))
        );
        entityDataMap.put(EntityType.MENU_BUTTON_TO_MAP_MAKER,
                new EntityData(archetypes.menuButton)
                .add(new PositionComponent(centered(MENU_BUTTON_WIDTH), MENU_BUTTON_Y_POSITION + MENU_BUTTON_Y_GAP + MENU_BUTTON_HEIGHT))
                .add(new SizeComponent(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT))
                .add(new TextureComponent("menuButtonLevelSelect.png"))
        );
        entityDataMap.put(EntityType.MENU_BUTTON_EXIT,
                new EntityData(archetypes.menuButton)
                .add(new PositionComponent(centered(MENU_BUTTON_WIDTH), MENU_BUTTON_Y_POSITION))
                .add(new SizeComponent(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT))
                .add(new TextureComponent("menuButtonExit.png"))
        );
    }
}
