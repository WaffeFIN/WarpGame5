/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.components.signals;

import wg.games.warp.systems.EntityGroup;

/**
 Note that toDestroy entities are destroyed before toSpawn have spawn.

 @author Walter
 */
public class EntityManagerSignal extends SignalComponent {

    public EntityGroup toDestroy;
    public EntityGroup toSpawn;
}
