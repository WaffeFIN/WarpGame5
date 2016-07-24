/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.assets.AssetManager;

/**
 *
 * @author waxwax
 */
public class AssetManagerSystem extends BaseSystem {

    private final AssetManager manager;

    public AssetManagerSystem(AssetManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    protected void processSystem() {
        manager.update();
    }
}
