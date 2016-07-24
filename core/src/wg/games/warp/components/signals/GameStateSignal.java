/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components.signals;

import wg.games.warp.systems.GameState;

/**
 * Used to signal when to change game state (that is, change screens).
 *
 * @author Walter
 */
public class GameStateSignal extends SignalComponent {

    public GameState newState;
}
