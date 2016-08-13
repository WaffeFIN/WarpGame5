/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.render;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wg.games.warp.systems.CameraSystem;

/**
 Clears the buffer and sets up the SpriteBatch for drawing.

 @author Walter
 */
public class PreRenderSystem extends BaseSystem {

    private final SpriteBatch batch;
    private final Color background;

    public PreRenderSystem(SpriteBatch batch, Color background) {
        this.batch = batch;
        this.background = background;
    }

    @Override
    protected void processSystem() {
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        CameraSystem cameraS = world.getSystem(CameraSystem.class);
        batch.setProjectionMatrix(cameraS.getCameraMatrix());
        batch.begin();
    }
}
