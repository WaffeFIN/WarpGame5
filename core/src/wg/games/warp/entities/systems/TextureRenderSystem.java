/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.entities.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import wg.games.warp.components.PositionComponent;
import wg.games.warp.components.ScaleComponent;
import wg.games.warp.components.gfx.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.gfx.LoadingBarComponent;
import wg.games.warp.components.gfx.TextureComponent;

/**
 *
 * @author Walter
 */
public class TextureRenderSystem extends IteratingSystem {

    private ComponentMapper<TextureComponent> textureM; //auto-injected
    private ComponentMapper<PositionComponent> positionM; //auto-injected
    private ComponentMapper<SizeComponent> sizeM; //auto-injected
    private ComponentMapper<ScaleComponent> scaleM; //auto-injected

    private final SpriteBatch batch;

    public TextureRenderSystem(SpriteBatch batch) {
        super(Aspect.all(RenderableComponent.class, PositionComponent.class, SizeComponent.class, TextureComponent.class));
        this.batch = batch;
    }

    @Override
    protected void begin() {
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
    }

    @Override
    protected void process(int e) {
        TextureComponent tc = textureM.get(e);
        PositionComponent pc = positionM.get(e);
        SizeComponent sc = sizeM.get(e);

        float xScale = 1.0f;
        float yScale = 1.0f;
        ScaleComponent scc = scaleM.get(e);
        if (scc != null) {
            xScale = scc.x;
            yScale = scc.y;
        }

        batch.draw(tc.texture, pc.x, pc.y, sc.w * xScale, sc.h * yScale);
    }

}
