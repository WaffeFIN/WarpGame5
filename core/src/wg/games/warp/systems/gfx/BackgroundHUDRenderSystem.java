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
public class BackgroundHUDRenderSystem extends AbstractRenderSystem {

    public BackgroundHUDRenderSystem(SpriteBatch batch) {
        super(batch, Aspect
                .all(BackgroundComponent.class, HUDComponent.class));
    }

}
