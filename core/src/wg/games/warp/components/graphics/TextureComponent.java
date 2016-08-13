/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components.graphics;

import com.artemis.Component;
import com.artemis.annotations.Transient;
import com.badlogic.gdx.graphics.Texture;
import wg.games.warp.components.CopyableComponent;

/**
 *
 * @author waxwax
 */
@Transient
public class TextureComponent extends CopyableComponent {

    public String textureAdress;
    public Texture texture;

    public TextureComponent() {
    }    

    public TextureComponent(String textureAdress) {
        this.textureAdress = textureAdress;
    }

    public TextureComponent(String textureAdress, Texture texture) {
        this.textureAdress = textureAdress;
        this.texture = texture;
    }

    @Override
    public <T extends Component> void copyFrom(T original) {
        if (original.getClass().equals(TextureComponent.class)) {
            TextureComponent cast = (TextureComponent) original;
            textureAdress = cast.textureAdress;
            texture = cast.texture;
        }
    }
}
