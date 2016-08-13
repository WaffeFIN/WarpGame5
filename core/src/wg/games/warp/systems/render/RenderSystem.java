/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.systems.render;

import com.artemis.Aspect.Builder;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.graphics.ColorComponent;
import wg.games.warp.components.graphics.RenderableComponent;
import wg.games.warp.components.graphics.TextureComponent;

/**

 @author Walter
 */
public abstract class RenderSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionM;
    private ComponentMapper<SizeComponent> sizeM;
    private ComponentMapper<ScaleComponent> scaleM;
    private ComponentMapper<TextureComponent> textureM;
    private ComponentMapper<ColorComponent> colorM;

    private final SpriteBatch batch;
    private final Color defaultColor;

    public RenderSystem(SpriteBatch batch, Builder aspect) {
        super(aspect.all(RenderableComponent.class, PositionComponent.class, SizeComponent.class)
                    .one(TextureComponent.class));
        this.batch = batch;
        this.defaultColor = new Color(Color.WHITE);
    }

    @Override
    protected void process(int e) {
        PositionComponent positionC = positionM.get(e);

        SizeComponent sizeC = sizeM.get(e);

        float xScale = 1.0f;
        float yScale = 1.0f;
        ScaleComponent scaleC = scaleM.get(e);
        if (scaleC != null) {
            xScale = scaleC.x;
            yScale = scaleC.y;
        }
        
        boolean colorHasChanged = false;
        ColorComponent colorC = colorM.get(e);
        if (colorC != null && colorC.color != null) {
            batch.setColor(colorC.color);
            colorHasChanged = true;
        }

        TextureComponent textureC = textureM.get(e);                
        if (textureC.texture != null)
            batch.draw(textureC.texture, positionC.x, positionC.y, sizeC.w * xScale, sizeC.h * yScale);
            
        
        if (colorHasChanged)
            batch.setColor(defaultColor);
    }

}
