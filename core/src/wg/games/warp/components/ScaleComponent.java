/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components;

import com.artemis.Component;

/**
 *
 * @author Walter
 */
public class ScaleComponent extends Component {

    public float x = 1.0f;
    public float y = 1.0f;

    public ScaleComponent() {
    }

    public ScaleComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
