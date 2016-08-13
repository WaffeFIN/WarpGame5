/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.render;

import com.artemis.Aspect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wg.games.warp.components.graphics.BackgroundComponent;
import wg.games.warp.components.graphics.HUDComponent;

/**

 @author Walter
 */
public class ForegroundHUDRenderSystem extends RenderSystem {

    public ForegroundHUDRenderSystem(SpriteBatch batch) {
        super(batch, Aspect
                .all(HUDComponent.class)
                .exclude(BackgroundComponent.class));
    }

}
