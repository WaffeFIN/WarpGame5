/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.gfx;

import com.artemis.Aspect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wg.games.warp.components.gfx.BackgroundComponent;
import wg.games.warp.components.gfx.HUDComponent;

/**

 @author Walter
 */
public class ForegroundHUDRenderSystem extends AbstractRenderSystem {

    public ForegroundHUDRenderSystem(SpriteBatch batch) {
        super(batch, Aspect
                .all(HUDComponent.class)
                .exclude(BackgroundComponent.class));
    }

}
