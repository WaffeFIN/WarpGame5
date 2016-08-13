/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.spawning;

import wg.games.warp.systems.GameState;

/**

 @author Walter
 */
public enum EntityGroup {

    INTRO, MENU, LOAD_GAME, GAME, MAP_MAKER;

    public static EntityGroup get(GameState state) {
        if (state == null)
            return null;

        switch (state) {
            case INTRO:
                return EntityGroup.INTRO;
            case MENU:
                return EntityGroup.MENU;
            case LOAD_GAME:
                return EntityGroup.LOAD_GAME;
            case GAME:
                return EntityGroup.GAME;
            case MAP_MAKER:
                return EntityGroup.MAP_MAKER;
            default:
                return null;
        }
    }
}
