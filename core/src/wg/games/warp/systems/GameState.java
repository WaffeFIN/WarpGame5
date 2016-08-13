/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems;

/**

 @author waxwax
 */
public enum GameState {

    /**
     Start loading assets. Continue when menu assets are loaded.
     *//**
     Start loading assets. Continue when menu assets are loaded.
     */
    INTRO,
    /**
     Display menu buttons. No physics, AI or logic entities.
     */
    MENU,
    /**
     An in-between state where the level is loaded through a LevelLoader. Note
     that the game is frozen until level is fully loaded.
     */
    LOAD_GAME,
    /**
     In-game.
     */
    GAME,
    /**
     Map maker / Level editor.
     */
    MAP_MAKER;
}
