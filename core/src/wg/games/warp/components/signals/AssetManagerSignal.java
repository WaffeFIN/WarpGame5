/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components.signals;

import wg.games.warp.systems.GameState;

/**
 *
 * @author Walter
 */
public class AssetManagerSignal extends SignalComponent {

    public GameState load;
    public GameState unload;
    public boolean finish;
}
