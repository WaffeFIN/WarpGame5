/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

/**

 @author Walter
 */
@PooledWeaver
public class PositionComponent extends CopyableComponent {

    public float x = 0.0f;
    public float y = 0.0f;

    public PositionComponent() {
    }

    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public <T extends Component> void copyFrom(T original) {
        if (original.getClass().equals(PositionComponent.class)) {
            PositionComponent cast = (PositionComponent) original;
            set(cast.x, cast.y);
        }
    }
}
