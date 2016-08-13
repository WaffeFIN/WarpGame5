/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.components.graphics;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import wg.games.warp.components.CopyableComponent;

/**

 @author Walter
 */
public class ColorComponent extends CopyableComponent {

    public Color color;

    public ColorComponent() {
    }

    public ColorComponent(Color color) {
        this.color = color;
    }

    @Override
    public <T extends Component> void copyFrom(T original) {
        if (original.getClass().equals(ColorComponent.class)) {
            ColorComponent cast = (ColorComponent) original;
            color = cast.color;
        }
    }
}
