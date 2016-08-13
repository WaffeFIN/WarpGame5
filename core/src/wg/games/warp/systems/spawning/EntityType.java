/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

/**

 @author Walter
 */
public enum EntityType {

    //INTRO
    INTRO_LOADING_BAR_FRONT, INTRO_LOADING_BAR_BACK,
    //MENU
    MENU_BUTTON_TO_LOAD_GAME, MENU_BUTTON_TO_MAP_MAKER, MENU_BUTTON_EXIT;
    //LOAD_GAME
    //GAME
    //MAP MAKER

    private static final EntityType[] introGroup = new EntityType[]{
        EntityType.INTRO_LOADING_BAR_BACK,
        EntityType.INTRO_LOADING_BAR_FRONT};
    private static final EntityType[] menuGroup = new EntityType[]{
        EntityType.MENU_BUTTON_TO_LOAD_GAME,
        EntityType.MENU_BUTTON_TO_MAP_MAKER,
        EntityType.MENU_BUTTON_EXIT};
    private static final EntityType[] loadGameGroup = new EntityType[]{};
    private static final EntityType[] gameGroup = new EntityType[]{};
    private static final EntityType[] mapMakerGroup = new EntityType[]{};

    public static EntityType[] getAllFromGroup(EntityGroup group) {
        if (group == null)
            return null;

        switch (group) {
            case INTRO:
                return introGroup;
            case MENU:
                return menuGroup;
            case LOAD_GAME:
                return loadGameGroup;
            case GAME:
                return gameGroup;
            case MAP_MAKER:
                return mapMakerGroup;
            default:
                return null;
        }
    }
}
