/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components.gfx;

import com.artemis.Component;
import com.artemis.annotations.Transient;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author waxwax
 */
@Transient
public class TextureComponent extends Component {

    public Texture texture;

    public TextureComponent() {
    }    

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }
}
