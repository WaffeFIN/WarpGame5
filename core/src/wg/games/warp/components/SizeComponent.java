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
public class SizeComponent extends Component {

    public float w = 64.0f;
    public float h = 64.0f;

    public SizeComponent() {
    }

    public SizeComponent(float w, float h) {
        this.w = w;
        this.h = h;
    }

}
