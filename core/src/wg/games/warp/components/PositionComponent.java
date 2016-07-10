/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components;

import com.artemis.Component;
import com.artemis.annotations.PooledWeaver;

/**
 *
 * @author Walter
 */
@PooledWeaver
public class PositionComponent extends Component {

    public float x = 0.0f;
    public float y = 0.0f;
}
