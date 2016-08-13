/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wg.games.warp.components;

import com.artemis.Component;

/**

 @author Walter
 */
public class AlarmComponent extends Component {

    public float accumulated = 0.0f;
    public float end = 0.0f;

    public AlarmComponent() {
    }

    /** @param duration in seconds */
    public AlarmComponent(float duration) {
        this.end = duration;
    }
}
