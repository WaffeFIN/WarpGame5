/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 Finishes off rendering.

 @author Walter
 */
public class PostRenderSystem extends BaseSystem {

    private final SpriteBatch batch;

    public PostRenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    protected void processSystem() {
        batch.end();
    }
}
