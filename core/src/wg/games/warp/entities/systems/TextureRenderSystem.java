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
import wg.games.warp.components.gfx.RenderableComponent;
import wg.games.warp.components.SizeComponent;
import wg.games.warp.components.gfx.TextureComponent;

/**
 *
 * @author Walter
 */
public class TextureRenderSystem extends IteratingSystem {

    private ComponentMapper<TextureComponent> texMapper; //auto-injected
    private ComponentMapper<PositionComponent> posMapper; //auto-injected
    private ComponentMapper<SizeComponent> sizeMapper; //auto-injected

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
        TextureComponent tc = texMapper.get(e);
        PositionComponent pc = posMapper.get(e);
        SizeComponent sc = sizeMapper.get(e);

        batch.draw(tc.texture, pc.x, pc.y, sc.w, sc.h);
    }

}
