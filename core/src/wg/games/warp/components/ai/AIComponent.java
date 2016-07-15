/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wg.games.warp.components;

import com.artemis.Component;
import wg.games.warp.ai.AIState;

/**
 *
 * @author Walter
 */
public class AIComponent extends Component {

    /**
     * If sleeping is true, the AI is excluded from the AISystem's calculations.
     * The state of the AI doesn't change if sleeping is set to true.
     */
    public boolean sleeping = false;
    public AIState state = AIState.NULL;
}
